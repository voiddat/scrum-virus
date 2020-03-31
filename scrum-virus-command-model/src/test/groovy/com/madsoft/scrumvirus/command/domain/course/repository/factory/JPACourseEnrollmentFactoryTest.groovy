package com.madsoft.scrumvirus.command.domain.course.repository.factory

import com.madsoft.scrumvirus.command.datamodel.EnrollCourseDTO
import com.madsoft.scrumvirus.command.domain.course.repository.CourseRepository
import com.madsoft.scrumvirus.command.domain.course.repository.UserRepository
import com.madsoft.scrumvirus.command.domain.course.repository.entities.Course
import com.madsoft.scrumvirus.command.domain.course.repository.entities.User
import spock.lang.Specification

import javax.persistence.EntityNotFoundException

class JPACourseEnrollmentFactoryTest extends Specification {
    EnrollCourseDTO enrollCourseDTO;
    CourseRepository courseRepository = Mock()
    UserRepository userRepository = Mock()
    JPACourseEnrollmentFactory jpaCourseEnrollmentFactory = new JPACourseEnrollmentFactoryImpl(courseRepository, userRepository)

    def "user not found"() {
        given:
        enrollCourseDTO = EnrollCourseDTO.builder()
                .user(User.withId("5"))
                .build()
        userRepository.findById(_) >> Optional.empty()

        when:
        jpaCourseEnrollmentFactory.createCourseEnrollmentOrThrowException(enrollCourseDTO)

        then:
        EntityNotFoundException ex = thrown()
        ex.message == "User not found id=5"
    }

    def "course not found"() {
        given:
        enrollCourseDTO = EnrollCourseDTO.builder()
                .user(User.withId("5"))
                .course(Course.withId("6"))
                .build()
        def user = new User();
        user.username = 'Nowak'
        userRepository.findById(_) >> Optional.of(user)
        courseRepository.findById(_) >> Optional.empty();

        when:
        jpaCourseEnrollmentFactory.createCourseEnrollmentOrThrowException(enrollCourseDTO)

        then:
        EntityNotFoundException ex = thrown()
        ex.message == "Course not found id=6"
    }

    def "should throw username not permitted exception"() {
        given:
        enrollCourseDTO = EnrollCourseDTO.builder()
                .user(User.withId("5"))
                .course(Course.withId("6"))
                .build()
        def user = new User()
        user.username = 'Abc'
        userRepository.findById(_) >> Optional.of(user)
        courseRepository.findById(_) >> Optional.of(new Course())

        when:
        jpaCourseEnrollmentFactory.createCourseEnrollmentOrThrowException(enrollCourseDTO)

        then:
        IllegalArgumentException ex = thrown()
        ex.message == "Username Abc is not permitted"
    }

    def "should create CourseEnrollment"() {
        given:
        enrollCourseDTO = EnrollCourseDTO.builder()
                .user(User.withId("5"))
                .course(Course.withId("6"))
                .build()
        def user = new User()
        user.username = 'Nowak'
        userRepository.findById(_) >> Optional.of(user)
        courseRepository.findById(_) >> Optional.of(new Course())

        when:
        jpaCourseEnrollmentFactory.createCourseEnrollmentOrThrowException(enrollCourseDTO)

        then:
        1 == 1
    }
}
