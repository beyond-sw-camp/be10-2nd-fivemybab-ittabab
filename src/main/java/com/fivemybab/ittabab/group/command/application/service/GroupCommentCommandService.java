package com.fivemybab.ittabab.group.command.application.service;

import com.fivemybab.ittabab.group.command.domain.aggregate.GroupComment;
import com.fivemybab.ittabab.group.command.domain.repository.GroupCommentRepository;
import com.fivemybab.ittabab.group.query.dto.GroupCommentDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupCommentCommandService {

    private final GroupCommentRepository repository;
    private final ModelMapper modelMapper;

    @Transactional
    public void registerComment(GroupCommentDto newComment) {
        repository.save(modelMapper.map(newComment, GroupComment.class));
    }

    @Transactional
    public void deleteByGroupCommentId(Long groupCommentId) {
        repository.deleteById(groupCommentId);
    }

    @Transactional
    public void modifyGroupComment(GroupCommentDto modifyComment) {
        System.out.println("modifyComment.getGroupCommentId() = " + modifyComment.getGroupCommentId());
        GroupComment foundGroupComment = repository.findById(modifyComment.getGroupCommentId()).orElse(null);
        if(foundGroupComment == null) {
            System.out.println("null!!!!!");
        }
        foundGroupComment.modifyCommentContent(modifyComment.getCommentContent());
        foundGroupComment.modifyUpdateDate(modifyComment.getUpdateDate());
    }
}
