package com.fivemybab.ittabab.user.query.service;

import com.fivemybab.ittabab.user.query.dto.CourseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CourseQueryServiceTest {

    @Autowired
    private CourseQueryService courseQueryService;

    @DisplayName("특정 훈련 기관에 속한 훈련 과정 조회")
    @ParameterizedTest
    @ValueSource(longs = {2L})
    void testFindCourseByBC(Long bootId) {

        List<CourseDto> courseList = courseQueryService.findCourseByBootCamp(bootId);

        System.out.println(courseList);

        Assertions.assertDoesNotThrow(
                () -> courseQueryService.findCourseByBootCamp(bootId)
        );
    }

    @Test
    @DisplayName("훈련 과정 전체 조회")
    void testCourseList() {

        List<CourseDto> courseList = courseQueryService.findAll();

        System.out.println(courseList);

        Assertions.assertDoesNotThrow(
                () -> courseQueryService.findAll()
        );
    }
}