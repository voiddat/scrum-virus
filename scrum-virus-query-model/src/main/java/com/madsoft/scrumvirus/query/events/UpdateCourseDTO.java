package com.madsoft.scrumvirus.query.events;

import com.madsoft.scrumvirus.query.datamodel.CourseEnrollmentDTO;
import com.madsoft.scrumvirus.query.datamodel.ScrumEvangelistDTO;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UpdateCourseDTO {
    private Long id;
    private ScrumEvangelistDTO scrumEvangelist;
    //    private List<UserDTO> users;
    List<CourseEnrollmentDTO> courseEnrollments;
    private LocalDateTime startDate;
    private LocalDateTime deadline;
}
