package com.fivemybab.ittabab.board.command.application.service;

import com.fivemybab.ittabab.board.command.application.dto.CreatePostCommentDto;
import com.fivemybab.ittabab.board.command.application.dto.UpdatePostCommentDto;
import com.fivemybab.ittabab.board.command.application.service.PostCommentCommandService;
import com.fivemybab.ittabab.board.command.domain.aggregate.PostComment;
import com.fivemybab.ittabab.board.command.domain.repository.PostCommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PostCommentCommandServiceTest {

    @Autowired
    private PostCommentCommandService postCommentCommandService;

    @Autowired
    private PostCommentRepository postCommentRepository;

    private CreatePostCommentDto createPostCommentDto;
    private UpdatePostCommentDto updatePostCommentDto;

    @BeforeEach
    void setUp() {
        // 댓글 등록용 데이터 생성
        createPostCommentDto = new CreatePostCommentDto();
        createPostCommentDto.setPostId(1L);
        createPostCommentDto.setUserId(1L);
        createPostCommentDto.setCommentContent("테스트 댓글 내용");

        // 댓글 수정용 데이터 생성
        updatePostCommentDto = new UpdatePostCommentDto();
        updatePostCommentDto.setCommentId(1L);
        updatePostCommentDto.setCommentContent("수정된 댓글 내용");
    }

    @Test
    @DisplayName("댓글 등록 테스트")
    void createCommentTest() {
        // 댓글 등록
        postCommentCommandService.createComment(createPostCommentDto);

        // 댓글이 제대로 저장되었는지 확인
        PostComment savedComment = postCommentRepository.findAll().get(0); // 등록한 댓글이 저장된 번호
        assertNotNull(savedComment);
        assertEquals("테스트 댓글 내용", savedComment.getCommentContent());
    }

    @Test
    @DisplayName("댓글 수정 테스트")
    void updateCommentTest() {
        // 먼저 댓글을 등록
        PostComment comment = postCommentCommandService.createComment(createPostCommentDto);
        Long commentId = comment.getPostCommentId();

        // 댓글 수정
        postCommentCommandService.updateComment(updatePostCommentDto);

        // 수정이 제대로 되었는지 확인
        PostComment updatedComment = postCommentRepository.findById(commentId).orElseThrow();
        assertEquals("수정된 댓글 내용", updatedComment.getCommentContent());
    }

    @Test
    @DisplayName("댓글 삭제 테스트")
    void deleteCommentTest() {
        // 먼저 댓글을 등록
        PostComment comment = postCommentCommandService.createComment(createPostCommentDto);
        Long commentId = comment.getPostCommentId();

        // 댓글 삭제
        postCommentCommandService.deleteComment(commentId);

        // 삭제되었는지 확인
        assertFalse(postCommentRepository.existsById(commentId));
    }
}
