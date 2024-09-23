package com.fivemybab.ittabab.group.command.application.mapper;


import com.fivemybab.ittabab.group.command.application.dto.GroupInfoDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GroupInfoMapper {

    /* 전체 모임 조회*/
    List<GroupInfoDTO> findGroupByGroupStatus();

    /* 특정 모임 조회 */
    GroupInfoDTO findGroupByGroupId(Long groupId);
}
