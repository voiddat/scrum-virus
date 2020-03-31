package com.madsoft.scrumvirus.query.events.listener;

import com.google.gson.Gson;
import com.madsoft.scrumvirus.query.datamodel.CourseDTO;
import com.madsoft.scrumvirus.query.events.CourseUpdatedEvent;
import com.madsoft.scrumvirus.query.repository.CourseQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CourseUpdatedEventListener {
    private final CourseQueryRepository courseQueryRepository;
    private final Gson gson;

    @JmsListener(destination = "#{@MQProperties.courseUpdatedQueue}")
    public void receiveMessage(String courseUpdatedEvent) {
        final CourseUpdatedEvent event = gson.fromJson(courseUpdatedEvent, CourseUpdatedEvent.class);
        courseQueryRepository.save(
                CourseDTO.builder()
                        .id(event.getPayload().getId())
                        .courseEnrollments(event.getPayload().getCourseEnrollments())
                        .deadline(event.getPayload().getDeadline())
                        .scrumEvangelist(event.getPayload().getScrumEvangelist())
                        .startDate(event.getPayload().getStartDate())
                        .build()
        ).subscribe();
    }
}
