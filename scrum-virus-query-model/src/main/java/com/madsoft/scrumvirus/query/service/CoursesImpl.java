package com.madsoft.scrumvirus.query.service;

import com.madsoft.scrumvirus.query.datamodel.CourseDTO;
import com.madsoft.scrumvirus.query.repository.CourseQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CoursesImpl implements Courses {
    private final CourseQueryRepository courseQueryRepository;

    @Override
    public Mono<CourseDTO> fetchCourseById(Long id) {
//        User user = new User();
//        user.setId(1L);
//        user.setUsername("abc");
//        Map<User, Boolean> users = new HashMap<>();
//        users.put(user, false);
//        CourseDTO course = CourseDTO.builder()
//                .users(users).build();
//
//        return Mono.just(course);
        return courseQueryRepository.findById(id);
    }

    @Override
    public Flux<CourseDTO> fetchAllCourses() {
        return courseQueryRepository.findAll();
    }
}
