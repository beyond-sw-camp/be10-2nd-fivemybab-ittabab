package com.fivemybab.ittabab.user.command.application.service;

import com.fivemybab.ittabab.exception.NotFoundException;
import com.fivemybab.ittabab.user.command.application.dto.FriendRequestDTO;
import com.fivemybab.ittabab.user.command.application.dto.UpdateFriendRequest;
import com.fivemybab.ittabab.user.command.domain.aggregate.Friend;
import com.fivemybab.ittabab.user.command.domain.aggregate.FriendStatus;
import com.fivemybab.ittabab.user.command.domain.repository.FriendRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FriendCommandService {

    private final ModelMapper modelMapper;
    private final FriendRepository friendRepository;

    @Transactional
    public void sendFriendRequest(FriendRequestDTO friendRequest) {

        /* 이미 상대방에게 친구 요청을 보낸 적이 있는데, 또 친구 요청을 보내려는 상황
        * -> 오류 메세지 보냄. */
        boolean alreadySent = friendRepository.existsByFromUserIdAndToUserId(friendRequest.getFromUserId(), friendRequest.getToUserId());
        if (alreadySent) {
            throw new IllegalArgumentException("이미 친구 요청을 보냈습니다.");
        }

        /* 상대방이 보낸 요청이 이미 있고, 나 또한 상대방에게 친구 요청을 보내려는 상황
        * -> 수락한다. */
        boolean reverseRequestExists = friendRepository.existsByFromUserIdAndToUserId(friendRequest.getToUserId(), friendRequest.getFromUserId());
        if (reverseRequestExists) {
            acceptFriendRequest(friendRequest.getToUserId(), new UpdateFriendRequest(friendRequest.getFromUserId()));
            return;
        }

        /* 위의 두 가지 모두의 경우가 아닐 경우
        * -> 상태 컬럼이 PENDING인 상태로 새로운 행을 저장한다. */
        Friend friend = modelMapper.map(friendRequest, Friend.class);
        friendRepository.save(friend);
    }

    @Transactional
    public void acceptFriendRequest(Long userId, UpdateFriendRequest updateFriendRequest) {

        /* 해당 친구 요청이 있을 경우에만 예외 처리를 발생하지 않고 정상적으로 가져옴 */
        Friend friend = findFriendRequestOrThrow(updateFriendRequest.getFromUserId(), userId);

        /* 상태 검증 : 현재 대기 중인 요청만 수락 가능 */
        validatePendingStatus(friend);
        friend.modifyStatus(FriendStatus.ACCEPTED);
        friendRepository.save(friend);
    }

    @Transactional
    public void rejectFriendRequest(Long userId, UpdateFriendRequest updateFriendRequest) {

        /* 해당 친구 요청이 있을 경우에만 예외 처리를 발생하지 않고 정상적으로 가져옴 */
        Friend friend = findFriendRequestOrThrow(updateFriendRequest.getFromUserId(), userId);

        /* 상태 검증 : 현재 대기 중인 요청만 거절 가능 */
        validatePendingStatus(friend);
        friend.modifyStatus(FriendStatus.REJECTED);
        friendRepository.save(friend);
    }

    /* 해당 친구 요청 존재여부 확인 및 조회 메서드 */
    private Friend findFriendRequestOrThrow(Long fromUserId, Long toUserId) {
        return friendRepository.findByFromUserIdAndToUserId(fromUserId, toUserId)
                .orElseThrow(() -> new NotFoundException("해당 친구 요청을 찾을 수 없습니다."));
    }

    /* 상태 컬럼 검증 (대기(PENDING) 상태인지 아닌지) */
    private void validatePendingStatus(Friend friend) {
        if (friend.getFriendStatus() != FriendStatus.PENDING) {
            throw new IllegalArgumentException("요청이 대기 상태가 아닙니다.");
        }
    }

    @Transactional
    public void deleteFriend(Long userId, Long friendUserId) {

        /* 해당 친구 관계가 존재하는 지 확인 및 조회
        * (누가 누구에게 보냈는지에 대한 사전정보는 없으므로 양방향 모두 조회 후 찾는다.) */
        Friend friend = friendRepository.findByFromUserIdAndToUserIdAndFriendStatus(userId, friendUserId, FriendStatus.ACCEPTED)
                .or(() -> friendRepository.findByFromUserIdAndToUserIdAndFriendStatus(friendUserId, userId, FriendStatus.ACCEPTED))
                .orElseThrow(() -> new NotFoundException("해당 친구 관계를 찾을 수 없습니다."));

        friendRepository.delete(friend);
    }
}
