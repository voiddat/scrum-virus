package com.madsoft.scrumvirus.command.domain.course.repository;


import com.madsoft.scrumvirus.command.domain.course.repository.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
