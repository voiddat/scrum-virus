package com.madsoft.scrumvirus.query.events;

import lombok.Data;

@Data
public class CourseEnrollmentUpdatedEvent {
    private final EnrollCourseDTO payload;
}
