package com.madsoft.scrumvirus.command.domain.course.repository.factory;

import com.madsoft.scrumvirus.command.datamodel.EnrollCourseDTO;
import com.madsoft.scrumvirus.command.domain.course.repository.entities.CourseEnrollment;

public interface JPACourseEnrollmentFactory {
    CourseEnrollment createCourseEnrollmentOrThrowException(EnrollCourseDTO enrollCourseDTO);
}
