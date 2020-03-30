package com.madsoft.scrumvirus.command.domain.course.repository.factory;

import com.madsoft.scrumvirus.command.datamodel.UpdateCourseDTO;
import com.madsoft.scrumvirus.command.domain.course.repository.entities.Course;

public interface JPACourseFactory {
    Course createCourseOrThrowException(UpdateCourseDTO updateCourseDTO);
}
