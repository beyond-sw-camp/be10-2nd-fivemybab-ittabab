package com.fivemybab.ittabab.board.query.service;

import com.fivemybab.ittabab.board.query.dto.PostQueryDto;
import com.fivemybab.ittabab.board.query.mapper.PostQueryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostQueryService {

    private final PostQueryMapper postQueryMapper;

    /* 게시물 목록 조회 (최신순) */
    @Transactional(readOnly = true)
    public List<PostQueryDto> findPostsByTime() throws NotFoundException {
        List<PostQueryDto> posts = postQueryMapper.selectPostsByTime();

        if (posts.isEmpty()) {
            throw new NotFoundException("게시물이 없습니다.");
        }

        return posts;
    }

    /* 게시물 목록 조회 (좋아요 내림차순) */
    @Transactional(readOnly = true)
    public List<PostQueryDto> findPostsByLikesDesc() throws NotFoundException {
        List<PostQueryDto> posts = postQueryMapper.selectPostsByLikesDesc();

        if (posts.isEmpty()) {
            throw new NotFoundException("게시물이 없습니다.");
        }

        return posts;
    }

    /* 게시물 목록 조회 (좋아요 오름차순) */
    @Transactional(readOnly = true)
    public List<PostQueryDto> findPostsByLikesAsc() throws NotFoundException {
        List<PostQueryDto> posts = postQueryMapper.selectPostsByLikesAsc();

        if (posts.isEmpty()) {
            throw new NotFoundException("게시물이 없습니다.");
        }

        return posts;
    }


}
