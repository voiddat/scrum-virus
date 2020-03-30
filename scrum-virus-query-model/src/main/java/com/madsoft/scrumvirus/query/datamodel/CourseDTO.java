package com.madsoft.scrumvirus.query.datamodel;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "Courses")
@Data
@Builder
public class CourseDTO {
    @Id
    private final Long id;
    private ScrumEvangelistDTO scrumEvangelist;
    //    private List<UserDTO> users;
    private List<CourseEnrollmentDTO> courseEnrollments;
    private LocalDateTime startDate;
    private LocalDateTime deadline;
}
