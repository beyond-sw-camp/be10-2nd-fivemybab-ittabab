package com.fivemybab.ittabab.board.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "post")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId; // 게시글 번호
    private Long userId; // 작성자 번호
    private String postTitle; // 게시글 제목
    private String postContent; // 게시글 내용
    private LocalDateTime createDate; // 작성 일시
    private LocalDateTime updateDate; // 수정 일시
    private boolean isBlinded; // 블라인드 여부

  @Builder
    public Post(String postTitle, String postContent, boolean isBlinded, LocalDateTime createDate,Long userId){
      this.postTitle = postTitle;
      this.postContent = postContent;
      this.isBlinded = isBlinded;
      this.createDate = createDate;
      this.userId = userId;
  }

}
