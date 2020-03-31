package com.madsoft.scrumvirus.command.domain.course.repository.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Data
@Entity
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
}
