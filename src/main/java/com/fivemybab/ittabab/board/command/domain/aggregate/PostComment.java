package com.fivemybab.ittabab.board.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "post_comment")
@Getter
@Setter // Setter 어노테이션 추가
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자 보호
public class PostComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postCommentId;

    private Long postId;
    private Long parentCommentId;
    private Long userId;
    private String commentContent;

    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    private boolean isBlinded;

    // @Builder 어노테이션과 함께 필요한 생성자 추가
    @Builder
    public PostComment(Long postId, Long parentCommentId, Long userId, String commentContent, boolean isBlinded) {
        this.postId = postId;
        this.parentCommentId = parentCommentId;
        this.userId = userId;
        this.commentContent = commentContent;
        this.isBlinded = isBlinded;
    }

    // 댓글 생성 시 자동으로 createDate 설정
    @PrePersist
    protected void onCreate() {
        this.createDate = LocalDateTime.now();
    }

    // 댓글 수정 시 자동으로 updateDate 설정
    @PreUpdate
    protected void onUpdate() {
        this.updateDate = LocalDateTime.now();
    }
}
