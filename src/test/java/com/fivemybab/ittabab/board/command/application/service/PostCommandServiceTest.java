package com.fivemybab.ittabab.board.command.application.service;

import com.fivemybab.ittabab.board.command.application.dto.CreatePostDto;
import com.fivemybab.ittabab.board.command.application.dto.UpdatedPostDto;
import com.fivemybab.ittabab.board.command.application.service.PostCommandService;
import com.fivemybab.ittabab.board.command.domain.aggregate.Post;
import com.fivemybab.ittabab.board.command.domain.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
    @DisplayName("게시글 등록 테스트")
    void createPostTest() {
        // 게시글 등록
        postCommandService.createPost(createPostDto, 1L);

        // 게시글이 제대로 저장되었는지 확인
        Post savedPost = postRepository.findAll().get(0); // 등록한 게시글이 저장된 번호
        assertNotNull(savedPost);
        assertEquals("테스트 제목", savedPost.getPostTitle());
        assertEquals("테스트 내용", savedPost.getPostContent());
    }

    @Test
    @DisplayName("게시글 수정 테스트")
    void updatePostTest() {
        // 먼저 게시글을 등록
        CreatePostDto post = postCommandService.createPost(createPostDto, 1L);
        Long postId = post.getPostId();

        // 게시글 수정
        postCommandService.updatePost(postId, updatedPostDto);

        // 수정이 제대로 되었는지 확인
        Post updatedPost = postRepository.findById(postId).orElseThrow();
        assertEquals("수정된 제목", updatedPost.getPostTitle());
        assertEquals("수정된 내용", updatedPost.getPostContent());
    }

    @Test
    @DisplayName("게시글 삭제 테스트")
    void deletePostTest() {
        // 먼저 게시글을 등록
        CreatePostDto post = postCommandService.createPost(createPostDto, 1L);
        Long postId = post.getPostId();

        // 게시글 삭제
        postCommandService.deletePost(postId);

        // 삭제되었는지 확인
        assertFalse(postRepository.existsById(postId));
    }
}
