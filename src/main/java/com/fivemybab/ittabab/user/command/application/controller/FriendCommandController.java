package com.fivemybab.ittabab.user.command.application.controller;

import com.fivemybab.ittabab.user.command.application.dto.FriendRequestDTO;
import com.fivemybab.ittabab.user.command.application.dto.UpdateFriendRequest;
import com.fivemybab.ittabab.user.command.application.service.FriendCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Friend", description = "친구 관련 API")
@RestController
@RequestMapping("/friend")
@RequiredArgsConstructor
public class FriendCommandController {

    private final FriendCommandService friendCommandService;

    /* 친구 신청 기능 */
    @Operation(summary = "친구 신청")
    @PostMapping("/request")
    public ResponseEntity<String> sendFriendRequest(@RequestBody FriendRequestDTO friendRequest) {

        try {
            friendCommandService.sendFriendRequest(friendRequest);
            return ResponseEntity.ok("친구 신청을 보냈습니다.");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("친구 신청 처리 중 오류가 발생했습니다.");
        }
    }

    /* 친구 수락 기능 */
    @Operation(summary = "친구 수락")
    @PutMapping("/{userId}/accept")
    public ResponseEntity<String> acceptFriendRequest(@PathVariable Long userId, @RequestBody UpdateFriendRequest updateFriendRequest) {

        try {
            friendCommandService.acceptFriendRequest(userId, updateFriendRequest);
            return ResponseEntity.ok("친구 요청을 수락하였습니다.");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("친구 요청 수락 중 오류가 발생했습니다.");
        }
    }

    /* 친구 거절 기능 */
    @Operation(summary = "친구 거절")
    @PutMapping("/{userId}/reject")
    public ResponseEntity<String> rejectFriendRequest(@PathVariable Long userId, @RequestBody UpdateFriendRequest updateFriendRequest) {

        try {
            friendCommandService.rejectFriendRequest(userId, updateFriendRequest);
            return ResponseEntity.ok("친구 요청을 거절하였습니다.");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("친구 요청 거절 중 오류가 발생했습니다.");
        }
    }

    /* 친구 삭제 기능 */
    @Operation(summary = "친구 삭제")
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteFriend(@PathVariable Long userId, @RequestBody Long friendUserId) {

        try {
            friendCommandService.deleteFriend(userId, friendUserId);
            return ResponseEntity.ok("친구를 삭제했습니다.");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("친구 삭제 중 오류가 발생했습니다.");
        }
    }
}
