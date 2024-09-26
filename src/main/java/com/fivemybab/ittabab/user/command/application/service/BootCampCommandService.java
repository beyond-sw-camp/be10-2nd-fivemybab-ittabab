package com.fivemybab.ittabab.user.command.application.service;

import com.fivemybab.ittabab.user.command.domain.aggregate.BootCamp; // 클래스 이름 확인
import com.fivemybab.ittabab.user.query.dto.BootCampDto;
import com.fivemybab.ittabab.user.command.domain.repository.BootCampRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException; // 수정된 예외 처리
import org.springframework.http.HttpStatus;

@Service
@RequiredArgsConstructor
public class BootCampCommandService {

    private final BootCampRepository bootcampRepository;

    public BootCampDto save(BootCampDto bootcampDTO) {
        BootCamp bootcamp = BootCamp.of(bootcampDTO.getBootName(), bootcampDTO.getBootLocation());
        return new BootCampDto(bootcampRepository.save(bootcamp).getBootId(),
                bootcamp.getBootName(),
                bootcamp.getBootLocation());
    }

    public BootCampDto update(Long id, BootCampDto bootcampDTO) {
        BootCamp bootcamp = bootcampRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Bootcamp not found"));
        bootcamp.setBootName(bootcampDTO.getBootName());
        bootcamp.setBootLocation(bootcampDTO.getBootLocation());
        return new BootCampDto(bootcampRepository.save(bootcamp).getBootId(), bootcamp.getBootName(), bootcamp.getBootLocation());
    }

    public void delete(Long id) {
        bootcampRepository.deleteById(id);
    }
}

