package com.fivemybab.ittabab.group.command.application.service;

import com.fivemybab.ittabab.group.command.application.dto.GroupCommentDto;
import com.fivemybab.ittabab.group.command.application.dto.GroupInfoDto;
import com.fivemybab.ittabab.group.command.application.mapper.GroupCommentMapper;
import com.fivemybab.ittabab.group.command.application.mapper.GroupInfoMapper;
import com.fivemybab.ittabab.group.command.application.repository.GroupInfoRepository;
import com.fivemybab.ittabab.group.command.domain.entity.GroupInfo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final SqlSessionTemplate session;
    private final GroupInfoRepository repository;
    private final ModelMapper modelMapper;

    public List<GroupInfoDto> findGroupByGroupStatus(String loginId) {
        return session.getMapper(GroupInfoMapper.class).findGroupByGroupStatus(loginId);
    }

    public GroupInfoDto findGroupByGroupId(Long groupId) {
        return session.getMapper(GroupInfoMapper.class).findGroupByGroupId(groupId);
    }

    public void registerGroupUser(Long groupId, Long userId) {

    }

    public List<GroupCommentDto> findGroupCommentsByGroupId(Long groupId) {
        List<GroupCommentDto> commentList = session.getMapper(GroupCommentMapper.class).findGroupCommentsByGroupId(groupId);

        return commentList;
    }

    /* 모임 등록 */
    @Transactional
    public void registGroup(GroupInfoDto newGroupInfo) {
        repository.save(modelMapper.map(newGroupInfo, GroupInfo.class));
    }

    public Long loginIdToUserId(String loginUserLoginId) {
        return session.getMapper(GroupInfoMapper.class).findUserIdByLoginId(loginUserLoginId);
    }
}
