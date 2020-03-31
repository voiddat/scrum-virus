package com.madsoft.scrumvirus.handler;

import com.madsoft.scrumvirus.command.datamodel.EnrollCourseDTO;
import com.madsoft.scrumvirus.command.datamodel.UpdateCourseDTO;
import com.madsoft.scrumvirus.command.domain.course.repository.entities.Course;
import com.madsoft.scrumvirus.command.domain.course.repository.entities.User;
import com.madsoft.scrumvirus.command.domain.course.service.CourseEnrollmentService;
import com.madsoft.scrumvirus.command.domain.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;
import static org.springframework.web.reactive.function.server.ServerResponse.badRequest;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CommandHandler {
    private final CourseService courseService;
    private final CourseEnrollmentService courseEnrollmentService;

    public Mono<ServerResponse> addOrUpdateCourse(ServerRequest serverRequest) {
        return serverRequest
                .bodyToMono(UpdateCourseDTO.class)
                .map(courseService::updateCourse)
                .flatMap(CommandHandler::createResponseWithUpdateCourseDTO)
                .onErrorResume(CommandHandler::createExceptionResponse);
    }

    public Mono<ServerResponse> enrollCourse(ServerRequest serverRequest) {
        String courseId = serverRequest.pathVariable("courseId");
        String userId = serverRequest.pathVariable("userId");
        EnrollCourseDTO enrollCourseDTO = EnrollCourseDTO.builder()
                .course(Course.withId(courseId))
                .user(User.withId(userId))
                .build();

        return Mono.just(enrollCourseDTO)
                .map(courseEnrollmentService::enrollCourse)
                .flatMap(CommandHandler::createResponseWithEnrollCourseDTO)
                .onErrorResume(CommandHandler::createExceptionResponse);
    }

    public Mono<ServerResponse> finishCourse(ServerRequest serverRequest) {
        return serverRequest
                .bodyToMono(EnrollCourseDTO.class)
                .map(courseEnrollmentService::finishCourse)
                .flatMap(CommandHandler::createResponseWithEnrollCourseDTO)
                .onErrorResume(CommandHandler::createExceptionResponse);
    }

    private static Mono<ServerResponse> createResponseWithEnrollCourseDTO(EnrollCourseDTO enrollCourseDTO) {
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromValue(enrollCourseDTO));
    }

    private static Mono<ServerResponse> createResponseWithUpdateCourseDTO(UpdateCourseDTO updateCourseDTO) {
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromValue(updateCourseDTO));
    }

    private static Mono<ServerResponse> createExceptionResponse(Throwable exception) {
        return badRequest()
                .body(fromValue(new RuntimeException(exception.getMessage())));
    }

}
