package com.fivemybab.ittabab.member.query.mapper;

import com.fivemybab.ittabab.member.command.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberMapper {
    MemberDTO findById(@Param("id") Long id);

    List<MemberDTO> findAll();
}
