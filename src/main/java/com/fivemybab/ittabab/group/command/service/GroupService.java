package com.fivemybab.ittabab.group.command.service;

import com.fivemybab.ittabab.group.command.dto.GroupCommentDTO;
import com.fivemybab.ittabab.group.command.dto.GroupInfoDTO;
import com.fivemybab.ittabab.group.command.mapper.GroupCommentMapper;
import com.fivemybab.ittabab.group.command.mapper.GroupInfoMapper;
import com.fivemybab.ittabab.group.command.repository.GroupInfoRepository;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    private final SqlSessionTemplate session;
    private final GroupInfoRepository repository;

    @Autowired
    public GroupService(SqlSessionTemplate session, GroupInfoRepository repository) {
        this.session = session;
        this.repository = repository;
    }

    public List<GroupInfoDTO> findGroupByGroupStatus() {
        return session.getMapper(GroupInfoMapper.class).findGroupByGroupStatus();
    }

    public GroupInfoDTO findGroupByGroupId(Integer groupId) {
        return session.getMapper(GroupInfoMapper.class).findGroupByGroupId(groupId);
    }

    public void registerGroupMember(Integer groupId, Integer memberId) {

    }

    public List<GroupCommentDTO> findGroupCommentsByGroupId(Integer groupId) {
        List<GroupCommentDTO> commentList = session.getMapper(GroupCommentMapper.class).findGroupCommentsByGroupId(groupId);

        return commentList;
    }

}
