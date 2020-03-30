package com.madsoft.scrumvirus.command.datamodel;

import com.madsoft.scrumvirus.command.domain.course.repository.entities.ScrumEvangelist;
import com.madsoft.scrumvirus.command.domain.course.repository.entities.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class AddCourseDTO {
    private ScrumEvangelist scrumEvangelist;
    private List<User> users;
    private LocalDateTime startDate;
    private LocalDateTime deadline;
}
