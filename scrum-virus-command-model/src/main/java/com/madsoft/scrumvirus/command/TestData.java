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
    private final CourseRepository courseRepository;

    @Override
    public void afterPropertiesSet() throws Exception {
        User user = new User();
        user.setId(2L);
        user.setUsername("UserA");
        userRepository.save(user);
        ScrumEvangelist scrumEvangelist = new ScrumEvangelist();
        scrumEvangelist.setId(1L);
        scrumEvangelist.setUsername("kowalski");
        scrumEvangelistRepository.save(scrumEvangelist);
//        Course course = new Course();
//        course.setId(123L);
//        course.setDeadline(LocalDateTime.of(2020, Month.FEBRUARY, 1, 12, 30));
//        course.setScrumEvangelist(new ScrumEvangelist());
//        course.setStartDate(LocalDateTime.of(2019, Month.FEBRUARY, 1, 12, 30));
//        course.setUsers(new ArrayList<>());
//        Course course1 = courseRepository.saveAndFlush(course);
////        course = courseRepository.findById(123L).get();
//        System.out.println("abc");
    }
}
