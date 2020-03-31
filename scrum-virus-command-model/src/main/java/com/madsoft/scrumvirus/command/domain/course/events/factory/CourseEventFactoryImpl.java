package com.madsoft.scrumvirus.command.domain.course.events.factory;

import com.google.gson.Gson;

import com.madsoft.scrumvirus.command.datamodel.UpdateCourseDTO;
import com.madsoft.scrumvirus.command.domain.course.events.events.CourseUpdatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CourseEventFactoryImpl implements CourseEventFactory {
    private final Gson gson;

    @Override
    public String createCourseUpdatedEvent(UpdateCourseDTO updateCourseDTO) {
        return gson.toJson(new CourseUpdatedEvent(updateCourseDTO));
    }
}
