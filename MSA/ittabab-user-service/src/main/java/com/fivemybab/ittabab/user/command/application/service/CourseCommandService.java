package com.fivemybab.ittabab.user.command.application.service;

import com.fivemybab.ittabab.exception.NotFoundException;
import com.fivemybab.ittabab.user.command.application.dto.CreateCourseRequest;
import com.fivemybab.ittabab.user.command.application.dto.UpdateCourseRequest;
import com.fivemybab.ittabab.user.command.domain.aggregate.Course;
import com.fivemybab.ittabab.user.command.domain.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CourseCommandService {

    private final ModelMapper modelMapper;
    private final CourseRepository courseRepository;

    @Transactional
    public void createCourse(CreateCourseRequest createCourseRequest) {

        Course course = modelMapper.map(createCourseRequest, Course.class);
        courseRepository.save(course);
    }

    @Transactional
    public void modifyCourse(Long id, UpdateCourseRequest updateCourseRequest) {

        Course foundCourse = courseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("해당 id에 맞는 훈련 과정이 없습니다. code : " + id));
        foundCourse.modifyStart(updateCourseRequest.getStartDate());
        foundCourse.modifyEnd(updateCourseRequest.getEndDate());
        foundCourse.modifyName(updateCourseRequest.getClassName());
        foundCourse.modifyNum(updateCourseRequest.getSeasonNum());
    }

    @Transactional
    public void deleteCourse(Long id) {

        courseRepository.deleteById(id);
    }
}


