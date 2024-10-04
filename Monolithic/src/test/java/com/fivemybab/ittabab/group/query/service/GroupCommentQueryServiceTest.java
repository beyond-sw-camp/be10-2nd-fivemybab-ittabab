package com.fivemybab.ittabab.group.query.service;

import com.fivemybab.ittabab.group.query.dto.GroupCommentDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class GroupCommentQueryServiceTest {

    @Autowired
    private GroupCommentQueryService queryService;

    @DisplayName("모임 댓글 조회 테스트")
    @ParameterizedTest
    @ValueSource(longs = {1L})
    public void findByGroupId(Long groupId) {
        List<GroupCommentDto> groupComments = queryService.findByGroupId(groupId);
    }
}