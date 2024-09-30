package com.fivemybab.ittabab.user.command.application.controller;

import com.fivemybab.ittabab.user.command.application.dto.CreateCourseRequest;
import com.fivemybab.ittabab.user.command.application.dto.UpdateCourseRequest;
import com.fivemybab.ittabab.user.command.application.service1.service.CourseCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Course", description = "훈련 과정 관련 API")
@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseCommandController {

    private final CourseCommandService courseCommandService;

    /* 훈련 과정 등록 */
    @Operation(summary = "훈련 과정 등록")
    @PostMapping
    public ResponseEntity<String> createCourse(@RequestBody CreateCourseRequest createCourseRequest) {

        courseCommandService.createCourse(createCourseRequest);

        return ResponseEntity.ok().body("등록 성공");
    }

    /* 훈련 과정 수정 */
    @Operation(summary = "훈련 과정 수정")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCourse(@PathVariable Long id, @RequestBody UpdateCourseRequest updateCourseRequest) {

        courseCommandService.modifyCourse(id, updateCourseRequest);

        return ResponseEntity.ok().body("수정 성공");
    }

    /* 훈련 과정 삭제 */
    @Operation(summary = "훈련 과정 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id) {

        courseCommandService.deleteCourse(id);

        return ResponseEntity.noContent().build();
    }
}
