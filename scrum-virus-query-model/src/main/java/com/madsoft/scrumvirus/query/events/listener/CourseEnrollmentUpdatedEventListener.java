package com.madsoft.scrumvirus.query.events.listener;

import com.google.gson.Gson;
import com.madsoft.scrumvirus.query.datamodel.CourseEnrollmentDTO;
import com.madsoft.scrumvirus.query.events.CourseEnrollmentUpdatedEvent;
import com.madsoft.scrumvirus.query.repository.CourseQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CourseEnrollmentUpdatedEventListener {
    private final CourseQueryRepository courseRepository;
    private final Gson gson;

    @JmsListener(destination = "#{@MQProperties.courseEnrollmentUpdatedQueue}")
    public void receiveMessage(String message) {
        final CourseEnrollmentUpdatedEvent event = gson.fromJson(message, CourseEnrollmentUpdatedEvent.class);
        CourseEnrollmentDTO courseEnrollment = CourseEnrollmentDTO.builder()
                .id(event.getPayload().getId())
                .course(event.getPayload().getCourse())
                .user(event.getPayload().getUser())
                .finishTime(event.getPayload().getFinishDate())
                .build();

        courseRepository.findById(courseEnrollment.getCourse().getId())
                .doOnNext(courseDTO -> courseDTO.getCourseEnrollments().add(courseEnrollment))
                .flatMap(courseRepository::save)
                .subscribe();
    }
}
