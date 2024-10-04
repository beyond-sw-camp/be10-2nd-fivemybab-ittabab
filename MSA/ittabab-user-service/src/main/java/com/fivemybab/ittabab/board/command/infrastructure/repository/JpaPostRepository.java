package com.fivemybab.ittabab.board.command.infrastructure.repository;

import com.fivemybab.ittabab.board.command.domain.aggregate.Post;
import com.fivemybab.ittabab.board.command.domain.repository.PostRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPostRepository extends PostRepository, JpaRepository<Post, Long> {
}
