package com.fivemybab.ittabab.group.command.application.service;

import com.fivemybab.ittabab.group.query.dto.GroupInfoDto;
import com.fivemybab.ittabab.group.query.service.GroupInfoQueryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class GroupInfoCommandServiceTest {

    @Autowired
    private GroupInfoCommandService commandService;
    private GroupInfoQueryService queryService;

    @DisplayName("전체 목록 조회")
    @ParameterizedTest
    @ValueSource(strings = {"test01"})
    public void findGroupByGroupStatus(String userId, Long courseId) {
        Assertions.assertDoesNotThrow(
                () -> {
                    List<GroupInfoDto> list = queryService.findGroupByGroupStatus(userId, courseId);
                    if (list.isEmpty() || list.size() < 1) {
                        System.out.println("????");
                    } else {
                        list.forEach(System.out::println);
                    }
                }
        );
    }

    @ParameterizedTest
    @ValueSource(longs = {16L})
    // 소스 값은 자기 더미 데이터에 맞게 수정해야됩니다.
    public void findGroupByGroupId(Long groupId) {
        GroupInfoDto foundGroup = commandService.findGroupByGroupId(groupId);
        Assertions.assertNotNull(foundGroup);
    }

    @ParameterizedTest
    @ValueSource(strings = {"test01", "test999"})
    public void transferLoginIdtoUserId(String loginId) {
        Assertions.assertDoesNotThrow(
                () -> {
                    Long res = commandService.loginIdToUserId(loginId);
                    System.out.println("res = " + res);
                }
        );
    }

    @Test
    public void test() {
        // 1번 모임에는 3명이 가입되어있다.
        List<Long> arr = commandService.findGroupUserByGroupId(2L);
        Assertions.assertDoesNotThrow(
                () -> {
                    System.out.println(arr);
                    System.out.println(arr.size());
                }
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"test01", "test02"})
    public void findUserDtoByLoginId(String loginId) {
        queryService.findUserDtoByLoginId(loginId);
    }
}