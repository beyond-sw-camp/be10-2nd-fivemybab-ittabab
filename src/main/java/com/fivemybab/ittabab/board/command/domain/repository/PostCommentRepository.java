package com.fivemybab.ittabab.board.command.domain.repository;


import com.fivemybab.ittabab.board.command.domain.aggregate.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostCommentRepository extends JpaRepository<PostComment, Long> {

    void deleteCommentByPostId(Long postId); //상속 받는거에 없어서 추가함
}
