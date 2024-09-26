package com.fivemybab.ittabab.bootcamp.command.mapper;

import com.fivemybab.ittabab.bootcamp.command.dto.BootCampDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BootCampMapper {

    List<BootCampDTO> selectAllBootcamp();

    // 부트캠프 등록
    void insertBootCamp(BootCampDTO bootCampDTO);

    // 부트캠프 수정
    void updateBootCamp(BootCampDTO bootCampDTO);

    // 부트캠프 삭제
    void deleteBootCamp(Long bootId);
}

