package com.fivemybab.ittabab.board.query.service;

import com.fivemybab.ittabab.board.command.domain.aggregate.PostComment;
import com.fivemybab.ittabab.board.command.domain.repository.PostCommentRepository;
import com.fivemybab.ittabab.board.query.dto.PostCommentQueryDto;
import org.apache.ibatis.javassist.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PostCommentQueryServiceTest {

    @Autowired
    private PostCommentQueryService postCommentQueryService;

    @Autowired
    private PostCommentRepository postCommentRepository;

    @BeforeEach
    void setUp() {
        // 테스트 데이터 초기화
        postCommentRepository.deleteAll();

        // 고정된 postId로 댓글 데이터 생성
        PostComment postComment = PostComment.builder()
                .postId(5L) // 고정된 postId 값 사용
                .userId(1L)
                .commentContent("Test comment")
                .isBlinded(false) // 기본 값 설정
                .build();

        postCommentRepository.save(postComment);
    }

    @Test
    @DisplayName("댓글 많은 순으로 게시물 목록 조회")
    void findPostsByCommentCountTest() throws NotFoundException {
        List<PostCommentQueryDto> posts = postCommentQueryService.findPostsByCommentCount();

        // 조회된 게시물 리스트가 null이 아니고, 올바르게 조회되는지 확인
        assertNotNull(posts);
        assertFalse(posts.isEmpty(), "게시물 목록이 비어있지 않아야 합니다.");
        posts.forEach(post -> {
            assertNotNull(post.getPostId(), "게시물의 ID가 null이 아니어야 합니다.");
            System.out.println(post);
        });
    }


}
