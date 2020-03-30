package com.madsoft.scrumvirus.query.events.listener;

import com.google.gson.Gson;
import com.madsoft.scrumvirus.query.datamodel.CourseEnrollmentDTO;
import com.madsoft.scrumvirus.query.events.CourseEnrollmentUpdatedEvent;
import com.madsoft.scrumvirus.query.repository.CourseEnrollmentQueryRepository;
import com.madsoft.scrumvirus.query.repository.CourseQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CourseEnrollmentUpdatedEventListener {
    private final CourseEnrollmentQueryRepository courseEnrollmentQueryRepository;
    private final CourseQueryRepository courseRepository;
    //    private final UserRepository userRepository;
    private static final Gson GSON = new Gson();

    @JmsListener(destination = "COURSE_ENROLLMENT_UPDATED_QUEUE")
    public void receiveMessage(String message) {
        final CourseEnrollmentUpdatedEvent event = GSON.fromJson(message, CourseEnrollmentUpdatedEvent.class);
        CourseEnrollmentDTO courseEnrollment = CourseEnrollmentDTO.builder()
                .id(event.getPayload().getId())
                .course(event.getPayload().getCourse())
                .user(event.getPayload().getUser())
                .finishTime(event.getPayload().getFinishDate())
                .build();

        courseEnrollmentQueryRepository.save(courseEnrollment)
                .zipWith(courseRepository.findById(courseEnrollment.getCourse().getId()))
                .subscribe(tuple -> {
                    tuple.getT2().getCourseEnrollments().add(tuple.getT1());
                    courseRepository.save(tuple.getT2()).subscribe();
                });
    }
}
