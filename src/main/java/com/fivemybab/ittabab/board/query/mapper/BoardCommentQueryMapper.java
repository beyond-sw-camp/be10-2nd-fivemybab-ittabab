package com.fivemybab.ittabab.board.query.mapper;

import com.fivemybab.ittabab.board.query.dto.BoardCommentQueryDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardCommentQueryMapper {
    // 댓글 많은 순으로 게시글 목록 조회
    List<BoardCommentQueryDto> selectPostsByCommentCount();
}
