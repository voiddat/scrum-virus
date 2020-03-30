package com.madsoft.scrumvirus.query.events;

import lombok.Data;

@Data
public class CourseUpdatedEvent {
    private final UpdateCourseDTO payload;
}
