package com.fivemybab.ittabab.user.command.application.service;

import com.fivemybab.ittabab.user.command.domain.aggregate.BootCamp; // 클래스 이름 확인
import com.fivemybab.ittabab.bootcamp.command.dto.BootCampDTO;
import com.fivemybab.ittabab.user.command.domain.repository.BootCampRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException; // 수정된 예외 처리
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BootCampService {

    private final BootCampRepository bootcampRepository;

    public List<BootCampDTO> findAll() {
        return bootcampRepository.findAll()
                .stream()
                .map(bootcamp -> new BootCampDTO(bootcamp.getBootId(), bootcamp.getBootName(), bootcamp.getBootLocation()))
                .collect(Collectors.toList());
    }

    public BootCampDTO save(BootCampDTO bootcampDTO) {
        BootCamp bootcamp = BootCamp.of(bootcampDTO.getBootName(), bootcampDTO.getBootLocation());
        return new BootCampDTO(bootcampRepository.save(bootcamp).getBootId(),
                bootcamp.getBootName(),
                bootcamp.getBootLocation());
    }

    public BootCampDTO update(Long id, BootCampDTO bootcampDTO) {
        BootCamp bootcamp = bootcampRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Bootcamp not found"));
        bootcamp.setBootName(bootcampDTO.getBootName());
        bootcamp.setBootLocation(bootcampDTO.getBootLocation());
        return new BootCampDTO(bootcampRepository.save(bootcamp).getBootId(), bootcamp.getBootName(), bootcamp.getBootLocation());
    }

    public void delete(Long id) {
        bootcampRepository.deleteById(id);
    }
}

