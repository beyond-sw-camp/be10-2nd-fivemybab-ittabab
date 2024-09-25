package com.fivemybab.ittabab.group.command.application.mapper;


import com.fivemybab.ittabab.group.command.application.dto.GroupInfoDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GroupInfoMapper {

    /* 전체 모임 조회*/
    List<GroupInfoDto> findGroupByGroupStatus(String loginId);

    /* 특정 모임 조회 */
    GroupInfoDto findGroupByGroupId(Long groupId);
}
