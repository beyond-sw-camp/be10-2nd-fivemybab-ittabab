package com.fivemybab.ittabab.board.query.service;

import com.fivemybab.ittabab.board.query.dto.PostCommentQueryDto;
import org.apache.ibatis.javassist.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class PostCommentQueryServiceTest {

    @Autowired
    private PostCommentQueryService postCommentQueryService;

    @Test
    @DisplayName("특정 게시물의 댓글 목록 조회")
    void findCommentsByPostIdTest() throws NotFoundException {
        Long postId = 1L; // 테스트할 게시물 ID

        List<PostCommentQueryDto> comments = postCommentQueryService.findPostsByCommentCount();

        // 조회된 댓글 리스트가 null이 아니고, 올바르게 조회되는지 확인
        Assertions.assertNotNull(comments);
        comments.forEach(comment -> {
            Assertions.assertEquals(postId, comment.getPostId());
            System.out.println(comment);
        });
    }
}
