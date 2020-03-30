package com.madsoft.scrumvirus.command.domain.course.repository.factory

import com.madsoft.scrumvirus.command.datamodel.UpdateCourseDTO
import com.madsoft.scrumvirus.command.domain.course.exceptions.CourseInvalidException
import com.madsoft.scrumvirus.command.domain.course.repository.entities.Course
import com.madsoft.scrumvirus.command.domain.course.repository.entities.ScrumEvangelist
import spock.lang.Specification

import java.time.LocalDateTime

class JPACourseFactoryTest extends Specification {

    JPACourseFactory jpaCourseFactory = new JPACourseFactoryImpl()
    UpdateCourseDTO updateCourseDTO = new UpdateCourseDTO()

    def "deadline earlier than start date"() {
        given:
        updateCourseDTO.startDate = LocalDateTime.MAX
        updateCourseDTO.deadline = LocalDateTime.MIN

        when:
        jpaCourseFactory.createCourseOrThrowException(updateCourseDTO)

        then:
        CourseInvalidException ex = thrown()
        ex.message == "Invalid dates"
    }

    def "no scrum evangelist"() {
        given:
        updateCourseDTO.startDate = LocalDateTime.MIN
        updateCourseDTO.deadline = LocalDateTime.MAX

        when:
        jpaCourseFactory.createCourseOrThrowException(updateCourseDTO)

        then:
        CourseInvalidException ex = thrown()
        ex.message == "No scrumEvangelist"
    }

    //todo test case with username not within list

    def "should create Course"() {
        given:
        updateCourseDTO.startDate = LocalDateTime.MIN
        updateCourseDTO.deadline = LocalDateTime.MAX
        updateCourseDTO.scrumEvangelist = new ScrumEvangelist()

        when:
        Course course = jpaCourseFactory.createCourseOrThrowException(updateCourseDTO)

        then:
        1==1
    }
}
