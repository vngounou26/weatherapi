package com.hesias.weatherapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @Column(name = "employee_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "weatherSequence")
    @SequenceGenerator(name = "weatherSequence", sequenceName = "weatherSequence", allocationSize = 1)
    private int id;

    private String name;
    private String email;
}
