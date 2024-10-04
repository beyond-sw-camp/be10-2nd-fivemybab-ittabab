package com.fivemybab.ittabab.user.query.mapper;

import com.fivemybab.ittabab.user.query.dto.CourseDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseMapper {

    List<CourseDto> findCourseByBootCamp(Long bootId);

    List<CourseDto> findAll();
}
