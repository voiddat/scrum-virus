package com.madsoft.scrumvirus.query.service;

import com.madsoft.scrumvirus.query.datamodel.CourseDTO;
import com.madsoft.scrumvirus.query.datamodel.CourseEnrollmentDTO;
import com.madsoft.scrumvirus.query.datamodel.UserDTO;
import com.madsoft.scrumvirus.query.repository.CourseQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UsersImpl implements Users {
    private final CourseQueryRepository courseQueryRepository;

    @Override
    public Flux<UserDTO> fetchOverdueUsersForGivenCourse(long courseId) {
        Mono<CourseDTO> course = courseQueryRepository.findById(courseId);
        Flux<CourseEnrollmentDTO> courseEnrollments = course
                .map(CourseDTO::getCourseEnrollments)
                .flatMapMany(Flux::fromIterable);

        return courseEnrollments
                .filter(UsersImpl::isOverdue)
                .map(CourseEnrollmentDTO::getUser);
    }

    private static boolean isOverdue(CourseEnrollmentDTO courseEnrollmentDTO) {
        if (courseEnrollmentDTO.getFinishTime().isPresent()) {
            LocalDateTime deadline = courseEnrollmentDTO.getCourse().getDeadline();
            return courseEnrollmentDTO.getFinishTime().get().isAfter(deadline);
        } else {
            return LocalDateTime.now().isAfter(courseEnrollmentDTO.getCourse().getDeadline());
        }
    }

}
