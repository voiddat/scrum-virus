package com.madsoft.scrumvirus.command.domain.course.service

import com.madsoft.scrumvirus.command.datamodel.UpdateCourseDTO
import com.madsoft.scrumvirus.command.domain.course.events.factory.CourseEventFactory
import com.madsoft.scrumvirus.command.domain.course.repository.CourseRepository
import com.madsoft.scrumvirus.command.domain.course.repository.entities.Course
import com.madsoft.scrumvirus.command.domain.course.repository.entities.ScrumEvangelist
import com.madsoft.scrumvirus.command.domain.course.repository.factory.JPACourseFactory
import org.springframework.jms.core.JmsTemplate
import spock.lang.Specification

import java.time.LocalDateTime

class CourseServiceImplTest extends Specification {
    def "shouldAddCourse"() {
        given:
        UpdateCourseDTO updateCourseDTO = new UpdateCourseDTO()
        updateCourseDTO.setDeadline(LocalDateTime.now())
        updateCourseDTO.setScrumEvangelist(new ScrumEvangelist())
        updateCourseDTO.setCourseEnrollments(new ArrayList<>())
        Course course = Mock()
        course.getId() >> 1L
        CourseRepository courseRepository = Mock()
        courseRepository.save(_) >> course
        JPACourseFactory jpaCourseFactory = Mock()
        jpaCourseFactory.createCourseOrThrowException(updateCourseDTO) >> course
        JmsTemplate jmsTemplate = Mock()
        CourseEventFactory courseEventFactory = Mock()
        def courseServiceImpl = new CourseServiceImpl(courseRepository, jpaCourseFactory, jmsTemplate, courseEventFactory)

        when:
        courseServiceImpl.updateCourse(updateCourseDTO)

        then:
        1 * jmsTemplate.convertAndSend(_, _)
    }
}
