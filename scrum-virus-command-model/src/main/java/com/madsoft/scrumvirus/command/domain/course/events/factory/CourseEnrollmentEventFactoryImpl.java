package com.madsoft.scrumvirus.command.domain.course.events.factory;

import com.google.gson.Gson;
import com.madsoft.scrumvirus.command.datamodel.EnrollCourseDTO;
import com.madsoft.scrumvirus.command.domain.course.events.events.CourseEnrollmentUpdatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CourseEnrollmentEventFactoryImpl implements CourseEnrollmentEventFactory {
    private final Gson gson;

    @Override
    public String createCourseEnrollmentUpdatedEvent(EnrollCourseDTO enrollCourseDTO) {
        return gson.toJson(new CourseEnrollmentUpdatedEvent(enrollCourseDTO));
    }
}
