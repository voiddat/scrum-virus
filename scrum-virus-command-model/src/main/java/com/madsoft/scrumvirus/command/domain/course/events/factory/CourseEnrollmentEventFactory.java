package com.madsoft.scrumvirus.command.domain.course.events.factory;

import com.madsoft.scrumvirus.command.datamodel.EnrollCourseDTO;

public interface CourseEnrollmentEventFactory {
    String createCourseEnrollmentUpdatedEvent(EnrollCourseDTO enrollCourseDTO);
}
