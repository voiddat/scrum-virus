package com.madsoft.scrumvirus.command.domain.course.events.events;

import com.madsoft.scrumvirus.command.datamodel.EnrollCourseDTO;
import lombok.Data;

@Data
public class CourseEnrollmentUpdatedEvent {
    private final EnrollCourseDTO payload;
}
