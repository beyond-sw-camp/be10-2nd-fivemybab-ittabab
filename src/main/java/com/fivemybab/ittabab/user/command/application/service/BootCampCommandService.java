package com.fivemybab.ittabab.user.command.application.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fivemybab.ittabab.exception.NotFoundException;
import com.fivemybab.ittabab.exception.ServerInternalException;
import com.fivemybab.ittabab.user.command.application.dto.CreateBootCampRequest;
import com.fivemybab.ittabab.user.command.application.dto.UpdateBootCampRequest;
import com.fivemybab.ittabab.user.command.domain.aggregate.BootCamp;
import com.fivemybab.ittabab.user.command.domain.repository.BootCampRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Service
@RequiredArgsConstructor
public class BootCampCommandService {

    @Value("${google.api-key}")
    private String apiKey;
    @Value("${google.geocoding-url}")
    private String geocodingUrl;

    private final ModelMapper modelMapper;
    private final BootCampRepository bootcampRepository;
    private final ObjectMapper objectMapper;

    @Transactional
    public void createBootCamp(CreateBootCampRequest createBootCampRequest) {

        BootCamp bootCamp = modelMapper.map(createBootCampRequest, BootCamp.class);
        bootcampRepository.save(bootCamp);
    }

    @Transactional
    public void modifyBootCamp(Long id, UpdateBootCampRequest updateBootCampRequest) {

        BootCamp foundBootCamp = bootcampRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("해당 id에 맞는 훈련 기관이 없습니다. code : " + id));
        foundBootCamp.modifyName(updateBootCampRequest.getBootName());
        foundBootCamp.modifyLocation(updateBootCampRequest.getBootLocation());
    }

    @Transactional
    public void deleteBootCamp(Long id) {

        bootcampRepository.deleteById(id);
    }

    /* 주소를 받아 좌표를 반환 */
    public double[] getCoordinates(String address) {

        RestTemplate restTemplate = new RestTemplate();

        String url = UriComponentsBuilder.fromHttpUrl(geocodingUrl)
                .queryParam("address", address)
                .queryParam("key", apiKey)
                .queryParam("language", "ko")
                .queryParam("components", "country:KR")
                .toUriString();

        log.info("URL 시도 중: {}", url);

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            log.info("응답 상태: {}", response.getStatusCode());
            log.info("응답 본문: {}", response.getBody());

            JsonNode jsonNode = objectMapper.readTree(response.getBody());

            if ("OK".equals(jsonNode.path("status").asText())) {
                JsonNode location = jsonNode.path("results").get(0).path("geometry").path("location");
                double latitude = location.path("lat").asDouble();
                double longitude = location.path("lng").asDouble();
                log.info("좌표 찾음: 위도={}, 경도={}", latitude, longitude);
                return new double[]{latitude, longitude};
            } else {
                log.warn("주소에 대한 결과값이 없습니다: {}. 상태: {}", address, jsonNode.path("status").asText());
                throw new ServerInternalException("주소에 대한 결과 값이 없습니다. 에러 발생.");
            }
        } catch (Exception e) {
            log.error("주소의 좌표를 가져오는 중 오류 발생: {}", address, e);
            throw new ServerInternalException("주소의 좌표를 가져오는 중 에러 발생");
        }

    }
}

