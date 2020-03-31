package com.madsoft.scrumvirus.query.datamodel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "Courses")
@Data
@Builder
@AllArgsConstructor
public class CourseDTO {
    @Id
    private final Long id;
    private final ScrumEvangelistDTO scrumEvangelist;
    private final List<CourseEnrollmentDTO> courseEnrollments;
    private final LocalDateTime startDate;
    private final LocalDateTime deadline;
}
