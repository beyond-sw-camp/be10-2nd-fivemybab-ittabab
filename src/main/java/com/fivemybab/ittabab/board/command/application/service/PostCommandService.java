package com.fivemybab.ittabab.board.command.application.service;

import com.fivemybab.ittabab.board.command.application.dto.CreatePostDto;
import com.fivemybab.ittabab.board.command.application.dto.UpdatedPostDto;
import com.fivemybab.ittabab.board.command.domain.aggregate.Post;
import com.fivemybab.ittabab.board.command.domain.repository.PostCommentRepository;
import com.fivemybab.ittabab.board.command.domain.repository.PostRepository;
import com.fivemybab.ittabab.user.command.domain.aggregate.UserInfo;
import com.fivemybab.ittabab.user.command.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostCommandService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostCommentRepository postCommentRepository;
    private final ModelMapper modelMapper;



    @Transactional // create 서비스 구문
    public CreatePostDto createPost(CreatePostDto createPostDto, Long userId) {
        //log.info("createBoardDTO : {}, userId : {}", createBoardDTO,userId);
        UserInfo user = userRepository.findByUserId(userId);  // 작성자 조회
        if (user == null) {
            throw new IllegalArgumentException("회원 정보를 찾을 수 없습니다.");
        }

        //게시판 글 객체 생성
        Post post = Post.builder()
                .postTitle(createPostDto.getPostTitle())
                .postContent(createPostDto.getPostContent())
                .isBlinded(false) //true? false?
                .createDate(LocalDateTime.now())
                .userId(userId)
                .build();
        postRepository.save(post);
        return createPostDto;
    }

    // 모든 게시판 조회는 마이바티스로 구현

    // update
    @Transactional
    public void updatePost(Long postId, UpdatedPostDto updatedPostDto){
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new IllegalArgumentException("게시글을 못찾았습니다."));
        // 변경 사항 반영 조건이 3가지가 됨 해당되면 모두가 선택됨
        /*
         * 1.제목만 바꾸는 경우
         * 2. 내용만 바꾸는 경우
         * 3. 공개 비공개 전환 하는경우 <- 이 부분은 이제 무조건 비공개면 지워도 됨
         * */

        if (updatedPostDto.getPostTitle() != null) {
            post.modifyTitle(updatedPostDto.getPostTitle());

        }
        if (updatedPostDto.getPostContent() != null) {
            post.modifyContent(updatedPostDto.getPostContent());
        }
    }
    //delete
    @Transactional
    public void deletePost(Long postId){
        postCommentRepository.deleteCommentByPostId(postId);
        postRepository.deleteById(postId);
    }
}