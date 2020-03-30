package com.madsoft.scrumvirus.query.events.listener;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import com.madsoft.scrumvirus.query.datamodel.CourseDTO;
import com.madsoft.scrumvirus.query.events.CourseUpdatedEvent;
import com.madsoft.scrumvirus.query.repository.CourseQueryRepository;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CourseUpdatedEventListener {
    private final CourseQueryRepository courseQueryRepository;
    private static final Gson GSON = new Gson();

    @JmsListener(destination = "COURSE_UPDATED_QUEUE")
    public void receiveMessage(String courseUpdatedEvent) {
        final CourseUpdatedEvent event = GSON.fromJson(courseUpdatedEvent, CourseUpdatedEvent.class);
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
