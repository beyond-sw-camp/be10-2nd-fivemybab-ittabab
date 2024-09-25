package com.fivemybab.ittabab.group.command.application.service;

import com.fivemybab.ittabab.group.command.application.dto.GroupCommentDto;
import com.fivemybab.ittabab.group.command.application.dto.GroupInfoDto;
import com.fivemybab.ittabab.group.command.application.mapper.GroupCommentMapper;
import com.fivemybab.ittabab.group.command.application.mapper.GroupInfoMapper;
import com.fivemybab.ittabab.group.command.application.repository.GroupInfoRepository;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final SqlSessionTemplate session;
    private final GroupInfoRepository groupInfoRepository;

    public List<GroupInfoDto> findGroupByGroupStatus(Long courseId) {
        return session.getMapper(GroupInfoMapper.class).findGroupByGroupStatus(courseId);
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

}
