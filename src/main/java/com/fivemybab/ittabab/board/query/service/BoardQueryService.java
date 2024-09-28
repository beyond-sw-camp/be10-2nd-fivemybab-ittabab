package com.fivemybab.ittabab.board.query.service;

import com.fivemybab.ittabab.board.query.dto.BoardQueryDto;
import com.fivemybab.ittabab.board.query.mapper.BoardQueryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardQueryService {

    private final BoardQueryMapper boardQueryMapper;

    /* 게시물 목록 조회 (최신순) */
    @Transactional(readOnly = true)
    public List<BoardQueryDto> findPostsByTime() throws NotFoundException {
        List<BoardQueryDto> posts = boardQueryMapper.selectPostsByTime();

        if (posts.isEmpty()) {
            throw new NotFoundException("게시물이 없습니다.");
        }

        return posts;
    }

    /* 게시물 목록 조회 (좋아요 내림차순) */
    @Transactional(readOnly = true)
    public List<BoardQueryDto> findPostsByLikesDesc() throws NotFoundException {
        List<BoardQueryDto> posts = boardQueryMapper.selectPostsByLikesDesc();

        if (posts.isEmpty()) {
            throw new NotFoundException("게시물이 없습니다.");
        }

        return posts;
    }

    /* 게시물 목록 조회 (좋아요 오름차순) */
    @Transactional(readOnly = true)
    public List<BoardQueryDto> findPostsByLikesAsc() throws NotFoundException {
        List<BoardQueryDto> posts = boardQueryMapper.selectPostsByLikesAsc();

        if (posts.isEmpty()) {
            throw new NotFoundException("게시물이 없습니다.");
        }

        return posts;
    }

    /* 게시물 목록 조회 (댓글 많은 순) */
    @Transactional(readOnly = true)
    public List<BoardQueryDto> findPostsByComments() throws NotFoundException {
        List<BoardQueryDto> posts = boardQueryMapper.selectPostsByComments();

        if (posts.isEmpty()) {
            throw new NotFoundException("게시물이 없습니다.");
        }

        return posts;
    }
}
