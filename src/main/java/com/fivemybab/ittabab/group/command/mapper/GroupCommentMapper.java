package com.fivemybab.ittabab.group.command.mapper;

import com.fivemybab.ittabab.group.command.dto.GroupCommentDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GroupCommentMapper {
    List<GroupCommentDTO> findGroupCommentsByGroupId(Integer groupId);
}
