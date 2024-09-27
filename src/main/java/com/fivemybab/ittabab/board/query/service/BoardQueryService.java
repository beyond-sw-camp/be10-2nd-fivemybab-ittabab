package com.fivemybab.ittabab.board.query.service;

import com.fivemybab.ittabab.board.query.dto.BoardQueryDto;
import com.fivemybab.ittabab.board.query.mapper.BoardQueryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardQueryService {

    private final BoardQueryMapper boardQueryMapper;
    private final ModelMapper modelMapper;

    /* 모든 게시물 조회 */
    @Transactional(readOnly = true)
    public List<BoardQueryDto> findAllPosts() throws NotFoundException {
        List<BoardQueryDto> posts = boardQueryMapper.findAllPosts();

        if (posts.isEmpty()) {
            throw new NotFoundException("게시물이 없습니다.");
        }

        return posts;
    }

    /* 게시물 ID로 조회 */
    @Transactional(readOnly = true)
    public BoardQueryDto findPostById(Long postId) throws NotFoundException {
        BoardQueryDto post = boardQueryMapper.findPostById(postId);

        if (post == null) {
            log.warn("게시물 ID {}에 대한 게시물이 없습니다.", postId);
            throw new NotFoundException("게시물 ID " + postId + "에 대한 게시물이 없습니다.");
        } else {
            log.info("조회된 게시물: {}", post);
        }

        return post;
    }
}
