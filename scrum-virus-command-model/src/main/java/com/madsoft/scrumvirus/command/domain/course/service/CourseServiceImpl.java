package com.madsoft.scrumvirus.command.domain.course.service;

import com.madsoft.scrumvirus.command.datamodel.UpdateCourseDTO;
import com.madsoft.scrumvirus.command.domain.course.events.factory.CourseEventFactory;
import com.madsoft.scrumvirus.command.domain.course.repository.CourseRepository;
import com.madsoft.scrumvirus.command.domain.course.repository.ScrumEvangelistRepository;
import com.madsoft.scrumvirus.command.domain.course.repository.entities.Course;
import com.madsoft.scrumvirus.command.domain.course.repository.factory.JPACourseFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final JPACourseFactory jpaCourseFactory;
    private final JmsTemplate jmsTemplate;
    private final CourseEventFactory courseEventFactory;

    //TODO properties
    @Value("COURSE_UPDATED_QUEUE")
    private String courseUpdatedQueue;

    @Override
    @Transactional
    public UpdateCourseDTO updateCourse(UpdateCourseDTO updateCourseDTO) {
        Course course = jpaCourseFactory.createCourseOrThrowException(updateCourseDTO);
        course = courseRepository.save(course);
        updateCourseDTO.setId(course.getId());
        jmsTemplate.convertAndSend(courseUpdatedQueue, courseEventFactory.createCourseUpdatedEvent(updateCourseDTO));
        return updateCourseDTO;
    }

    @Override
    public Mono<Course> fetchCourseById(Long id) {
        Optional<Course> courseOpt = courseRepository.findById(id);
        return courseOpt.map(Mono::just).orElseThrow(IllegalArgumentException::new);
    }
}
