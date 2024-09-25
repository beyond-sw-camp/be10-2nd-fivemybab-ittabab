package com.fivemybab.ittabab.group.command.application.controller;

import com.fivemybab.ittabab.group.command.application.dto.GroupCommentDto;
import com.fivemybab.ittabab.group.command.application.dto.GroupCommentDto;
import com.fivemybab.ittabab.group.command.application.dto.GroupInfoDto;
import com.fivemybab.ittabab.group.command.application.dto.GroupInfoDto;
import com.fivemybab.ittabab.group.command.application.service.GroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    /* 전체 모임 조회 */
    @GetMapping("/list")
    public String group(Model model, Authentication authentication) {
        log.info("authentication.getName: {}", authentication.getName());
        List<GroupInfoDto> groupList = groupService.findGroupByGroupStatus(authentication.getName());

        if (!groupList.isEmpty() && groupList.size() > 0) {
            model.addAttribute("groupList", groupList);
        }

        return "group/list";
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