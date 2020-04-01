package com.madsoft.scrumvirus.command;

import com.madsoft.scrumvirus.command.domain.course.repository.CourseRepository;
import com.madsoft.scrumvirus.command.domain.course.repository.ScrumEvangelistRepository;
import com.madsoft.scrumvirus.command.domain.course.repository.UserRepository;
import com.madsoft.scrumvirus.command.domain.course.repository.entities.Course;
import com.madsoft.scrumvirus.command.domain.course.repository.entities.ScrumEvangelist;
import com.madsoft.scrumvirus.command.domain.course.repository.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TestData implements InitializingBean {
    private final UserRepository userRepository;
    private final ScrumEvangelistRepository scrumEvangelistRepository;

    @Override
    public void afterPropertiesSet() {
        ScrumEvangelist scrumEvangelist = new ScrumEvangelist();
        scrumEvangelist.setUsername("Kowalski");
        scrumEvangelistRepository.save(scrumEvangelist);

        User nowak = new User();
        nowak.setUsername("Nowak");
        userRepository.save(nowak);

        User smith = new User();
        smith.setUsername("Smith");
        userRepository.save(smith);

        User nowicki = new User();
        nowicki.setUsername("Nowicki");
        userRepository.save(nowicki);
    }
}
