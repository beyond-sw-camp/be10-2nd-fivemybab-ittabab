package com.fivemybab.ittabab.group.query.service;

import com.fivemybab.ittabab.group.query.dto.GroupCommentDto;
import com.fivemybab.ittabab.group.query.dto.GroupInfoDto;
import com.fivemybab.ittabab.group.query.mapper.GroupCommentMapper;
import com.fivemybab.ittabab.group.query.mapper.GroupInfoMapper;
import com.fivemybab.ittabab.group.query.mapper.GroupUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupInfoQueryService {

    private final GroupInfoMapper groupInfoMapper;
    private final GroupUserMapper groupUserMapper;
    private final GroupCommentMapper groupCommentMapper;

    public List<GroupInfoDto> findGroupByGroupStatus(String loginId) {
        Long courseId = findCourseIdByLoginId(loginId);
        return groupInfoMapper.findGroupByGroupStatus(loginId, courseId);
    }

    public GroupInfoDto findGroupByGroupId(Long groupId) {
        return groupInfoMapper.findGroupByGroupId(groupId);
    }

    /* 로그인 ID -> 유저 ID */
    public Long loginIdToUserId(String loginUserLoginId) {
        return groupInfoMapper.findUserIdByLoginId(loginUserLoginId);
    }

    /* 모임에 가입된 사용자 아이디 가져오는 메소드 */
    public List<Long> findGroupUserByGroupId(Long groupId) {
        return groupUserMapper.findUserByGroupId(groupId);
    }

    /* 로그인 Id("test01")를 사용하여 유저 정보 알아오는 메소드 */
    public Long findCourseIdByLoginId(String loginId) {
        return groupInfoMapper.findCourseIdByLoginId(loginId);
    }
}
