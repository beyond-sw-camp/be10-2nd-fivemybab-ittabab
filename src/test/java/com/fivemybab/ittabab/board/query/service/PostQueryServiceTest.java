package com.fivemybab.ittabab.board.query.service;

import com.fivemybab.ittabab.board.query.dto.PostQueryDto;
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
class PostQueryServiceTest {

    @Autowired
    private PostQueryService postQueryService;

    @Test
    @DisplayName("게시물 목록 조회 - 최신순")
    void findPostsByTimeTest() throws NotFoundException {
        List<PostQueryDto> posts = postQueryService.findPostsByTime();

        // 게시물 목록이 null이 아니고, 올바르게 조회되는지 확인
        Assertions.assertNotNull(posts);
        posts.forEach(post -> System.out.println(post));
    }

    @Test
    @DisplayName("게시물 목록 조회 - 좋아요 순")
    void findPostsByLikesTest() throws NotFoundException {
        List<PostQueryDto> posts = postQueryService.findPostsByLikesDesc();

        // 게시물 목록이 null이 아니고, 올바르게 조회되는지 확인
        Assertions.assertNotNull(posts);
        posts.forEach(post -> System.out.println(post));
    }
}
