package com.madsoft.scrumvirus.query.datamodel;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Optional;

@Document(collection = "CourseEnrollments")
@Data
@Builder
public class CourseEnrollmentDTO {
    @Id
    private Long id;
    private CourseDTO course;
    private UserDTO user;
    private LocalDateTime finishTime;

    public Optional<LocalDateTime> getFinishTime() {
        return Optional.ofNullable(finishTime);
    }
}
