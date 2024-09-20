package com.fivemybab.ittabab.group.command.controller;

import com.fivemybab.ittabab.group.command.dto.GroupCommentDTO;
import com.fivemybab.ittabab.group.command.dto.GroupInfoDTO;
import com.fivemybab.ittabab.group.command.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/group")
public class GroupController {

    private final GroupService service;

    @Autowired
    public GroupController(GroupService service) {
        this.service = service;
    }

    /* 전체 모임 조회 */
    @GetMapping("/list")
    public String group(Model model) {
        List<GroupInfoDTO> groupList = service.findGroupByGroupStatus();

        if (!groupList.isEmpty() && groupList.size() > 0) {
            model.addAttribute("groupList", groupList);
        }

        return "group/list";
    }

    /* 모임 상세 조회 */
    @GetMapping("/detail/{groupId}")
    public String groupDetail(@PathVariable Integer groupId, Model model) {
        GroupInfoDTO foundGroup = service.findGroupByGroupId(groupId);
        List<GroupCommentDTO> commentList = service.findGroupCommentsByGroupId(groupId);
        model.addAttribute("foundGroup", foundGroup);
        model.addAttribute("commentList", commentList);
        return "group/detail";
    }

    /* 모임 참여 */
    @GetMapping("/join")
    public void registGroupMember(@PathVariable Integer groupId, @RequestParam Integer memberId) {
        // 현재 로그인된 계정을 정보를 가져와야 되는데 얘기해 봐야 될 거 같음.
    }

    @GetMapping("/modify")
    public void modifyGroupInfoPage() {
    }

//    @PostMapping("/group/comment")
//    public String addComment(@RequestParam Integer groupId, @RequestParam String comment) {
//        // 댓글 추가 로직
//        GroupCommentDTO newComment = .create(groupId, comment);
////        service.registComment(groupId)
//        return "redirect:/group/detail/" + groupId; // 댓글 추가 후 상세 페이지로 리다이렉트
//    }

}