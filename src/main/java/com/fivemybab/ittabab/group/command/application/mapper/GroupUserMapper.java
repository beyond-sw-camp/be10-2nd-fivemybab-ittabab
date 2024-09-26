package com.fivemybab.ittabab.group.command.application.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GroupUserMapper {

    /* GroupId를 입력받아 모임에 속한 사용자들을 반환 */
    List<Long> findUserByGroupId(Long groupId);
}
