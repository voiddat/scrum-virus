package com.madsoft.scrumvirus.command.domain.course.repository.factory

import com.madsoft.scrumvirus.command.datamodel.EnrollCourseDTO
import com.madsoft.scrumvirus.command.domain.course.repository.CourseRepository
import com.madsoft.scrumvirus.command.domain.course.repository.UserRepository
import com.madsoft.scrumvirus.command.domain.course.repository.entities.User
import spock.lang.Specification

import javax.persistence.EntityNotFoundException
import javax.swing.text.html.Option

class JPACourseEnrollmentFactoryTest extends Specification {
    JPACourseEnrollmentFactory jpaCourseEnrollmentFactory = new JPACourseEnrollmentFactoryImpl(courseRepository, userRepository)
    EnrollCourseDTO enrollCourseDTO = new EnrollCourseDTO()
    CourseRepository courseRepository = Mock()
    UserRepository userRepository = Mock()

    def "user not found"() {
        given:
        enrollCourseDTO.user = new User()
        enrollCourseDTO.user.id = 5L
        userRepository.findById(enrollCourseDTO.user.id) >> Optional.empty()

        when:
        jpaCourseEnrollmentFactory.createCourseEnrollmentOrThrowException(enrollCourseDTO)

        then:
        EntityNotFoundException ex = thrown()
        ex.message == "User not found"
    }
}
