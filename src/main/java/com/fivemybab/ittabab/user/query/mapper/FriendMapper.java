package com.fivemybab.ittabab.user.query.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FriendMapper {

    List<Long> findFriendRequests(Long id);
}
