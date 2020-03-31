package com.madsoft.scrumvirus.command.datamodel;

import com.madsoft.scrumvirus.command.domain.course.repository.entities.CourseEnrollment;
import com.madsoft.scrumvirus.command.domain.course.repository.entities.ScrumEvangelist;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class UpdateCourseDTO {
    private final Long id;
    private final ScrumEvangelist scrumEvangelist;
    private final List<CourseEnrollment> courseEnrollments;
    private final LocalDateTime startDate;
    private final LocalDateTime deadline;

    public UpdateCourseDTO() {
        id = null;
        scrumEvangelist = null;
        courseEnrollments = null;
        startDate = null;
        deadline = null;
    }
}
