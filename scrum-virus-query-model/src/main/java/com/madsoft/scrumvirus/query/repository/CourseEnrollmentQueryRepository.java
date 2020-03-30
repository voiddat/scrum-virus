package com.madsoft.scrumvirus.query.repository;

import com.madsoft.scrumvirus.query.datamodel.CourseEnrollmentDTO;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface CourseEnrollmentQueryRepository extends ReactiveMongoRepository<CourseEnrollmentDTO, Long> {
    Flux<CourseEnrollmentDTO> findByCourse_Id(long courseId);
}
