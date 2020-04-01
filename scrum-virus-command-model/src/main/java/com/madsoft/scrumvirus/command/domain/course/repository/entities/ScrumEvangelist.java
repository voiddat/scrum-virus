package com.madsoft.scrumvirus.command.domain.course.repository.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Inheritance
public class ScrumEvangelist extends User {
}
