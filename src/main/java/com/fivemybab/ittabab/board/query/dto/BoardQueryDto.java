package com.fivemybab.ittabab.board.query.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardQueryDto {
    private Long postId;       // 게시물 ID
    private String title;      // 제목
    private String content;    // 내용
    private int likeCount;     // 좋아요 수
    private int commentCount;  // 댓글 수
    private boolean isBlinded; // 블라인드 여부
    private String createdAt;  // 생성일
    private String memberName; // 게시물 작성자 이름 추가

    // 기존 생성된 getter와 setter 메서드가 필요하다면 남겨두어도 무방합니다.
    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public boolean isBlinded() {
        return isBlinded;
    }

    public void setBlinded(boolean blinded) {
        isBlinded = blinded;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    @Override
    public String toString() {
        return "BoardQueryDto{" +
                "postId=" + postId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", likeCount=" + likeCount +
                ", commentCount=" + commentCount +
                ", isBlinded=" + isBlinded +
                ", createdAt='" + createdAt + '\'' +
                ", memberName='" + memberName + '\'' +
                '}';
    }
}
