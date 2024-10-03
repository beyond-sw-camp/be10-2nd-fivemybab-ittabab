package com.fivemybab.ittabab.group.query.service;

import com.fivemybab.ittabab.group.query.dto.GroupInfoDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class GroupInfoQueryServiceTest {

    @Autowired
    private GroupInfoQueryService queryService;

    @DisplayName("전체 목록 조회 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"test01", "test02"})
    public void findGroupByGroupStatus(String userId) {
        Assertions.assertDoesNotThrow(
                () -> {
                    List<GroupInfoDto> list = queryService.findGroupByGroupStatus(userId);
                    if (list.isEmpty() || list.size() < 1) {
                        System.out.println("조회 결과 없음");
                    } else {
                        list.forEach(System.out::println);
                    }
                }
        );
    }

    @DisplayName("모임 상세 조회 테스트")
    @ParameterizedTest
    @ValueSource(longs = {1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L})
    // 소스 값은 자기 더미 데이터에 맞게 수정해야됩니다.
    public void findGroupByGroupId(Long groupId) {
        GroupInfoDto foundGroup = queryService.findGroupByGroupId(groupId);
        Assertions.assertNotNull(foundGroup);
    }

    @DisplayName("모임 회원 조회 테스트")
    @ParameterizedTest
    @ValueSource(longs = {1L, 2L})
    public void selectGroupUserByGroupId(Long groupId) {
        List<Long> arr = queryService.findGroupUserByGroupId(groupId);
        Assertions.assertDoesNotThrow(
                () -> {
                    System.out.println(groupId + "번 모임의 회원 번호: " + arr);
                }
        );
    }

    @Test
    public void test(){
        System.out.println(queryService.findCourseIdByLoginId("alice01"));
    }
}