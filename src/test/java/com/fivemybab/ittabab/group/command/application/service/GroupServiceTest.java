package com.fivemybab.ittabab.group.command.application.service;

import com.fivemybab.ittabab.group.command.application.dto.GroupInfoDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;

import java.util.List;

@SpringBootTest
class GroupServiceTest {

    @Autowired
    private GroupService service;

    @ParameterizedTest
    @ValueSource(strings = {"test01"})
    public void findGroupByGroupStatus(String userId) {
        Assertions.assertDoesNotThrow(
                () -> {
                    List<GroupInfoDto> list = service.findGroupByGroupStatus(userId);
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
        GroupInfoDto foundGroup = service.findGroupByGroupId(groupId);
        Assertions.assertNotNull(foundGroup);
    }

    @ParameterizedTest
    @ValueSource(strings = {"test01", "test999"})
    public void transferLoginIdtoUserId(String loginId) {
        Assertions.assertDoesNotThrow(
                () -> {
                    Long res = service.loginIdToUserId(loginId);
                    System.out.println("res = " + res);
                }
        );
    }
}