package com.fivemybab.ittabab.group.query.service;

import com.fivemybab.ittabab.group.query.dto.GroupCommentDto;
import com.fivemybab.ittabab.group.query.mapper.GroupCommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupCommentQueryService {

    private final GroupCommentMapper mapper;

    public List<GroupCommentDto> findByGroupId(Long groupId) {
        return mapper.findByGroupId(groupId);
    }

    public GroupCommentDto findByGroupCommentId(Long groupCommentId) {
        return mapper.findByGroupCommentId(groupCommentId);
    }
}
