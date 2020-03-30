package com.madsoft.scrumvirus.command.domain.course.service;

import com.madsoft.scrumvirus.command.datamodel.EnrollCourseDTO;

public interface CourseEnrollmentService {
    EnrollCourseDTO enrollCourse(EnrollCourseDTO enrollCourseDTO);

    EnrollCourseDTO finishCourse(EnrollCourseDTO enrollCourseDTO);
}
