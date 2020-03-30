package com.madsoft.scrumvirus.query.repository;


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import com.madsoft.scrumvirus.query.datamodel.CourseDTO;

@Repository
public interface CourseQueryRepository extends ReactiveMongoRepository<CourseDTO, Long> {
}
