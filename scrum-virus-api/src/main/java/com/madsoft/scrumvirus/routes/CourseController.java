package com.madsoft.scrumvirus.routes;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import com.madsoft.scrumvirus.handler.CommandHandler;
import com.madsoft.scrumvirus.handler.QueryHandler;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CourseController {
    private final QueryHandler queryHandler;
    private final CommandHandler commandHandler;

    @Bean
    public RouterFunction<ServerResponse> route() {
        return RouterFunctions
                .route(POST("/course"), commandHandler::addOrUpdateCourse)
                .andRoute(POST("/course/{courseId}/enroll/{userId}"), commandHandler::enrollCourse)
                .andRoute(POST("/course/{courseId}/finish/{userId}"), commandHandler::finishCourse)
                .andRoute(GET("/course"), queryHandler::fetchAllCourses)
                .andRoute(GET("/course/{id}"), queryHandler::fetchCourse)
                .andRoute(GET("/course/{id}/overdue"), queryHandler::fetchOverdueUsers);
    }
}
