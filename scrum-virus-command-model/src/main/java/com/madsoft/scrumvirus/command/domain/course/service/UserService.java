package com.madsoft.scrumvirus.command.domain.course.service;

import com.madsoft.scrumvirus.command.domain.course.repository.entities.User;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<User> getUserById(Long id);
}
