package com.madsoft.scrumvirus.command.domain.course.events.events;

import com.madsoft.scrumvirus.command.datamodel.UpdateCourseDTO;
import lombok.Data;

@Data
public class CourseUpdatedEvent {
    private final UpdateCourseDTO payload;
}
