package com.fivemybab.ittabab.user.query.mapper;

import com.fivemybab.ittabab.user.query.dto.BootCampDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BootCampMapper {

    List<BootCampDto> findBootCampList();
}

