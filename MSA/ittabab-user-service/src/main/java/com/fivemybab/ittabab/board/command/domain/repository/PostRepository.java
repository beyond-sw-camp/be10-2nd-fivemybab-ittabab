package com.fivemybab.ittabab.board.command.domain.repository;

import com.fivemybab.ittabab.board.command.domain.aggregate.Post;

import java.util.Optional;

public interface PostRepository {

    Optional<Post> findById(Long targetId);
}
