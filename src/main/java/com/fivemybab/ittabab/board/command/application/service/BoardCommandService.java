package com.fivemybab.ittabab.board.command.application.service;

import com.fivemybab.ittabab.board.command.application.dto.CreateBoardDTO;
import com.fivemybab.ittabab.board.command.application.dto.UpdatedBoardDTO;
import com.fivemybab.ittabab.board.command.domain.aggregate.Post;
import com.fivemybab.ittabab.board.command.domain.repository.PostRepository;
import com.fivemybab.ittabab.user.command.domain.aggregate.UserInfo;
import com.fivemybab.ittabab.user.command.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service

public class BoardCommandService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public BoardCommandService(PostRepository postRepository, UserRepository userRepository,ModelMapper modelMapper){
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this. modelMapper = modelMapper;
    }


    @Transactional // create 서비스 구문
    public CreateBoardDTO createBoard(CreateBoardDTO createBoardDTO, Long userId) {
        UserInfo user = userRepository.findByUserId(userId);  // 작성자 조회
        if (user == null) {
            throw new IllegalArgumentException("회원 정보를 찾을 수 없습니다.");
        }

        //게시판 글 객체 생성
        Post post = Post.builder()
                .postTitle(createBoardDTO.getPostTitle())
                .postContent(createBoardDTO.getPostContent())
                .isBlinded(false) //true? false?
                .createDate(LocalDateTime.now())
                .build();
        postRepository.save(post);
        return modelMapper.map(post, CreateBoardDTO.class);
    }

    // 모든 게시판 조회는 마이바티스로 구현

    // update
    @Transactional
    public void updateBoard(Long postId, UpdatedBoardDTO updatedBoardDTO){
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new IllegalArgumentException("게시글을 못찾았습니다."));
        // 변경 사항 반영 조건이 3가지가 됨 해당되면 모두가 선택됨
        /*
        * 1.제목만 바꾸는 경우
        * 2. 내용만 바꾸는 경우
        * 3. 공개 비공개 전환 하는경우 <- 이 부분은 이제 무조건 비공개면 지워도 됨
        * */
        if (updatedBoardDTO.getPostTitle() != null) {
            post.setPostTitle(updatedBoardDTO.getPostTitle());
        }
        if (updatedBoardDTO.getPostContent() != null) {
            post.setPostContent(updatedBoardDTO.getPostContent());
        }
        if (updatedBoardDTO.getIsBlinded() != null) {
            post.setBlinded(Boolean.parseBoolean(updatedBoardDTO.getIsBlinded()));
        }
        postRepository.save(post);
    }

    //delete
    @Transactional
    public void deleteBoard(Long postId){
        postRepository.deleteById(postId);
    }
















}
