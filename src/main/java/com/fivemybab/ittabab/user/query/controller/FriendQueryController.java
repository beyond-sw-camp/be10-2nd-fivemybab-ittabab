package com.fivemybab.ittabab.user.query.controller;

import com.fivemybab.ittabab.user.query.service.FriendQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Friend", description = "친구 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/friend")
public class FriendQueryController {

    private final FriendQueryService friendQueryService;

    /* 친구 요청 조회 */
    @Operation(summary = "친구 요청 조회")
    @GetMapping("/{id}/request")
    public ResponseEntity<List<Long>> getFriendRequests(@PathVariable Long id) {

        List<Long> friendRequests = friendQueryService.findFriendRequests(id);

        return new ResponseEntity<>(friendRequests, HttpStatus.OK);
    }

    /* 친구 목록 조회 */
    @Operation(summary = "친구 목록 조회")
    @GetMapping("/{id}/list")
    public ResponseEntity<List<Long>> getFriendList(@PathVariable Long id) {

        List<Long> friendRequests = friendQueryService.findFriendList(id);

        return new ResponseEntity<>(friendRequests, HttpStatus.OK);
    }
}
