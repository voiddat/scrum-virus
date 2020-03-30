package com.madsoft.scrumvirus.command.datamodel;


import com.madsoft.scrumvirus.command.domain.course.repository.entities.Course;
import com.madsoft.scrumvirus.command.domain.course.repository.entities.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EnrollCourseDTO {
    private long id;
//    private long courseId;
//    private long userId;
    private Course course;
    private User user;
    private LocalDateTime finishDate;
}
