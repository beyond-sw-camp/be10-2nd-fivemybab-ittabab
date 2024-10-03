package com.fivemybab.ittabab.group.command.application.service;

import com.fivemybab.ittabab.group.command.domain.aggregate.ChatRoomStatus;
import com.fivemybab.ittabab.group.query.dto.GroupInfoDto;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.stream.Stream;

@SpringBootTest
@Transactional
class GroupInfoCommandServiceTest {

    @Autowired
    private GroupInfoCommandService service;

    public static Stream<GroupInfoDto> registerGroupInfoDtoSource() {
        return Stream.of(
                new GroupInfoDto(
                        null,
                        1L,
                        7L,
                        "새로운 모임 제목",
                        10,
                        true,
                        LocalDateTime.now(),
                        LocalDateTime.now().plusHours(1),
                        "새로운 모임 내용",
                        false,
                        ChatRoomStatus.NOT_CREATED
                )
        );
    }

    @DisplayName(value = "모임 등록 테스트")
    @ParameterizedTest
    @MethodSource("registerGroupInfoDtoSource")
    public void registerGroupInfo(GroupInfoDto newGroupInfo) {
        Assertions.assertDoesNotThrow(() -> {
            service.registGroup(7L, newGroupInfo);
        });
    }

    @DisplayName(value = "모임에 신규 사용자 등록 테스트")
    @Test
    public void registerGroupUser() {
        Assertions.assertDoesNotThrow(() -> {
            service.registGroupUser(7L, 12L);
        });
    }

    @DisplayName(value = "모임에 가입되 사용자 조회 테스트")
    @ParameterizedTest
    @ValueSource(longs = {12L, 13L})
    public void findGroupUserByGroupId(Long groupId) {
        Assertions.assertDoesNotThrow(() -> {
            service.findGroupUserByGroupId(groupId);
            System.out.println("service.findGroupByGroupId(groupId) = " + service.findGroupByGroupId(groupId));
        });
    }

    @DisplayName(value = "모임 삭제 테스트")
    @Test
    public void deleteGroup() {
        Assertions.assertDoesNotThrow(() -> {
            service.deleteGroupInfo(10L);
        });
    }
}