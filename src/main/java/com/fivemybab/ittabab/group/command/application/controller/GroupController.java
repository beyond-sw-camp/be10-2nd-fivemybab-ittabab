package com.fivemybab.ittabab.group.command.application.controller;

import com.fivemybab.ittabab.group.command.application.dto.GroupCommentDto;
import com.fivemybab.ittabab.group.command.application.dto.GroupInfoDto;
import com.fivemybab.ittabab.group.command.application.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/group")
public class GroupController {

    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService service) {
        this.groupService = service;
    }

    /* 전체 모임 조회 */
    @GetMapping("/list")
    public String group(Model model) {
        List<GroupInfoDto> groupList = groupService.findGroupByGroupStatus();

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
    @GetMapping("/join")
    public void registGroupUser(@PathVariable Long groupId, @RequestParam Long userId) {
        // 현재 로그인된 계정을 정보를 가져와야 되는데 얘기해 봐야 될 거 같음.
    }

    @GetMapping("/modify")
    public void modifyGroupInfoPage() {
    }

//    @PostMapping("/group/comment")
//    public String addComment(@RequestParam Long groupId, @RequestParam String comment) {
//        // 댓글 추가 로직
//        GroupCommentDTO newComment = .create(groupId, comment);
////        service.registComment(groupId)
//        return "redirect:/group/detail/" + groupId; // 댓글 추가 후 상세 페이지로 리다이렉트
//    }

}