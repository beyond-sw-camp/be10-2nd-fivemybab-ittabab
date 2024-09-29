package com.fivemybab.ittabab.user.query.service;

import com.fivemybab.ittabab.user.query.mapper.FriendMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendQueryService {

    private final FriendMapper friendMapper;

    public List<Long> findFriendRequests(Long id) {

        return friendMapper.findFriendRequests(id);
    }
}
