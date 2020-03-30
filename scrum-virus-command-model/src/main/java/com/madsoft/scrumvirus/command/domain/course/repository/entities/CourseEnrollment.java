package com.madsoft.scrumvirus.command.domain.course.repository.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseEnrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Course course;

    @OneToOne
    private User user;

    @Column
    private LocalDateTime finishDate;

    public Optional<LocalDateTime> getFinishDate() {
        return Optional.ofNullable(finishDate);
    }
}
