package com.fivemybab.ittabab.board.command.domain.repository;

import com.fivemybab.ittabab.board.command.domain.aggregate.PostComment;

import java.util.Optional;

public interface PostCommentRepository {

    Optional<PostComment> findById(Long targetId);
}
