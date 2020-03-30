package com.madsoft.scrumvirus.command.domain.course.service;

import com.madsoft.scrumvirus.command.domain.course.exceptions.UserNotFoundException;
import com.madsoft.scrumvirus.command.domain.course.repository.UserRepository;
import com.madsoft.scrumvirus.command.domain.course.repository.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public Mono<User> getUserById(Long id) {
        Optional<User> userOpt = userRepository.findById(id);
        return userOpt.map(Mono::just).orElseThrow(UserNotFoundException::new);
    }
}
