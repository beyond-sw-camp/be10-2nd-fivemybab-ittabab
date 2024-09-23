package com.fivemybab.ittabab.group.command.application.mapper;

import com.fivemybab.ittabab.group.command.application.dto.GroupCommentDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GroupCommentMapper {
    List<GroupCommentDTO> findGroupCommentsByGroupId(Long groupId);
}
