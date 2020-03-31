package com.madsoft.scrumvirus.command.datamodel;


import com.madsoft.scrumvirus.command.domain.course.repository.entities.Course;
import com.madsoft.scrumvirus.command.domain.course.repository.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
public class EnrollCourseDTO {
    private final Long id;
    private final Course course;
    private final User user;
    private final LocalDateTime finishDate;

    public EnrollCourseDTO() {
        id = null;
        course = null;
        user = null;
        finishDate = null;
    }
}
