package com.fivemybab.ittabab.group.query.Service;

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
public class GroupQueryService {

    private final GroupInfoMapper groupInfoMapper;
    private final GroupUserMapper groupUserMapper;
    private final GroupCommentMapper groupCommentMapper;

    public List<GroupInfoDto> findGroupByGroupStatus(String loginId) {
        return groupInfoMapper.findGroupByGroupStatus(loginId);
    }

    public GroupInfoDto findGroupByGroupId(Long groupId) {
        return groupInfoMapper.findGroupByGroupId(groupId);
    }

    public List<GroupCommentDto> findGroupCommentsByGroupId(Long groupId) {
        List<GroupCommentDto> commentList = groupCommentMapper.findGroupCommentsByGroupId(groupId);

        return commentList;
    }

    /* 로그인 ID -> 유저 ID */
    public Long loginIdToUserId(String loginUserLoginId) {
        return groupInfoMapper.findUserIdByLoginId(loginUserLoginId);
    }

    /* 모임에 가입된 사용자 아이디 가져오는 메소드 */
    public List<Long> findGroupUserByGroupId(Long groupId) {
        return groupUserMapper.findUserByGroupId(groupId);
    }
}
