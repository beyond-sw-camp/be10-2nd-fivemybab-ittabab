package com.fivemybab.ittabab.board.query.mapper;

import com.fivemybab.ittabab.board.query.dto.BoardQueryDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardQueryMapper {
    // 최신순으로 게시물 목록 조회
    List<BoardQueryDto> selectPostsByTime();

    // 좋아요가 가장 많은 게시물 목록 조회
    List<BoardQueryDto> selectPostsByLikesDesc();

    // 좋아요가 가장 적은 게시물 목록 조회
    List<BoardQueryDto> selectPostsByLikesAsc();


}
