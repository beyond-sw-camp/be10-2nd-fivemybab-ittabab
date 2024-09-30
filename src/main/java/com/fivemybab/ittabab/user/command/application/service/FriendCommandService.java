package com.fivemybab.ittabab.user.command.application.service;

import com.fivemybab.ittabab.user.command.application.dto.FriendRequestDTO;
import com.fivemybab.ittabab.user.command.application.dto.UpdateFriendRequest;
import com.fivemybab.ittabab.user.command.domain.aggregate.Friend;
import com.fivemybab.ittabab.user.command.domain.aggregate.FriendStatus;
import com.fivemybab.ittabab.user.command.domain.repository.FriendRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FriendCommandService {

    private final ModelMapper modelMapper;
    private final FriendRepository friendRepository;

    @Transactional
    public void sendFriendRequest(FriendRequestDTO friendRequest) {

        Friend friend = modelMapper.map(friendRequest, Friend.class);
        friendRepository.save(friend);
    }

    @Transactional
    public void acceptFriendRequest(Long userId, UpdateFriendRequest updateFriendRequest) {

        Friend friend = friendRepository.findByFromUserIdAndToUserId(updateFriendRequest.getFromUserId(), userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 친구 요청을 찾을 수 없습니다."));

        friend.modifyStatus("accept");
        friendRepository.save(friend);
    }

    @Transactional
    public void rejectFriendRequest(Long userId, UpdateFriendRequest updateFriendRequest) {

        Friend friend = friendRepository.findByFromUserIdAndToUserId(updateFriendRequest.getFromUserId(), userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 친구 요청을 찾을 수 없습니다."));

        friend.modifyStatus("reject");
        friendRepository.save(friend);
    }

    @Transactional
    public void deleteFriend(Long userId, Long friendUserId) {

        Optional<Friend> friend1 = friendRepository.findByFromUserIdAndToUserIdAndFriendStatus(userId, friendUserId, FriendStatus.ACCEPTED);
        Optional<Friend> friend2 = friendRepository.findByFromUserIdAndToUserIdAndFriendStatus(friendUserId, userId, FriendStatus.ACCEPTED);

        if (friend1.isPresent()) {
            friendRepository.delete(friend1.get());
        } else if (friend2.isPresent()) {
            friendRepository.delete(friend2.get());
        } else {
            throw new IllegalArgumentException("해당 친구 관계를 찾을 수 없습니다.");
        }
    }
}
