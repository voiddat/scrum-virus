package com.madsoft.scrumvirus.query.service;

import com.madsoft.scrumvirus.query.datamodel.CourseDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface Courses {
    Mono<CourseDTO> fetchCourseById(Long id);
    Flux<CourseDTO> fetchAllCourses();
}
