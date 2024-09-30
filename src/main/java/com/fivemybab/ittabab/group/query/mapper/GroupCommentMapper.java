package com.fivemybab.ittabab.group.query.mapper;

import com.fivemybab.ittabab.group.query.dto.GroupCommentDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GroupCommentMapper {
    List<GroupCommentDto> findByGroupId(Long groupId);

    GroupCommentDto findByGroupCommentId(Long groupCommentId);
}
