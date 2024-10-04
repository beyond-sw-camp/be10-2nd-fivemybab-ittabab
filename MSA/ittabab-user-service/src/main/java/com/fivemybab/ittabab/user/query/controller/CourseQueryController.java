package com.fivemybab.ittabab.user.query.controller;

import com.fivemybab.ittabab.user.query.dto.CourseDto;
import com.fivemybab.ittabab.user.query.service.CourseQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Course", description = "훈련 과정 관련 API")
@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseQueryController {

    private final CourseQueryService courseQueryService;

    /* 특정 훈련 기관에 속한 훈련 과정 조회 */
    @Operation(summary = "특정 훈련 기관에 속한 훈련 과정 조회")
    @GetMapping("/list")
    public List<CourseDto> getAllCourses(@RequestParam Long bootId) {
        return courseQueryService.findCourseByBootCamp(bootId);
    }


    /* 훈련 과정 리스트 전체 조회 */
    @Operation(summary = "훈련 과정 전체 조회")
    @GetMapping("/all")
    public List<CourseDto> getCourseByBootCamp() {
        return courseQueryService.findAll();
    }

}
