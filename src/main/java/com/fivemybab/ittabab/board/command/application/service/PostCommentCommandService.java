package com.fivemybab.ittabab.board.command.application.service;

import com.fivemybab.ittabab.board.command.application.dto.CreatePostCommentDto;
import com.fivemybab.ittabab.board.command.application.dto.UpdatePostCommentDto;
import com.fivemybab.ittabab.board.command.domain.aggregate.PostComment;
import com.fivemybab.ittabab.board.command.domain.repository.PostCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostCommentCommandService {

    private final PostCommentRepository postCommentRepository;

    // 댓글 생성 (C)
    @Transactional
    public PostComment createComment(CreatePostCommentDto createPostCommentDto) {
        PostComment postComment = PostComment.builder()
                .postId(createPostCommentDto.getPostId())
                .userId(createPostCommentDto.getUserId())
                .commentContent(createPostCommentDto.getCommentContent())
                .isBlinded(false) // 기본 값 설정
                .build();
        return postCommentRepository.save(postComment);
    }

    // 댓글 수정 (U)
    @Transactional
    public PostComment updateComment(UpdatePostCommentDto updatePostCommentDto) {
        PostComment postComment = postCommentRepository.findById(updatePostCommentDto.getCommentId())
                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다."));
        postComment.setCommentContent(updatePostCommentDto.getCommentContent());
        return postCommentRepository.save(postComment);
    }

    // 댓글 삭제 (D)
    @Transactional
    public void deleteComment(Long commentId) {
        if (!postCommentRepository.existsById(commentId)) {
            throw new IllegalArgumentException("댓글을 찾을 수 없습니다.");
        }
        postCommentRepository.deleteById(commentId);
    }

    // 게시글 ID를 기준으로 모든 댓글 삭제
    @Transactional
    public void deleteCommentsByPostId(Long postId) {
        postCommentRepository.deleteCommentByPostId(postId);
    }
}
