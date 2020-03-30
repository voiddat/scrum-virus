package com.madsoft.scrumvirus.command.domain.course.events.factory;

import com.google.gson.Gson;

import com.madsoft.scrumvirus.command.datamodel.UpdateCourseDTO;
import com.madsoft.scrumvirus.command.domain.course.events.events.CourseUpdatedEvent;
import org.springframework.stereotype.Component;

@Component
public class CourseEventFactoryImpl implements CourseEventFactory {

    private static final Gson GSON = new Gson();

    @Override
    public String createCourseUpdatedEvent(UpdateCourseDTO updateCourseDTO) {
        return GSON.toJson(new CourseUpdatedEvent(updateCourseDTO));
    }
}
