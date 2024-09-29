package com.fivemybab.ittabab.user.query.mapper;

import com.fivemybab.ittabab.user.command.domain.aggregate.FriendStatus;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FriendMapper {

    List<Long> findFriendList(Long id, FriendStatus friendStatus);
}
