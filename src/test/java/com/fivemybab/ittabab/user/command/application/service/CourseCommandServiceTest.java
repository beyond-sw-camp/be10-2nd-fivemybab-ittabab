package com.fivemybab.ittabab.user.command.application.service;

import com.fivemybab.ittabab.user.command.application.dto.CreateCourseRequest;
import com.fivemybab.ittabab.user.command.application.dto.UpdateCourseRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.stream.Stream;

@SpringBootTest
@Transactional
class CourseCommandServiceTest {

    @Autowired
    private CourseCommandService courseCommandService;

    private static Stream<Arguments> getCourseInfo() {

        CreateCourseRequest courseRequest = new CreateCourseRequest();
        courseRequest.setBootId(1L);
        courseRequest.setStartDate(LocalDate.parse("2024-01-02"));
        courseRequest.setEndDate(LocalDate.parse("2024-06-03"));
        courseRequest.setClassName("오레오 과정");
        courseRequest.setSeasonNum(3);

        return Stream.of(Arguments.arguments(courseRequest));
    }

    @DisplayName("훈련과정 등록 테스트")
    @ParameterizedTest
    @MethodSource("getCourseInfo")
    void testCreateBC(CreateCourseRequest createCourseRequest) {

        Assertions.assertDoesNotThrow(
                () -> courseCommandService.createCourse(createCourseRequest)
        );
    }

    private static Stream<Arguments> getModifyInfo() {

        UpdateCourseRequest courseRequest = new UpdateCourseRequest();
        courseRequest.setStartDate(LocalDate.parse("2023-07-25"));
        courseRequest.setEndDate(LocalDate.parse("2023-11-30"));
        courseRequest.setClassName("돈까스 과정");
        courseRequest.setSeasonNum(5);

        return Stream.of(Arguments.arguments(1L, courseRequest));
    }

    @DisplayName("훈련과정 수정 테스트")
    @ParameterizedTest
    @MethodSource("getModifyInfo")
    void testModifyUser(Long id, UpdateCourseRequest updateCourseRequest) {

        Assertions.assertDoesNotThrow(
                () -> courseCommandService.modifyCourse(id, updateCourseRequest)
        );
    }

    @DisplayName("훈련과정 삭제 테스트")
    @ParameterizedTest
    @ValueSource(longs = {1L})
    void testDeleteUser(Long userId) {

        Assertions.assertDoesNotThrow(
                () -> courseCommandService.deleteCourse(userId)
        );
    }
}