package com.smalaca.jpa.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@DiscriminatorValue(value = "EMPLOYEE")
public class Employee extends Person {
    private LocalDate workStartDate;
}
