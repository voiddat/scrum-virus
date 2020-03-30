package com.madsoft.scrumvirus.command.domain.course.repository.factory;

import com.madsoft.scrumvirus.command.datamodel.EnrollCourseDTO;
import com.madsoft.scrumvirus.command.domain.course.repository.CourseRepository;
import com.madsoft.scrumvirus.command.domain.course.repository.UserRepository;
import com.madsoft.scrumvirus.command.domain.course.repository.entities.Course;
import com.madsoft.scrumvirus.command.domain.course.repository.entities.CourseEnrollment;
import com.madsoft.scrumvirus.command.domain.course.repository.entities.ScrumEvangelist;
import com.madsoft.scrumvirus.command.domain.course.repository.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JPACourseEnrollmentFactoryImpl implements JPACourseEnrollmentFactory {
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    @Override
    public CourseEnrollment createCourseEnrollmentOrThrowException(EnrollCourseDTO enrollCourseDTO) {
        User user = userRepository.findById(enrollCourseDTO.getUser().getId()).orElseThrow(() -> new EntityNotFoundException("User not found id=" + enrollCourseDTO.getUser().getId()));
        Course course = courseRepository.findById(enrollCourseDTO.getCourse().getId()).orElseThrow(() -> new EntityNotFoundException("Course not found"));
//        Course course = Course.builder().deadline(LocalDateTime.now().minusDays(10L)).id(123L).scrumEvangelist(new ScrumEvangelist()).startDate(LocalDateTime.now().minusDays(12L)).users(new ArrayList<>()).build();
//        User user = userRepository.findById(enrollCourseDTO.getUserId()).orElseThrow(() -> new IllegalArgumentException("User not found"));
//        Course course = courseRepository.findById(enrollCourseDTO.getCourseId()).orElseThrow(() -> new IllegalArgumentException("Course not found"));

        return CourseEnrollment.builder()
                .course(course)
                .user(user)
                .build();
    }
}
