package com.madsoft.scrumvirus.command.domain.course.repository.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.MERGE)
    private ScrumEvangelist scrumEvangelist;

    @OneToMany
    private List<CourseEnrollment> courseEnrollments;

    @Column
    private LocalDateTime startDate;

    @Column
    private LocalDateTime deadline;

    public static Course withId(String id) {
        return Course.builder()
                .id(Long.parseLong(id))
                .build();
    }
}
