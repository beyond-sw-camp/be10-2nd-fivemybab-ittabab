package com.fivemybab.ittabab.group.command.application.service;

import com.fivemybab.ittabab.group.query.dto.GroupCommentDto;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.stream.Stream;

@SpringBootTest
@Transactional
class GroupCommentCommandServiceTest {

    @Autowired
    private GroupCommentCommandService service;

    private static Stream<GroupCommentDto> registerGroupCommentDtoSource() {
        return Stream.of(
                new GroupCommentDto(
                        null,
                        12L,
                        null,
                        7L,
                        "테스트 내용 1",
                        LocalDateTime.now(),
                        null,
                        false
                ),
                new GroupCommentDto(
                        null,
                        13L,
                        null,
                        7L,
                        "테스트 내용 2",
                        LocalDateTime.now(),
                        null,
                        false
                )
        );
    }

    @DisplayName("댓글 등록")
    @ParameterizedTest
    @MethodSource("registerGroupCommentDtoSource")
    public void registerGroupComment(GroupCommentDto newComment) {
        Assertions.assertDoesNotThrow(() -> {
            service.registerComment(newComment);
        });
    }

    @DisplayName("댓글 삭제")
    @ParameterizedTest
    @ValueSource(longs = {10L, 15L})
    public void deleteGroupComment(Long groupCommentId) {
        Assertions.assertDoesNotThrow(() -> {
            service.deleteByGroupCommentId(groupCommentId);
        });
    }

    private static Stream<GroupCommentDto> modifyRegisterGroupCommentDtoSource() {
        return Stream.of(
                new GroupCommentDto(
                        10L,
                        null,
                        null,
                        7L,
                        "test",
                        null,
                        LocalDateTime.now(),
                        false
                        )
        );
    }

    @DisplayName("댓글 수정")
    @ParameterizedTest
    @MethodSource("modifyRegisterGroupCommentDtoSource")
    public void modifyGroupComment(GroupCommentDto modifyGroupComment) {
        Assertions.assertDoesNotThrow(() -> {
            service.modifyGroupComment(modifyGroupComment);
        });
    }
}