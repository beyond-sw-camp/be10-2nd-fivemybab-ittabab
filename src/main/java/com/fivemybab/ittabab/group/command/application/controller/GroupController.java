package com.fivemybab.ittabab.group.command.application.controller;

import com.fivemybab.ittabab.group.command.application.dto.GroupCommentDto;
import com.fivemybab.ittabab.group.command.application.dto.GroupInfoDto;
import com.fivemybab.ittabab.group.command.application.service.GroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/group")
@Slf4j
public class GroupController {

    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService service) {
        this.groupService = service;
    }

    // 로그인한 유저의 로그인 아이디 -> 유저 아이디로 변환 메소드
    public Long loginIdToUserId(Authentication loginUserLoginId) {
        Long userId = groupService.loginIdToUserId(loginUserLoginId.getName());

        return userId;
    }

    /* 모임 등록 */
    @PostMapping("/registGroup")
    public ResponseEntity<String> registGroup(@RequestBody GroupInfoDto newGroupInfo, Authentication loginUserLoginId) {

        Long userId = loginIdToUserId(loginUserLoginId);

        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } else {
            newGroupInfo.setUserId(userId);
            newGroupInfo.setCreateDate(LocalDateTime.now());
            groupService.registGroup(newGroupInfo);
            return new ResponseEntity<>("등록 완료", HttpStatus.OK);
        }
    }

    /* 전체 모임 조회 */
    @GetMapping("/list")
    public String group(Model model, Authentication loginUserLoginId) {
        // 인증된 사용자가 아닌 경우 에러 페이지로 이동 -> 에러 페이지 구현해야 함.
        if (loginUserLoginId == null || !loginUserLoginId.isAuthenticated()) {
            // 적절한 오류 메시지를 모델에 추가하고 에러 페이지로 리다이렉트
            model.addAttribute("errorMessage", "로그인 후에 접근할 수 있습니다.");
            return "error"; // 에러 페이지로 리다이렉트
        }

        // 인증된 사용자의 이름을 가져옴
        log.info("loginUserLoginId.getName: {}", loginUserLoginId.getName());

        List<GroupInfoDto> groupList = groupService.findGroupByGroupStatus(loginUserLoginId.getName());

        if (!groupList.isEmpty()) {
            model.addAttribute("groupList", groupList);
        }

        return "group/list"; // 그룹 목록 페이지 반환
    }

    /* 모임 상세 조회 */
    @GetMapping("/detail/{groupId}")
    public String groupDetail(@PathVariable Long groupId, Model model) {
        GroupInfoDto foundGroup = groupService.findGroupByGroupId(groupId);
        List<GroupCommentDto> commentList = groupService.findGroupCommentsByGroupId(groupId);
        model.addAttribute("foundGroup", foundGroup);
        model.addAttribute("commentList", commentList);
        return "group/detail";
    }

    /* 모임 참여 */
    @GetMapping("/detail/join")
    public void registGroupUser(@PathVariable Long groupId, @RequestParam Long userId) {
        // 현재 로그인된 계정을 정보를 가져와야 되는데 얘기해 봐야 될 거 같음.
    }

    /* 그룹 채팅 참여 */
    @GetMapping("/chatroom/{groupId}")
    public String joinChatting(@PathVariable Long groupId, Model model) {
        // 참가자들에게 알람보내는 기능 추가 해야됨

        return "group/chatroom";
    }

    @GetMapping("/modify")
    public void modifyGroupInfoPage() {
    }
}