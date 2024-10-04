package com.fivemybab.ittabab.user.command.application.service;

import com.fivemybab.ittabab.exception.NotFoundException;
import com.fivemybab.ittabab.user.command.application.dto.CreateBootCampRequest;
import com.fivemybab.ittabab.user.command.application.dto.UpdateBootCampRequest;
import com.fivemybab.ittabab.user.command.domain.aggregate.BootCamp;
import com.fivemybab.ittabab.user.command.domain.repository.BootCampRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BootCampCommandService {


    private final ModelMapper modelMapper;
    private final BootCampRepository bootcampRepository;

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
        foundBootCamp.modifyLocation(updateBootCampRequest.getAddress());
    }

    @Transactional
    public void deleteBootCamp(Long id) {

        bootcampRepository.deleteById(id);
    }
}

