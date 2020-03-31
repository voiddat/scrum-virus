package com.madsoft.scrumvirus.command.props;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:mq.properties")
@Getter
public class MQProperties {
    @Value("${course.updated.queue}")
    private String courseUpdatedQueue;

    @Value("${course.enrollment.updated.queue}")
    private String courseEnrollmentUpdatedQueue;
}
