package com.madsoft.scrumvirus.command.domain.course.repository.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Inheritance
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String username;

    public static List<String> userList = Arrays.asList(
            "Kowalski",
            "Nowak",
            "Smith"
    );

    public static User withId(String id) {
        return User.builder()
                .id(Long.parseLong(id))
                .build();
    }
}
