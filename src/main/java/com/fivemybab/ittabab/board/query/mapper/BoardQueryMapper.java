package com.fivemybab.ittabab.board.query.mapper;

import com.fivemybab.ittabab.board.query.dto.BoardQueryDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardQueryMapper {
    List<BoardQueryDto> findAllPosts(); // 모든 게시물 조회
    BoardQueryDto findPostById(Long postId); // 게시물 ID로 조회
}
