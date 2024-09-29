package com.fivemybab.ittabab.board.query.service;

import com.fivemybab.ittabab.board.query.dto.BoardCommentQueryDto;
import com.fivemybab.ittabab.board.query.mapper.BoardCommentQueryMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardCommentQueryService {

    private final BoardCommentQueryMapper boardCommentQueryMapper;

    /* 댓글 많은 순으로 게시글 목록 조회 */
    @Transactional(readOnly = true)
    public List<BoardCommentQueryDto> findPostsByCommentCount() throws NotFoundException {
        List<BoardCommentQueryDto> posts = boardCommentQueryMapper.selectPostsByCommentCount();

        if (posts.isEmpty()) {
            throw new NotFoundException("게시물이 없습니다.");
        }

        return posts;
    }
}
