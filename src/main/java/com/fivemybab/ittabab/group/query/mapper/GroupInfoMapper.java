package com.fivemybab.ittabab.group.query.mapper;


import com.fivemybab.ittabab.group.query.dto.GroupInfoDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GroupInfoMapper {

    /* 전체 모임 조회*/
    List<GroupInfoDto> findGroupByGroupStatus(String loginId);

    /* 특정 모임 조회 */
    GroupInfoDto findGroupByGroupId(Long groupId);

    /* 로그인 아이디를 통한 회원 아이디 조회 */
    Long findUserIdByLoginId(String loginUserLoginId);
}
