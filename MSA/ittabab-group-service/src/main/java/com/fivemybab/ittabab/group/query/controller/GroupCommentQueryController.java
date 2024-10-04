package com.fivemybab.ittabab.group.query.controller;

import com.fivemybab.ittabab.group.query.dto.GroupCommentDto;
import com.fivemybab.ittabab.group.query.service.GroupCommentQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/groupComment")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Group", description = "모임 관련 API")
public class GroupCommentQueryController {

    private final GroupCommentQueryService queryService;

    /* 모임 댓글 조회 */
    @Operation(
            summary = "모임 댓글 조회",
            description = "특정 모임의 등록된 모든 댓글을 조회합니다(블라인드 처리되지 않은).")
    @GetMapping("/{groupId}")
    public ResponseEntity<List<GroupCommentDto>> groupComment(
            @PathVariable
            Long groupId
    ) {
        List<GroupCommentDto> commentList = queryService.findByGroupId(groupId);

        return ResponseEntity.status(HttpStatus.OK).body(commentList);
    }
}
