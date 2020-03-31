package com.madsoft.scrumvirus.query.events;

import com.madsoft.scrumvirus.query.datamodel.CourseDTO;
import com.madsoft.scrumvirus.query.datamodel.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@Getter
public class EnrollCourseDTO {
    private final Long id;
    private final CourseDTO course;
    private final UserDTO user;
    private final LocalDateTime finishDate;

    public EnrollCourseDTO() {
        id = null;
        course = null;
        user = null;
        finishDate = null;
    }
}
