package com.fivemybab.ittabab.board.command.domain.repository;

import com.fivemybab.ittabab.board.command.domain.aggregate.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
