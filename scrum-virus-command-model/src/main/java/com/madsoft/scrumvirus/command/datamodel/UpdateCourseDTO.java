package com.madsoft.scrumvirus.command.datamodel;

import com.madsoft.scrumvirus.command.domain.course.repository.entities.CourseEnrollment;
import com.madsoft.scrumvirus.command.domain.course.repository.entities.ScrumEvangelist;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UpdateCourseDTO {
    private Long id;
    private ScrumEvangelist scrumEvangelist;
    //    private List<User> users;
    private List<CourseEnrollment> courseEnrollments;
    private LocalDateTime startDate;
    private LocalDateTime deadline;
}
