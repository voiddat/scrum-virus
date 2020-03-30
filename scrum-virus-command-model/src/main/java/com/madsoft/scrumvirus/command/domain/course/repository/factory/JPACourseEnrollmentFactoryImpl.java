package com.madsoft.scrumvirus.command.domain.course.repository.factory;

import com.madsoft.scrumvirus.command.datamodel.EnrollCourseDTO;
import com.madsoft.scrumvirus.command.domain.course.repository.CourseRepository;
import com.madsoft.scrumvirus.command.domain.course.repository.UserRepository;
import com.madsoft.scrumvirus.command.domain.course.repository.entities.Course;
import com.madsoft.scrumvirus.command.domain.course.repository.entities.CourseEnrollment;
import com.madsoft.scrumvirus.command.domain.course.repository.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JPACourseEnrollmentFactoryImpl implements JPACourseEnrollmentFactory {
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    @Override
    public CourseEnrollment createCourseEnrollmentOrThrowException(EnrollCourseDTO enrollCourseDTO) {
        User user = userRepository.findById(enrollCourseDTO.getUser().getId())
                .orElseThrow(() -> new EntityNotFoundException("User not found id=" + enrollCourseDTO.getUser().getId()));
        Course course = courseRepository.findById(enrollCourseDTO.getCourse().getId())
                .orElseThrow(() -> new EntityNotFoundException("Course not found id=" + enrollCourseDTO.getCourse().getId()));

        return CourseEnrollment.builder()
                .course(course)
                .user(user)
                .build();
    }
}
