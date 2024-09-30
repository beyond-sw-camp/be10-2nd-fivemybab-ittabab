package com.fivemybab.ittabab.group.command.application.controller;

import com.fivemybab.ittabab.group.command.application.service.GroupService;
import com.fivemybab.ittabab.group.query.dto.GroupInfoDto;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "모임 등록")
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

    /* 모임 참여 */
    @Operation(summary = "모임 참여")
    @GetMapping("/detail/{groupId}/join")
    public ResponseEntity<String> registGroupUser(
            @PathVariable Long groupId,
            Authentication loginUserLoginId
    ) {
        /* 처리과정
         * 1. 모임이 존재하는 확인
         * 2. 이미 가입되어 있는지 확인
         * 3. 가입하면 모집 인원 이하가 되는지 확인 */

        // 1
        GroupInfoDto foundGroupInfo = groupService.findGroupByGroupId(groupId);

        if (foundGroupInfo == null) {
            // 모임이 존재하지 않는 경우
            return new ResponseEntity<>("그런 모임은 없습니다.", HttpStatus.OK);
        } else {
            // 모임이 존재하는 경우

            // 모임에 가입한 인원들
            List<Long> groupUserList = groupService.findGroupUserByGroupId(groupId);

            // 2
            for (Long userId : groupUserList) {
                if (userId == groupService.loginIdToUserId(loginUserLoginId.getName())) {
                    return new ResponseEntity<>("이미 가입하셨습니다.", HttpStatus.OK);
                }
            }

            // 3
            if (groupUserList.size() + 1 <= foundGroupInfo.getUserCounting()) {
                // insert
                groupService.registGroupUser(groupService.loginIdToUserId(loginUserLoginId.getName()), foundGroupInfo.getGroupId());
            } else {
                return null;
            }
        }

        return null;
    }

    /* 모임 채팅 참여 */
    @Operation(summary = "모임 채팅 참여")
    @GetMapping("/chatroom/{groupId}")
    public String joinChatting(@PathVariable Long groupId, Model model) {
        // 참가자들에게 알람보내는 기능 추가 해야됨

        return "group/chatroom";
    }

    /* 모임 삭제 */
    @Operation(summary = "모임 삭제")
    @DeleteMapping("/{groupId}")
    public ResponseEntity<String> deleteGroup(
            @PathVariable Long groupId,
            Authentication loginUserLoginId
    ) {
        if (checkCreator(loginUserLoginId, groupId)) {
            groupService.deleteGroupInfo(groupId);

            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return new ResponseEntity<>("작성자가 아닙니다.", HttpStatus.OK);
        }
    }

    /* 모임 모집자 확인 */
    public boolean checkCreator(
            Authentication loginUserLoginId,
            Long groupId
    ) {
        Long creatorId = groupService.findGroupByGroupId(groupId).getUserId();

        return creatorId == groupService.loginIdToUserId(loginUserLoginId.getName());
    }
}