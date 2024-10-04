package com.fivemybab.ittabab.user.query.service;

import com.fivemybab.ittabab.user.query.dto.CourseDto;
import com.fivemybab.ittabab.user.query.mapper.CourseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseQueryService {

    private final CourseMapper courseMapper;

    public List<CourseDto> findCourseByBootCamp(Long bootId) {
        return courseMapper.findCourseByBootCamp(bootId);
    }

    public List<CourseDto> findAll() {
        return courseMapper.findAll();
    }
}
