package com.madsoft.scrumvirus.query.service;

import com.madsoft.scrumvirus.query.datamodel.UserDTO;
import reactor.core.publisher.Flux;

public interface Users {
    Flux<UserDTO> fetchOverdueUsersForGivenCourse(long courseId);
}
