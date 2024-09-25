package com.fivemybab.ittabab.board.command.domain.repository;

import com.fivemybab.ittabab.board.command.domain.aggregate.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Post save(Post post);
    Optional<Post>findById(Long postId);
    void deleteById(Long postId);
}
