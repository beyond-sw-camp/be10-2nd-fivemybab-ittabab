package com.fivemybab.ittabab.board.query.controller;

import com.fivemybab.ittabab.board.query.dto.PostQueryDto;
import com.fivemybab.ittabab.board.query.service.PostQueryService;
import com.fivemybab.ittabab.security.util.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostQueryController {

    private final PostQueryService postQueryService;

    // 게시물 목록 조회 (최신순)
    @GetMapping("/recent")
    public List<PostQueryDto> findPostsByTime(@AuthenticationPrincipal CustomUserDetails loginUser) throws NotFoundException {
        return postQueryService.findPostsByTime(loginUser);
    }

    // 게시물 목록 조회 (좋아요 내림차순)
    @GetMapping("/likes/desc")
    public List<PostQueryDto> findPostsByLikesDesc(@AuthenticationPrincipal CustomUserDetails loginUser) throws NotFoundException {
        return postQueryService.findPostsByLikesDesc(loginUser);
    }

    // 게시물 목록 조회 (좋아요 오름차순)
    @GetMapping("/likes/asc")
    public List<PostQueryDto> findPostsByLikesAsc(@AuthenticationPrincipal CustomUserDetails loginUser) throws NotFoundException {
        return postQueryService.findPostsByLikesAsc(loginUser);
    }


}
