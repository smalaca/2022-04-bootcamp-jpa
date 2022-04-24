package com.smalaca.jpa.domain;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Employee extends Person {
    private LocalDate workStartDate;
}
