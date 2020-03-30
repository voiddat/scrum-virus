package com.madsoft.scrumvirus.command.domain.course.service;

import com.madsoft.scrumvirus.command.datamodel.UpdateCourseDTO;
import com.madsoft.scrumvirus.command.domain.course.repository.entities.Course;
import reactor.core.publisher.Mono;

public interface CourseService {
    UpdateCourseDTO updateCourse(UpdateCourseDTO updateCourseDTO);

    Mono<Course> fetchCourseById(Long id);
}
