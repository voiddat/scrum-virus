package com.madsoft.scrumvirus.command.domain.course.events.factory;

import com.madsoft.scrumvirus.command.datamodel.UpdateCourseDTO;

public interface CourseEventFactory {
    String createCourseUpdatedEvent(UpdateCourseDTO updateCourseDTO);
}
