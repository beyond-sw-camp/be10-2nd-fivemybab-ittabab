package com.fivemybab.ittabab.group.command.application.controller;

import com.fivemybab.ittabab.group.command.application.service.GroupCommentCommandService;
import com.fivemybab.ittabab.group.query.dto.GroupCommentDto;
import com.fivemybab.ittabab.group.query.dto.UpdateGroupCommentDto;
import com.fivemybab.ittabab.group.query.service.GroupCommentQueryService;
import com.fivemybab.ittabab.security.util.CustomUserDetails;
import com.fivemybab.ittabab.user.command.domain.aggregate.UserRole;
import com.fivemybab.ittabab.user.command.domain.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/groupComment")
@Slf4j
@Tag(name = "Group", description = "모임 관련 API")
@RequiredArgsConstructor
public class GroupCommentCommandController {

    private final GroupCommentCommandService commandService;
    private final GroupCommentQueryService queryService;

    /* 댓글 추가 기능 */
    @Operation(
            summary = "모임 댓글 등록 기능",
            description = "특정 하나의 모임에 댓글을 등록하는 기능입니다.")
    @PostMapping
    public ResponseEntity<Void> registerComment(
            @RequestBody GroupCommentDto newComment,
            @AuthenticationPrincipal CustomUserDetails loginUser
    ) {
        newComment.setGroupCommentId(null);
        newComment.setUserId(loginUser.getUserId());
        newComment.setParentCommentId(null);
        newComment.setCreateDate(LocalDateTime.now());
        newComment.setUpdateDate(LocalDateTime.now());
        newComment.setBlinded(false);

        commandService.registerComment(newComment);

        return ResponseEntity.ok().build();
    }


    @Operation(
            summary = "모임 댓글 삭제 기능",
            description = "사용자가 등록한 댓글을 삭제하는 기능입니다. 작성한 사용자가 아니면 삭제가 불가능합니다."
    )
    @DeleteMapping("/admin/{groupCommentId}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Long groupCommentId,
            @AuthenticationPrincipal CustomUserDetails loginUser
    ) {
        // 1. 해당 글이 있는가?
        GroupCommentDto foundGroupComment = queryService.findByGroupCommentId(groupCommentId);

        // 댓글이 존재하는지 확인
        if (foundGroupComment == null) {
            return ResponseEntity.notFound().build();
        }

        log.info("founGroupComment {}", foundGroupComment);

        Long userUserId = loginUser.getUserId();


        System.out.println("접근자: " + userUserId);

        System.out.println("작성자 = " + foundGroupComment.getUserId());

        System.out.println("res = " + (userUserId == foundGroupComment.getUserId()));

        // 2. 해당 글의 작성자 또는 관리자 여부

        // 작성자 입니까?
        if (!(loginUser.getUserId() == foundGroupComment.getUserId())) {

            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        commandService.deleteByGroupCommentId(groupCommentId);

        return ResponseEntity.ok().build();
    }

    /* 댓글 수정 기능 */
    @Operation(
            summary = "모임 댓글 수정",
            description = "작성자는 작성한 댓글을 수정할 수 있습니다.")
    @PutMapping("/{groupCommentId}")
    public ResponseEntity<String> modifyGroupComment(
            @PathVariable Long groupCommentId,
            @RequestBody UpdateGroupCommentDto modifyComment,
            @AuthenticationPrincipal CustomUserDetails loginUser
            ) {
        // 존재여부
        GroupCommentDto foundGroupComment = queryService.findByGroupCommentId(groupCommentId);

        if (foundGroupComment == null) {
            System.out.println("존재 x");
            return ResponseEntity.notFound().build();
        }
        System.out.println("x존재 o");

        // 작성자 확인
        if (loginUser.getUserId() != foundGroupComment.getUserId()) {
            System.out.println("다른 사람 글임");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        System.out.println("본인 확인 완료");

        foundGroupComment.setGroupCommentId(groupCommentId);
        foundGroupComment.setCommentContent(modifyComment.getCommentContent());
        System.out.println("foundGroupComment.getCommentContent() = " + foundGroupComment.getCommentContent());
        foundGroupComment.setUpdateDate(LocalDateTime.now());
        System.out.println("foundGroupComment.getUpdateDate() = " + foundGroupComment.getUpdateDate());

        commandService.modifyGroupComment(foundGroupComment);

        return ResponseEntity.ok().body("댓글 수정 완료");
    }
}
