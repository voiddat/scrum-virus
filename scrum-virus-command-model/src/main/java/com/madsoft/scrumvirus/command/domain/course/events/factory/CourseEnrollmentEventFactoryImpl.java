package com.madsoft.scrumvirus.command.domain.course.events.factory;

import com.google.gson.Gson;
import com.madsoft.scrumvirus.command.datamodel.EnrollCourseDTO;
import com.madsoft.scrumvirus.command.domain.course.events.events.CourseEnrollmentUpdatedEvent;
import org.springframework.stereotype.Component;

@Component
public class CourseEnrollmentEventFactoryImpl implements CourseEnrollmentEventFactory {

    private static final Gson GSON = new Gson();

    @Override
    public String createCourseEnrollmentUpdatedEvent(EnrollCourseDTO enrollCourseDTO) {
        return GSON.toJson(new CourseEnrollmentUpdatedEvent(enrollCourseDTO));
    }
}
