package com.fivemybab.ittabab.board.command.application.service;

import com.fivemybab.ittabab.board.command.application.dto.CreatePostCommentDto;
import com.fivemybab.ittabab.board.command.application.dto.UpdatePostCommentDto;
import com.fivemybab.ittabab.board.command.domain.aggregate.PostComment;
import com.fivemybab.ittabab.board.command.domain.repository.PostCommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PostCommentCommandServiceTest {

    @Autowired
    private PostCommentCommandService postCommentCommandService;

    @Autowired
    private PostCommentRepository postCommentRepository;

    private Long validPostId;
    private Long validUserId;

    @BeforeEach
    void setUp() {
        // 테스트용 유효한 postId 및 userId 설정 (DB에 해당 데이터가 있어야 함)
        validPostId = 5L; // 실제 테스트 시 유효한 게시글 ID를 설정해야 함
        validUserId = 5L; // 실제 테스트 시 유효한 사용자 ID를 설정해야 함
    }

    @Test
    @DisplayName("댓글 생성 테스트")
    void createCommentTest() {
        CreatePostCommentDto createDto = new CreatePostCommentDto();
        createDto.setPostId(validPostId);
        createDto.setUserId(validUserId);
        createDto.setCommentContent("테스트 댓글 내용");

        PostComment createdComment = postCommentCommandService.createComment(createDto);

        assertNotNull(createdComment);
        assertEquals(validPostId, createdComment.getPostId());
        assertEquals(validUserId, createdComment.getUserId());
        assertEquals("테스트 댓글 내용", createdComment.getCommentContent());
    }

    @Test
    @DisplayName("필수 필드 누락된 상태로 댓글 생성 시 예외 발생 테스트")
    void createCommentWithMissingFieldsTest() {
        CreatePostCommentDto invalidCreateDto = new CreatePostCommentDto();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            postCommentCommandService.createComment(invalidCreateDto);
        });

        assertEquals("댓글 내용과 게시글 ID, 사용자 ID는 필수입니다.", exception.getMessage());
    }

    @Test
    @DisplayName("댓글 수정 테스트")
    void updateCommentTest() {
        // 먼저 댓글을 생성
        CreatePostCommentDto createDto = new CreatePostCommentDto();
        createDto.setPostId(validPostId);
        createDto.setUserId(validUserId);
        createDto.setCommentContent("테스트 댓글 내용");
        PostComment createdComment = postCommentCommandService.createComment(createDto);

        // 생성된 댓글을 수정
        UpdatePostCommentDto updateDto = new UpdatePostCommentDto();
        updateDto.setCommentId(createdComment.getPostCommentId());
        updateDto.setCommentContent("수정된 댓글 내용");

        PostComment updatedComment = postCommentCommandService.updateComment(updateDto);

        assertNotNull(updatedComment);
        assertEquals("수정된 댓글 내용", updatedComment.getCommentContent());
    }

    @Test
    @DisplayName("댓글 수정 시 필수 필드 누락 예외 테스트")
    void updateCommentWithMissingFieldsTest() {
        UpdatePostCommentDto updateDto = new UpdatePostCommentDto(); // 필수 필드인 commentId 누락

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            postCommentCommandService.updateComment(updateDto);
        });

        assertEquals("댓글 ID는 필수입니다.", exception.getMessage());
    }

    @Test
    @DisplayName("존재하지 않는 댓글 수정 예외 테스트")
    void updateNonExistentCommentTest() {
        UpdatePostCommentDto updateDto = new UpdatePostCommentDto();
        updateDto.setCommentId(999L);
        updateDto.setCommentContent("수정된 내용");

        assertThrows(IllegalArgumentException.class, () -> postCommentCommandService.updateComment(updateDto));
    }

    @Test
    @DisplayName("댓글 삭제 테스트")
    void deleteCommentTest() {
        // 먼저 댓글을 생성
        CreatePostCommentDto createDto = new CreatePostCommentDto();
        createDto.setPostId(validPostId);
        createDto.setUserId(validUserId);
        createDto.setCommentContent("테스트 댓글 내용");
        PostComment createdComment = postCommentCommandService.createComment(createDto);

        // 생성된 댓글을 삭제
        assertDoesNotThrow(() -> postCommentCommandService.deleteComment(createdComment.getPostCommentId()));

        // 댓글이 삭제되었는지 확인
        assertFalse(postCommentRepository.existsById(createdComment.getPostCommentId()));
    }

    @Test
    @DisplayName("존재하지 않는 댓글 삭제 예외 테스트")
    void deleteNonExistentCommentTest() {
        assertThrows(IllegalArgumentException.class, () -> postCommentCommandService.deleteComment(999L));
    }


}
