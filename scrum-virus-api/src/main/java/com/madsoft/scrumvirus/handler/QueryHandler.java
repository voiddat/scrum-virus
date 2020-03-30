package com.madsoft.scrumvirus.handler;

import com.madsoft.scrumvirus.query.datamodel.CourseDTO;
import com.madsoft.scrumvirus.query.datamodel.UserDTO;
import com.madsoft.scrumvirus.query.service.Courses;
import com.madsoft.scrumvirus.query.service.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class QueryHandler {
    private final Courses courses;
    private final Users users;

    public Mono<ServerResponse> fetchCourse(ServerRequest serverRequest) {
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromPublisher(courses.fetchCourseById(Long.parseLong(serverRequest.pathVariable("id"))), CourseDTO.class));
    }

    public Mono<ServerResponse> fetchOverdueUsers(ServerRequest serverRequest) {
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromPublisher(users.fetchOverdueUsersForGivenCourse(Long.parseLong(serverRequest.pathVariable("id"))), UserDTO.class));
    }

    public Mono<ServerResponse> fetchAllCourses(ServerRequest serverRequest) {
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromPublisher(courses.fetchAllCourses(), CourseDTO.class));
    }
}
