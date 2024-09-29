package com.fivemybab.ittabab.user.query.service;

import com.fivemybab.ittabab.user.command.domain.aggregate.FriendStatus;
import com.fivemybab.ittabab.user.query.mapper.FriendMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendQueryService {

    private final FriendMapper friendMapper;

    public List<Long> findFriendRequests(Long id) {

        return friendMapper.findFriendList(id, FriendStatus.PENDING);
    }

    public List<Long> findFriendList(Long id) {

        return friendMapper.findFriendList(id, FriendStatus.ACCEPTED);
    }
}
