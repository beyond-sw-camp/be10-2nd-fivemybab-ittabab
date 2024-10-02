package com.fivemybab.ittabab.user.command.application.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fivemybab.ittabab.config.redis.MailService;
import com.fivemybab.ittabab.config.redis.RedisService;
import com.fivemybab.ittabab.exception.ServerInternalException;
import com.fivemybab.ittabab.user.command.application.dto.CreateUserRequest;
import com.fivemybab.ittabab.user.command.application.dto.MailRequestDto;
import com.fivemybab.ittabab.user.command.application.dto.UpdateUserRequest;
import com.fivemybab.ittabab.user.command.domain.aggregate.UserInfo;
import com.fivemybab.ittabab.user.command.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Duration;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserCommandService {

    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final MailService mailService;
    private final RedisService redisService;

    private String AUTH_CODE_PREFIX = "AuthCode_";
    @Value("${spring.mail.auth-code-expiration-millis}")
    private long authCodeExpirationMillis;

    @Value("${google.api.key}")
    private String googleApiKey;
    @Value("${google.map-api-url}")
    private String geolocationUrl;

    private final double allowedDistance = 0.1; // 허용 가능한 거리 (킬로미터 단위)

    @Transactional
    public void createUser(CreateUserRequest newUser) {

        // 넘어온 객체를 UserEntity로 변환
        UserInfo user = modelMapper.map(newUser, UserInfo.class);

        // 이메일 인증 검증
        EmailVerification(newUser.getEmail(), newUser.getAuthCode());

        // 위치 인증

        user.encryptPwd(passwordEncoder.encode(newUser.getPwd()));
        userRepository.save(user);
    }

    @Transactional
    public void modifyUser(Long userNo, UpdateUserRequest updateUserRequest) {

        UserInfo foundUser = userRepository.findByUserId(userNo);
        foundUser.modifyPwd(passwordEncoder.encode(updateUserRequest.getPwd()));
        foundUser.modifyPhone(updateUserRequest.getPhone());
    }

    @Transactional
    public void deleteUser(Long userNo) {

        userRepository.deleteById(userNo);
    }

    public void sendCodeToEmail(MailRequestDto mailRequestDto) {
        String email = mailRequestDto.getEmail();

        // email 중복검사
        sameUserInDBByEmail(email);

        String title = "회원가입 이메일 인증 번호";
        String authCode = createCode();
        mailService.sendEmail(email, title, authCode);
        // 이메일 인증 요청 시 인증 번호 Redis에 저장 ( key = "AuthCode " + Email / value = AuthCode )
        redisService.setValues(AUTH_CODE_PREFIX + email,
                authCode, Duration.ofMillis(authCodeExpirationMillis));
    }

    private void sameUserInDBByEmail(String email) {

        UserInfo user = userRepository.findByEmail(email);

        if (user != null) {
            // 사용자 정보가 존재할 경우 예외 발생
            throw new IllegalArgumentException("이미 존재하는 이메일입니다: " + email);
        }
    }

    private String createCode() {
        int lenth = 6;
        try {
            Random random = SecureRandom.getInstanceStrong();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < lenth; i++) {
                builder.append(random.nextInt(10));
            }
            return builder.toString();
        } catch (NoSuchAlgorithmException e) {
            log.debug("UserCommandService.createCode() exception occur");
            throw new ServerInternalException("UserCommandService.createCode()에서 에러가 발생했습니다.");
        }
    }

    private void EmailVerification(String email, String authCode) {

        sameUserInDBByEmail(email);

        String key = AUTH_CODE_PREFIX + email;
        String redisAuthCode = redisService.getValues(key);

        if (!(redisService.checkExistsValue(redisAuthCode) && redisAuthCode.equals(authCode))) {
            throw new IllegalArgumentException("인증번호가 틀렸습니다. 다시 입력해주세요.");
        } else {
            redisService.deleteValues(key);
        }
    }

    /* 현재 위치 구하기 */
    public void getCurrentLocation() {

        RestTemplate restTemplate = new RestTemplate();

        try {

            // API 호출
            JsonNode response = restTemplate.postForObject(geolocationUrl + googleApiKey, null, JsonNode.class);

            if (response != null) {

                // 좌표 추출
                JsonNode location = response.path("location");
                double latitude = location.path("lat").asDouble();
                double longitude = location.path("lng").asDouble();

                System.out.println("현재 위치: 위도 " + latitude + ", 경도 " + longitude);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Haversine 공식을 사용하여 두 좌표 간의 거리 계산
    public static double haversine(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // 지구의 반지름 (킬로미터 단위)

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c; // 거리를 킬로미터로 변환

        return distance; // 거리 반환
    }

    public void currentLocationVerification(double targetLatitude, double targetLongitude) {
        RestTemplate restTemplate = new RestTemplate();

        // 현재 위치 가져오기
        JsonNode response = restTemplate.postForObject(geolocationUrl, null, JsonNode.class);
        if (response != null) {
            double currentLatitude = response.path("location").path("lat").asDouble();
            double currentLongitude = response.path("location").path("lng").asDouble();

            // 두 위치 간의 거리 계산
            double distance = haversine(targetLatitude, targetLongitude, currentLatitude, currentLongitude);
            System.out.println("현재 위치와 지정된 위치 간의 거리: " + distance + " km");

            // 거리 검증
            if (distance <= allowedDistance) {
                System.out.println("현재 위치가 허용 범위 내에 있습니다.");
            } else {
                System.out.println("현재 위치가 허용 범위를 초과합니다.");
            }
        }
    }

}
