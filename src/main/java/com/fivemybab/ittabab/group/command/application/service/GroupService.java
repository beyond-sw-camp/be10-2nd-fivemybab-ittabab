package com.fivemybab.ittabab.group.command.application.service;

import com.fivemybab.ittabab.group.command.application.dto.GroupCommentDto;
import com.fivemybab.ittabab.group.command.application.dto.GroupInfoDto;
import com.fivemybab.ittabab.group.command.application.dto.GroupUserDto;
import com.fivemybab.ittabab.group.command.application.mapper.GroupCommentMapper;
import com.fivemybab.ittabab.group.command.application.mapper.GroupInfoMapper;
import com.fivemybab.ittabab.group.command.application.mapper.GroupUserMapper;
import com.fivemybab.ittabab.group.command.application.repository.GroupInfoRepository;
import com.fivemybab.ittabab.group.command.application.repository.GroupUserRepository;
import com.fivemybab.ittabab.group.command.domain.aggregate.GroupInfo;
import com.fivemybab.ittabab.group.command.domain.aggregate.GroupUser;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupInfoMapper groupInfoMapper;
    private final GroupUserMapper groupUserMapper;
    private final GroupCommentMapper groupCommentMapper;
    private final GroupInfoRepository groupInfoRepository;
    private final GroupUserRepository groupUserRepository;
    private final ModelMapper modelMapper;

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

    /* 모임 등록 */
    @Transactional
    public void registGroup(GroupInfoDto newGroupInfo) {
        groupInfoRepository.save(modelMapper.map(newGroupInfo, GroupInfo.class));
    }

    /* 로그인 ID -> 유저 ID */
    public Long loginIdToUserId(String loginUserLoginId) {
        return groupInfoMapper.findUserIdByLoginId(loginUserLoginId);
    }

    /* 모임 삭제 */
    @Transactional
    public void deleteGroupInfo(Long groupId) {
        groupInfoRepository.deleteById(groupId);
    }

    /* 모임에 가입된 사용자 아이디 가져오는 메소드 */
    public List<Long> findGroupUserByGroupId(Long groupId) {
        return groupUserMapper.findUserByGroupId(groupId);
    }

    /* 모임에 신규 사용자 가입 메소드 */
    public void registGroupUser(Long userId, Long groupId) {
        GroupUserDto newGroupUser = new GroupUserDto(null, userId, groupId);

        groupUserRepository.save(modelMapper.map(newGroupUser, GroupUser.class));
    }
}
