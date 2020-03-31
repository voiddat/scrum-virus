package com.madsoft.scrumvirus.command.domain.course.repository.factory;

import com.madsoft.scrumvirus.command.datamodel.UpdateCourseDTO;
import com.madsoft.scrumvirus.command.domain.course.exceptions.CourseInvalidException;
import com.madsoft.scrumvirus.command.domain.course.repository.entities.Course;
import com.madsoft.scrumvirus.command.domain.course.repository.entities.CourseEnrollment;
import com.madsoft.scrumvirus.command.domain.course.repository.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JPACourseFactoryImpl implements JPACourseFactory {
    @Override
    public Course createCourseOrThrowException(UpdateCourseDTO updateCourseDTO) {
        if (updateCourseDTO.getDeadline().isBefore(updateCourseDTO.getStartDate())) {
            log.error("Error creating Course (deadline is before startDate), id={}", updateCourseDTO.getId());
            throw new CourseInvalidException("Invalid dates");
        }

        if (updateCourseDTO.getScrumEvangelist() == null) {
            log.error("Error creating Course (no scrumEvangelist)");
            throw new CourseInvalidException("No scrumEvangelist");
        }

        if (updateCourseDTO.getCourseEnrollments() != null
                && !updateCourseDTO.getCourseEnrollments()
                .stream()
                .map(CourseEnrollment::getUser)
                .allMatch(user -> User.userList.contains(user.getUsername()))) {
            log.error("Error creating Course (incorrect username in CourseEnrollments)");
            throw new CourseInvalidException("Incorrect username");
        }

        return Course.builder()
                .id(updateCourseDTO.getId())
                .startDate(updateCourseDTO.getStartDate())
                .deadline(updateCourseDTO.getDeadline())
                .scrumEvangelist(updateCourseDTO.getScrumEvangelist())
                .courseEnrollments(updateCourseDTO.getCourseEnrollments())
                .build();
    }
}
