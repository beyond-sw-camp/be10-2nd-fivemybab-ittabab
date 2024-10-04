package com.fivemybab.ittabab.user.query.service;

import com.fivemybab.ittabab.user.query.dto.BootCampDto;
import com.fivemybab.ittabab.user.query.mapper.BootCampMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BootCampQueryService {

    private final BootCampMapper bootCampMapper;

    @Transactional(readOnly = true)
    public List<BootCampDto> findBootCampList() {
        return bootCampMapper.findBootCampList();
    }
}
