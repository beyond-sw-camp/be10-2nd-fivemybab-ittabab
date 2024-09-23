package com.fivemybab.ittabab.group.command.application.service;

import com.fivemybab.ittabab.group.command.application.dto.GroupCommentDTO;
import com.fivemybab.ittabab.group.command.application.dto.GroupInfoDTO;
import com.fivemybab.ittabab.group.command.application.mapper.GroupCommentMapper;
import com.fivemybab.ittabab.group.command.application.mapper.GroupInfoMapper;
import com.fivemybab.ittabab.group.command.application.repository.GroupInfoRepository;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    private final SqlSessionTemplate session;
    private final GroupInfoRepository groupInfoRepository;

    @Autowired
    public GroupService(SqlSessionTemplate session, GroupInfoRepository repository) {
        this.session = session;
        this.groupInfoRepository = repository;
    }

    public List<GroupInfoDTO> findGroupByGroupStatus() {
        return session.getMapper(GroupInfoMapper.class).findGroupByGroupStatus();
    }

    public GroupInfoDTO findGroupByGroupId(Long groupId) {
        return session.getMapper(GroupInfoMapper.class).findGroupByGroupId(groupId);
    }

    public void registerGroupUser(Long groupId, Long userId) {

    }

    public List<GroupCommentDTO> findGroupCommentsByGroupId(Long groupId) {
        List<GroupCommentDTO> commentList = session.getMapper(GroupCommentMapper.class).findGroupCommentsByGroupId(groupId);

        return commentList;
    }

}
