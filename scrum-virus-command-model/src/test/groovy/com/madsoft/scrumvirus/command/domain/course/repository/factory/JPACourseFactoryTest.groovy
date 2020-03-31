package com.madsoft.scrumvirus.command.domain.course.repository.factory

import com.madsoft.scrumvirus.command.datamodel.UpdateCourseDTO
import com.madsoft.scrumvirus.command.domain.course.exceptions.CourseInvalidException
import com.madsoft.scrumvirus.command.domain.course.repository.entities.CourseEnrollment
import com.madsoft.scrumvirus.command.domain.course.repository.entities.ScrumEvangelist
import com.madsoft.scrumvirus.command.domain.course.repository.entities.User
import spock.lang.Specification

import java.time.LocalDateTime

class JPACourseFactoryTest extends Specification {

    JPACourseFactory jpaCourseFactory = new JPACourseFactoryImpl()
    UpdateCourseDTO updateCourseDTO = new UpdateCourseDTO()

    def "deadline earlier than start date"() {
        given:
        updateCourseDTO = UpdateCourseDTO.builder()
                .startDate(LocalDateTime.MAX)
                .deadline(LocalDateTime.MIN)
                .build()

        when:
        jpaCourseFactory.createCourseOrThrowException(updateCourseDTO)

        then:
        CourseInvalidException ex = thrown()
        ex.message == "Invalid dates"
    }

    def "no scrum evangelist"() {
        given:
        updateCourseDTO = UpdateCourseDTO.builder()
                .startDate(LocalDateTime.MIN)
                .deadline(LocalDateTime.MAX)
                .build()

        when:
        jpaCourseFactory.createCourseOrThrowException(updateCourseDTO)

        then:
        CourseInvalidException ex = thrown()
        ex.message == "No scrumEvangelist"
    }

    def "username not within list"() {
        given:
        updateCourseDTO = UpdateCourseDTO.builder()
                .startDate(LocalDateTime.MIN)
                .deadline(LocalDateTime.MAX)
                .scrumEvangelist(new ScrumEvangelist())
                .courseEnrollments(new ArrayList<>())
                .build()
        def user = new User();
        user.username = 'Abc'
        updateCourseDTO.courseEnrollments.add(CourseEnrollment.builder()
                .user(user)
                .build()
        )

        when:
        jpaCourseFactory.createCourseOrThrowException(updateCourseDTO)

        then:
        CourseInvalidException ex = thrown()
        ex.message == 'Incorrect username'
    }

    def "should create Course"() {
        given:
        updateCourseDTO = UpdateCourseDTO.builder()
                .startDate(LocalDateTime.MIN)
                .deadline(LocalDateTime.MAX)
                .scrumEvangelist(new ScrumEvangelist())
                .courseEnrollments(new ArrayList<>())
                .build()
        def user = new User();
        user.username = 'Kowalski'
        updateCourseDTO.courseEnrollments.add(CourseEnrollment.builder()
                .user(user)
                .build()
        )

        when:
        jpaCourseFactory.createCourseOrThrowException(updateCourseDTO)

        then:
        1 == 1
    }
}
