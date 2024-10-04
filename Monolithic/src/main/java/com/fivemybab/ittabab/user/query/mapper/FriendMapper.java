package com.fivemybab.ittabab.user.query.mapper;

import com.fivemybab.ittabab.user.command.domain.aggregate.FriendStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FriendMapper {

    List<Long> findFriendList(@Param("id") Long id, @Param("friendStatus") FriendStatus friendStatus);

    List<Long> findFriendListReverse(@Param("id") Long id, @Param("friendStatus") FriendStatus friendStatus);

}
