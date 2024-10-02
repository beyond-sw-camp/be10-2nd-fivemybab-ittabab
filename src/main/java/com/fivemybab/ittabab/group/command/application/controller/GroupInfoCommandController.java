package com.fivemybab.ittabab.group.command.application.controller;

import com.fivemybab.ittabab.group.command.application.service.GroupInfoCommandService;
import com.fivemybab.ittabab.group.command.domain.aggregate.ChatRoomStatus;
import com.fivemybab.ittabab.group.query.dto.ChatMessageDto;
import com.fivemybab.ittabab.group.query.dto.GroupInfoDto;
import com.fivemybab.ittabab.group.query.dto.RequestChatDto;
import com.fivemybab.ittabab.group.query.service.GroupInfoQueryService;
import com.fivemybab.ittabab.user.command.application.dto.UserDto;
import com.fivemybab.ittabab.user.query.service.UserQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
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
@Tag(name = "Group", description = "모임 관련 API")
@RequiredArgsConstructor
public class GroupInfoCommandController {

    private final GroupInfoCommandService groupService;
    private final GroupInfoQueryService groupInfoQueryService;
    private final UserQueryService userQueryService;
    private final ModelMapper modelMapper;

    /* 모임 등록 */
    @Operation(summary = "모임 등록")
    @PostMapping("/registGroup")
    public ResponseEntity<String> registGroup(@RequestBody GroupInfoDto newGroupInfo, Authentication loginId) {

        Long userId = modelMapper.map(userQueryService.findUserIdByLoginId(loginId), UserDto.class).getUserId();

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
    @GetMapping("/detail/{groupId}")
    public ResponseEntity<String> registGroupUser(
            @PathVariable Long groupId,
            Authentication loginId
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
                if (userId == modelMapper.map(userQueryService.findUserIdByLoginId(loginId), UserDto.class).getUserId()) {
                    return new ResponseEntity<>("이미 가입하셨습니다.", HttpStatus.OK);
                }
            }

            // 3
            if (groupUserList.size() + 1 <= foundGroupInfo.getUserCounting()) {
                // insert
                groupService.registGroupUser(modelMapper.map(userQueryService.findUserIdByLoginId(loginId), UserDto.class).getUserId(), foundGroupInfo.getGroupId());
            } else {
                return null;
            }
        }

        return null;
    }

    /* 모임 채팅 생성 */
    @Operation(summary = "모임 채팅 생성")
    @PostMapping("/chat")
    public ResponseEntity<String> createChat(
            @RequestBody RequestChatDto requestChatDto,
            Authentication loginId,
            Model model
    ) {

        ChatRoomStatus chatRoomStatus = groupInfoQueryService.findGroupByGroupId(requestChatDto.getGroupId()).getChatRoomStatus();

        if (chatRoomStatus == ChatRoomStatus.Not_Created) {
            ChatMessageDto chatMessageDto = new ChatMessageDto();

            chatMessageDto.setMessageType(ChatMessageDto.MessageType.ENTER);
            chatMessageDto.setChatRoomId(requestChatDto.getGroupId());
            chatMessageDto.setMessage(chatMessageDto.getMessage());
            groupService.createChat(chatMessageDto, loginId);
            return new ResponseEntity<>("채팅 생성 성공", HttpStatus.OK);
        } else if (chatRoomStatus == ChatRoomStatus.Created) {
            return new ResponseEntity<>("이미 생성되어 있습니다.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("채팅방이 닫혔습니다. 관리자에게 문의하세요.", HttpStatus.OK);
        }
    }

    /* 모임 채팅방 채팅 전송 */
    @Operation(summary = "채팅 전송")
    @PostMapping("/chat/{groupId}")
    public ResponseEntity<String> joinChat(
            @PathVariable Long groupId,
            @RequestBody ChatMessageDto chatMessageDto,
            Authentication loginId
    ) {

        ChatRoomStatus chatRoomStatus = groupInfoQueryService.findGroupByGroupId(groupId).getChatRoomStatus();

        if (chatRoomStatus == ChatRoomStatus.Created) {
            chatMessageDto.setMessageType(ChatMessageDto.MessageType.TALK);
            chatMessageDto.setChatRoomId(groupId);
            groupService.sendChat(chatMessageDto, loginId);

            return new ResponseEntity<>("채팅 전송 완료", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("채팅 전송에 실패했습니다.", HttpStatus.BAD_REQUEST);
        }
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
            Authentication loginId,
            Long groupId
    ) {
        Long creatorId = groupService.findGroupByGroupId(groupId).getUserId();

        return creatorId == modelMapper.map(userQueryService.findUserIdByLoginId(loginId), UserDto.class).getUserId();
    }
}