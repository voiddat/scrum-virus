package com.madsoft.scrumvirus.query.service

import com.madsoft.scrumvirus.query.datamodel.CourseDTO
import com.madsoft.scrumvirus.query.datamodel.CourseEnrollmentDTO
import com.madsoft.scrumvirus.query.datamodel.UserDTO
import com.madsoft.scrumvirus.query.repository.CourseEnrollmentQueryRepository
import com.madsoft.scrumvirus.query.repository.CourseQueryRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import spock.lang.Specification

import java.time.LocalDateTime

class UsersImplTest extends Specification {

    CourseEnrollmentQueryRepository courseEnrollmentQueryRepository = Mock()
    CourseQueryRepository courseQueryRepository = Mock()
    CourseDTO courseDTO = CourseDTO.builder().deadline(LocalDateTime.now()).build()
    CourseEnrollmentDTO courseEnrollmentDTOOverdue = Mock()
    CourseEnrollmentDTO courseEnrollmentDTOTimely = Mock()
    UserDTO userDTOOverdue = new UserDTO()
    UserDTO userDTOTimely = new UserDTO()
    UsersImpl usersImpl = new UsersImpl(courseQueryRepository, courseEnrollmentQueryRepository)
    
    def "should return one overdue user"() {
        given:
        userDTOOverdue.id = 8L
        userDTOOverdue.username = "Kowalski"
        userDTOTimely.id = 16L
        userDTOTimely.username = "Novak"
        courseQueryRepository.findById(_) >> Mono.just(courseDTO)
        courseEnrollmentDTOOverdue.getFinishTime() >> Optional.of(LocalDateTime.MAX)
        courseEnrollmentDTOOverdue.getUser() >> userDTOOverdue
        courseEnrollmentDTOTimely.getFinishTime() >> Optional.of(LocalDateTime.MIN)
        courseEnrollmentDTOTimely.getUser() >> userDTOTimely
        courseEnrollmentQueryRepository.findByCourse_Id(_) >> Flux.just(courseEnrollmentDTOOverdue, courseEnrollmentDTOTimely)

        when:
        def source = usersImpl.fetchOverdueUsersForGivenCourse(1L)

        then:
        StepVerifier.create(source)
        .expectNextMatches(user -> user.username == "Kowalski")
        .expectComplete().verify()
    }

}
