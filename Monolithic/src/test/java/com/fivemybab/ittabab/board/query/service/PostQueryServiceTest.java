package com.fivemybab.ittabab.board.query.service;

import com.fivemybab.ittabab.board.query.dto.PostQueryDto;
import com.fivemybab.ittabab.security.util.CustomUserDetails;
import org.apache.ibatis.javassist.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class PostQueryServiceTest {

    @Autowired
    private PostQueryService postQueryService;

    @Test
    @DisplayName("게시물 목록 조회 - 최신순")
    @WithMockUser(username = "testUser", roles = {"USER"})
    void findPostsByTimeTest(@AuthenticationPrincipal CustomUserDetails loginUser) throws NotFoundException {
        List<PostQueryDto> posts = postQueryService.findPostsByTime(loginUser);

        // 게시물 목록이 null이 아니고, 올바르게 조회되는지 확인
        Assertions.assertNotNull(posts);
        posts.forEach(System.out::println);
    }

    @Test
    @DisplayName("게시물 목록 조회 - 좋아요 순")
    @WithMockUser(username = "testUser", roles = {"USER"})
    void findPostsByLikesTest(@AuthenticationPrincipal CustomUserDetails loginUser) throws NotFoundException {
        List<PostQueryDto> posts = postQueryService.findPostsByLikesDesc(loginUser);

        // 게시물 목록이 null이 아니고, 올바르게 조회되는지 확인
        Assertions.assertNotNull(posts);
        posts.forEach(System.out::println);
    }
}
