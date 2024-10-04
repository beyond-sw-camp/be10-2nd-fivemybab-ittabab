package com.fivemybab.ittabab.board.command.application.service;

import com.fivemybab.ittabab.board.command.application.dto.CreatePostDto;
import com.fivemybab.ittabab.board.command.application.dto.UpdatedPostDto;
import com.fivemybab.ittabab.board.command.domain.aggregate.Post;
import com.fivemybab.ittabab.board.command.domain.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PostCommandServiceTest {

    @Autowired
    private PostCommandService postCommandService;

    @Autowired
    private PostRepository postRepository;

    private CreatePostDto createPostDto;
    private UpdatedPostDto updatedPostDto;

    @BeforeEach
    void setUp() {
        // 게시글 등록용 데이터 생성
        createPostDto = new CreatePostDto();
        createPostDto.setPostTitle("테스트 제목");
        createPostDto.setPostContent("테스트 내용");
        createPostDto.setCreateDate(LocalDateTime.now());

        // 게시글 수정용 데이터 생성
        updatedPostDto = new UpdatedPostDto();
        updatedPostDto.setPostTitle("수정된 제목");
        updatedPostDto.setPostContent("수정된 내용");
    }



    @Test
    @DisplayName("존재하지 않는 게시글 수정 시 예외 발생 테스트")
    void updateNonExistentPostTest() {
        Long nonExistentPostId = 999L; // 실제 존재하지 않는 게시글 ID

        assertThrows(IllegalArgumentException.class, () -> {
            postCommandService.updatePost(nonExistentPostId, updatedPostDto);
        });
    }


    @Test
    @DisplayName("존재하지 않는 게시글 삭제 시 예외 발생 테스트")
    void deleteNonExistentPostTest() {
        Long nonExistentPostId = 999L; // 실제 존재하지 않는 게시글 ID

        assertThrows(IllegalArgumentException.class, () -> {
            postCommandService.deletePost(nonExistentPostId);
        });
    }

    @Test
    @DisplayName("누락된 상태로 게시글 생성 시 예외 발생 테스트")
    void createPostWithMissingFieldsTest() {
        // 필수 필드 누락된 DTO 생성
        CreatePostDto invalidCreatePostDto = new CreatePostDto();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            postCommandService.createPost(invalidCreatePostDto, 1L);
        });

        assertEquals("제목과 내용은 필수입니다.", exception.getMessage());
    }

}
