package com.madsoft.scrumvirus.query.service

import com.madsoft.scrumvirus.query.datamodel.CourseDTO
import com.madsoft.scrumvirus.query.datamodel.CourseEnrollmentDTO
import com.madsoft.scrumvirus.query.datamodel.UserDTO
import com.madsoft.scrumvirus.query.repository.CourseQueryRepository
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import spock.lang.Specification

import java.time.LocalDateTime
import java.time.Month

class UsersImplTest extends Specification {

    CourseQueryRepository courseQueryRepository = Mock()
    CourseDTO courseDTO = Mock()
    CourseEnrollmentDTO courseEnrollmentDTOOverdue = Mock()
    CourseEnrollmentDTO courseEnrollmentDTOTimely = Mock()
    UserDTO userDTOOverdue = new UserDTO()
    UserDTO userDTOTimely = new UserDTO()

    def "should return one overdue user"() {
        given:
        userDTOTimely.username = 'Kowalski'
        userDTOOverdue.username = 'Nowak'
        courseEnrollmentDTOTimely.getFinishTime() >> Optional.of(LocalDateTime.of(2019, Month.APRIL, 12, 12, 20))
        courseEnrollmentDTOOverdue.getFinishTime() >> Optional.of(LocalDateTime.of(2021, Month.APRIL, 20, 20, 20))
        courseEnrollmentDTOTimely.getUser() >> userDTOTimely
        courseEnrollmentDTOOverdue.getUser() >> userDTOOverdue
        courseDTO.getDeadline() >> LocalDateTime.of(2020, Month.APRIL, 20, 20, 20)
        courseDTO.getCourseEnrollments() >> Arrays.asList(courseEnrollmentDTOOverdue, courseEnrollmentDTOTimely)
        courseQueryRepository.findById(_) >> Mono.just(courseDTO)
        def usersImpl = new UsersImpl(courseQueryRepository)

        when:
        def source = usersImpl.fetchOverdueUsersForGivenCourse(1L)

        then:
        StepVerifier.create(source)
                .expectNextMatches(user -> user.username == 'Nowak')
                .expectComplete()
                .verify()
    }

}
