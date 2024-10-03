package com.fivemybab.ittabab.user.command.application.service;

import com.fivemybab.ittabab.user.command.application.dto.CreateBootCampRequest;
import com.fivemybab.ittabab.user.command.application.dto.UpdateBootCampRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

@SpringBootTest
@Transactional
class BootCampCommandServiceTest {

    @Autowired
    private BootCampCommandService bootCampCommandService;

    private static Stream<Arguments> getBCInfo() {

        CreateBootCampRequest BC = new CreateBootCampRequest();
        BC.setBootName("아몬드 기관");
//        BC.setBootLocation("서초구 견과류동");

        return Stream.of(Arguments.arguments(BC));
    }

    @DisplayName("부캠 등록 테스트")
    @ParameterizedTest
    @MethodSource("getBCInfo")
    void testCreateBC(CreateBootCampRequest newBC) {

        Assertions.assertDoesNotThrow(
                () -> bootCampCommandService.createBootCamp(newBC)
        );
    }

    private static Stream<Arguments> getModifyInfo() {

        UpdateBootCampRequest BC = new UpdateBootCampRequest();
        BC.setBootName("피스타치오 기관");
//        BC.setBootLocation("동작구 과자돟");

        return Stream.of(Arguments.arguments(1L, BC));
    }

    @DisplayName("부캠 수정 테스트")
    @ParameterizedTest
    @MethodSource("getModifyInfo")
    void testModifyBC(Long id, UpdateBootCampRequest updateBootCampRequest) {

        Assertions.assertDoesNotThrow(
                () -> bootCampCommandService.modifyBootCamp(id, updateBootCampRequest)
        );
    }

    @DisplayName("부캠 삭제 테스트")
    @ParameterizedTest
    @ValueSource(longs = {1L})
    void testDeleteBC(Long id) {

        Assertions.assertDoesNotThrow(
                () -> bootCampCommandService.deleteBootCamp(id)
        );
    }
}