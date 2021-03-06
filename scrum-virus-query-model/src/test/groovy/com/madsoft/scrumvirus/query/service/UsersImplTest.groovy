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
    UserDTO userDTOOverdue;
    UserDTO userDTOTimely;

    def "should return one overdue user"() {
        given:
        userDTOTimely = UserDTO.builder()
                .username('Kowalski')
                .build()

        userDTOOverdue = UserDTO.builder()
                .username('Nowak')
                .build();
        courseEnrollmentDTOTimely.getFinishTime() >> Optional.of(LocalDateTime.of(2019, Month.APRIL, 12, 12, 20))
        courseEnrollmentDTOOverdue.getFinishTime() >> Optional.of(LocalDateTime.of(2021, Month.APRIL, 20, 20, 20))
        courseEnrollmentDTOTimely.getUser() >> userDTOTimely
        courseEnrollmentDTOOverdue.getUser() >> userDTOOverdue
        courseEnrollmentDTOTimely.getCourse() >> courseDTO
        courseEnrollmentDTOOverdue.getCourse() >> courseDTO
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

    def "should return two overdue users"() {
        given:
        userDTOTimely = UserDTO.builder()
                .username('Kowalski')
                .build()

        userDTOOverdue = UserDTO.builder()
                .username('Nowak')
                .build();
        courseEnrollmentDTOTimely.getFinishTime() >> Optional.of(LocalDateTime.of(2021, Month.APRIL, 12, 12, 20))
        courseEnrollmentDTOOverdue.getFinishTime() >> Optional.of(LocalDateTime.of(2021, Month.APRIL, 20, 20, 20))
        courseEnrollmentDTOTimely.getUser() >> userDTOTimely
        courseEnrollmentDTOOverdue.getUser() >> userDTOOverdue
        courseEnrollmentDTOTimely.getCourse() >> courseDTO
        courseEnrollmentDTOOverdue.getCourse() >> courseDTO
        courseDTO.getDeadline() >> LocalDateTime.of(2020, Month.APRIL, 20, 20, 20)
        courseDTO.getCourseEnrollments() >> Arrays.asList(courseEnrollmentDTOOverdue, courseEnrollmentDTOTimely)
        courseQueryRepository.findById(_) >> Mono.just(courseDTO)
        def usersImpl = new UsersImpl(courseQueryRepository)

        when:
        def source = usersImpl.fetchOverdueUsersForGivenCourse(1L)

        then:
        StepVerifier.create(source)
                .expectNextMatches(user -> user.username == 'Nowak')
                .expectNextMatches(user -> user.username == 'Kowalski')
                .expectComplete()
                .verify()
    }
}
