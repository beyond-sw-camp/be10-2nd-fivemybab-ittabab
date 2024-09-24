package com.fivemybab.ittabab.group.command.application.service;

import com.fivemybab.ittabab.group.command.application.dto.GroupInfoDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class GroupServiceTest {

    @Autowired
    private GroupService service;

    @Test
    public void findGroupByGroupStatus() {
        Assertions.assertDoesNotThrow(
                () -> {
                    List<GroupInfoDTO> list = service.findGroupByGroupStatus();
                    list.forEach(System.out::println);
                }
        );
    }

    @ParameterizedTest
    @ValueSource(longs = {16L})
    // 소스 값은 자기 더미 데이터에 맞게 수정해야됩니다.
    public void findGroupByGroupId(Long groupId) {
        GroupInfoDTO foundGroup = service.findGroupByGroupId(groupId);
        Assertions.assertNotNull(foundGroup);
    }
}