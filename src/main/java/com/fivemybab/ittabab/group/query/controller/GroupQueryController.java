package com.fivemybab.ittabab.group.query.controller;

import com.fivemybab.ittabab.group.query.Service.GroupQueryService;
import com.fivemybab.ittabab.group.query.dto.GroupCommentDto;
import com.fivemybab.ittabab.group.query.dto.GroupInfoDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/group")
@Slf4j
public class GroupQueryController {

    private final GroupQueryService groupQueryService;

    @Autowired
    public GroupQueryController(GroupQueryService groupQueryService) {
        this.groupQueryService = groupQueryService;
    }

    // 로그인한 유저의 로그인 아이디 -> 유저 아이디로 변환 메소드
    public Long loginIdToUserId(Authentication loginUserLoginId) {
        Long userId = groupQueryService.loginIdToUserId(loginUserLoginId.getName());

        return userId;
    }

    /* 전체 모임 조회 */
    @Operation(summary = "전체 모임 조회")
    @GetMapping("/list")
    public ResponseEntity<List<GroupInfoDto>> group(Model model, Authentication loginUserLoginId) {
        // 인증된 사용자가 아닌 경우 에러 페이지로 이동 -> 에러 페이지 구현해야 함.
        if (loginUserLoginId == null || !loginUserLoginId.isAuthenticated()) {
            // 적절한 오류 메시지를 모델에 추가하고 에러 페이지로 리다이렉트
            model.addAttribute("errorMessage", "로그인 후에 접근할 수 있습니다.");

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        // 인증된 사용자의 이름을 가져옴
        log.info("loginUserLoginId.getName: {}", loginUserLoginId.getName());

        List<GroupInfoDto> groupList = groupQueryService.findGroupByGroupStatus(loginUserLoginId.getName());

        if (!groupList.isEmpty()) {
            model.addAttribute("groupList", groupList);
        }

        return ResponseEntity.status(HttpStatus.OK).body(groupList);
    }

    /* 모임 상세 조회 */
    @Operation(summary = "모임 상세 조회")
    @GetMapping("/{groupId}")
    public ResponseEntity<GroupInfoDto> groupDetail(@PathVariable Long groupId, Model model) {
        GroupInfoDto foundGroup = groupQueryService.findGroupByGroupId(groupId);
        List<GroupCommentDto> commentList = groupQueryService.findGroupCommentsByGroupId(groupId);
        model.addAttribute("foundGroup", foundGroup);
        model.addAttribute("commentList", commentList);
        return ResponseEntity.ok(foundGroup);
    }
}
