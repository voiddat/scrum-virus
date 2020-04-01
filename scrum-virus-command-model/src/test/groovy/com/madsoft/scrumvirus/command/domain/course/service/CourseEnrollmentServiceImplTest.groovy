package com.madsoft.scrumvirus.command.domain.course.service

import com.madsoft.scrumvirus.command.datamodel.EnrollCourseDTO
import com.madsoft.scrumvirus.command.domain.course.events.events.CourseEnrollmentUpdatedEvent
import com.madsoft.scrumvirus.command.domain.course.events.factory.CourseEnrollmentEventFactory
import com.madsoft.scrumvirus.command.domain.course.repository.CourseEnrollmentRepository
import com.madsoft.scrumvirus.command.domain.course.repository.entities.Course
import com.madsoft.scrumvirus.command.domain.course.repository.entities.CourseEnrollment
import com.madsoft.scrumvirus.command.domain.course.repository.entities.User
import com.madsoft.scrumvirus.command.domain.course.repository.factory.JPACourseEnrollmentFactory
import com.madsoft.scrumvirus.command.props.MQProperties
import org.springframework.jms.core.JmsTemplate
import spock.lang.Specification

class CourseEnrollmentServiceImplTest extends Specification {

    CourseEnrollmentRepository courseEnrollmentRepository = Mock()
    JPACourseEnrollmentFactory jpaCourseEnrollmentFactory = Mock()
    JmsTemplate jmsTemplate = Mock()
    CourseEnrollmentEventFactory courseEnrollmentEventFactory = Mock()
    MQProperties mqProperties = Mock()
    CourseEnrollment courseEnrollment = Mock()
    Course course = new Course()
    User user = new User()
    CourseEnrollmentUpdatedEvent courseEnrollmentUpdatedEvent = Mock();

    def 'should enroll user to given course'() {
        given:
        courseEnrollment.getId() >> 1L
        courseEnrollment.getCourse() >> course
        courseEnrollment.getUser() >> user
        courseEnrollment.getFinishDate() >> Optional.empty()
        jpaCourseEnrollmentFactory.createCourseEnrollmentOrThrowException(_) >> courseEnrollment
        courseEnrollmentRepository.findByCourseAndUser(_, _) >> Optional.empty()
        mqProperties.getCourseEnrollmentUpdatedQueue() >> ""
        courseEnrollmentEventFactory.createCourseEnrollmentUpdatedEvent(_) >> courseEnrollmentUpdatedEvent
        CourseEnrollmentService courseEnrollmentService = new CourseEnrollmentServiceImpl(
                courseEnrollmentRepository,
                jpaCourseEnrollmentFactory,
                jmsTemplate,
                courseEnrollmentEventFactory,
                mqProperties
        )

        when:
        courseEnrollmentService.enrollCourse(new EnrollCourseDTO());

        then:
        1 * courseEnrollmentRepository.save(_) >> courseEnrollment
        1 * jmsTemplate.convertAndSend("", _)
    }

    def 'should trigger user is already enrolled validation'() {
        given:
        courseEnrollment.getCourse() >> course
        courseEnrollment.getUser() >> user
        courseEnrollment.getId() >> 1L
        courseEnrollment.getFinishDate() >> Optional.empty()
        jpaCourseEnrollmentFactory.createCourseEnrollmentOrThrowException(_) >> courseEnrollment
        courseEnrollmentRepository.findByCourseAndUser(_, _) >> Optional.of(courseEnrollment)
        CourseEnrollmentService courseEnrollmentService = new CourseEnrollmentServiceImpl(
                courseEnrollmentRepository,
                jpaCourseEnrollmentFactory,
                jmsTemplate,
                courseEnrollmentEventFactory,
                mqProperties
        )

        when:
        courseEnrollmentService.enrollCourse(new EnrollCourseDTO())

        then:
        IllegalArgumentException ex = thrown()
        ex.message == 'User is already enrolled to this course'

    }
}
