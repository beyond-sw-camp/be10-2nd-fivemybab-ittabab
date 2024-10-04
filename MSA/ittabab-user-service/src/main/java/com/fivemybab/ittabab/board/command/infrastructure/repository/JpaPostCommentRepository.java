package com.fivemybab.ittabab.board.command.infrastructure.repository;

import com.fivemybab.ittabab.board.command.domain.aggregate.PostComment;
import com.fivemybab.ittabab.board.command.domain.repository.PostCommentRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPostCommentRepository extends PostCommentRepository, JpaRepository<PostComment, Long> {
}
