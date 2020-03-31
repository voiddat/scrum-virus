package com.madsoft.scrumvirus.command.domain.course.service;

import com.madsoft.scrumvirus.command.datamodel.EnrollCourseDTO;
import com.madsoft.scrumvirus.command.domain.course.events.factory.CourseEnrollmentEventFactory;
import com.madsoft.scrumvirus.command.domain.course.repository.CourseEnrollmentRepository;
import com.madsoft.scrumvirus.command.domain.course.repository.CourseRepository;
import com.madsoft.scrumvirus.command.domain.course.repository.UserRepository;
import com.madsoft.scrumvirus.command.domain.course.repository.entities.CourseEnrollment;
import com.madsoft.scrumvirus.command.domain.course.repository.factory.JPACourseEnrollmentFactory;
import com.madsoft.scrumvirus.command.props.MQProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CourseEnrollmentServiceImpl implements CourseEnrollmentService {
    private final CourseEnrollmentRepository courseEnrollmentRepository;
    private final JPACourseEnrollmentFactory jpaCourseEnrollmentFactory;
    private final JmsTemplate jmsTemplate;
    private final CourseEnrollmentEventFactory courseEnrollmentEventFactory;
    private final MQProperties mqProperties;

    @Override
    @Transactional
    public EnrollCourseDTO enrollCourse(EnrollCourseDTO enrollCourseDTO) {
        CourseEnrollment courseEnrollment = courseEnrollmentRepository.save(
                jpaCourseEnrollmentFactory.createCourseEnrollmentOrThrowException(enrollCourseDTO)
        );
        enrollCourseDTO.setId(courseEnrollment.getId());
        enrollCourseDTO.setCourse(courseEnrollment.getCourse());
        enrollCourseDTO.setUser(courseEnrollment.getUser());
        enrollCourseDTO.setFinishDate(courseEnrollment.getFinishDate().orElse(null));
        jmsTemplate.convertAndSend(
                mqProperties.getCourseEnrollmentUpdatedQueue(),
                courseEnrollmentEventFactory.createCourseEnrollmentUpdatedEvent(enrollCourseDTO)
        );
        return enrollCourseDTO;
    }

    @Override
    @Transactional
    public EnrollCourseDTO finishCourse(EnrollCourseDTO enrollCourseDTO) {
        CourseEnrollment courseEnrollment = jpaCourseEnrollmentFactory.createCourseEnrollmentOrThrowException(enrollCourseDTO);
        courseEnrollment = courseEnrollmentRepository.findByCourseAndUser(
                courseEnrollment.getCourse(),
                courseEnrollment.getUser()
        ).orElseThrow(() -> new IllegalArgumentException("CourseEnrollment not found"));
        courseEnrollment.setFinishDate(LocalDateTime.now());
        courseEnrollmentRepository.save(courseEnrollment);
        jmsTemplate.convertAndSend(
                mqProperties.getCourseEnrollmentUpdatedQueue(),
                courseEnrollmentEventFactory.createCourseEnrollmentUpdatedEvent(enrollCourseDTO)
        );
        return enrollCourseDTO;
    }
}
