package com.fivemybab.ittabab.board.command.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "post")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;
    private int memberId;
    private String postTitle;
    private String postContent;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private boolean isBlinded;

}
