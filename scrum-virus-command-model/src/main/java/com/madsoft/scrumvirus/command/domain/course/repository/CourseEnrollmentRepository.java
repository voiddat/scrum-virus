package com.madsoft.scrumvirus.command.domain.course.repository;

import com.madsoft.scrumvirus.command.domain.course.repository.entities.Course;
import com.madsoft.scrumvirus.command.domain.course.repository.entities.CourseEnrollment;
import com.madsoft.scrumvirus.command.domain.course.repository.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseEnrollmentRepository extends JpaRepository<CourseEnrollment, Long> {
    Optional<CourseEnrollment> findByCourseAndUser(Course course, User user);
}
