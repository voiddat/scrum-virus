package com.madsoft.scrumvirus.query.events;

import com.madsoft.scrumvirus.query.datamodel.CourseDTO;
import com.madsoft.scrumvirus.query.datamodel.UserDTO;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EnrollCourseDTO {
    private long id;
    //    private long courseId;
//    private long userId;
    private CourseDTO course;
    private UserDTO user;
    private LocalDateTime finishDate;
}
