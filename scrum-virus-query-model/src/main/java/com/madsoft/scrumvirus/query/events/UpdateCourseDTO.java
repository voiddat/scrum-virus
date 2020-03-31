package com.madsoft.scrumvirus.query.events;

import com.madsoft.scrumvirus.query.datamodel.CourseEnrollmentDTO;
import com.madsoft.scrumvirus.query.datamodel.ScrumEvangelistDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@Getter
public class UpdateCourseDTO {
    private final Long id;
    private final ScrumEvangelistDTO scrumEvangelist;
    private final List<CourseEnrollmentDTO> courseEnrollments;
    private final LocalDateTime startDate;
    private final LocalDateTime deadline;
}
