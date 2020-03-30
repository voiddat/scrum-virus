package com.madsoft.scrumvirus.command.domain.course.repository.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Data
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class ScrumEvangelist extends User {
    private String specialization;
}
