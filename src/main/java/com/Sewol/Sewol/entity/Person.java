package com.Sewol.Sewol.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Long id;
    private String personName;

    @Enumerated(EnumType.STRING)
    private Job job;

    protected Person() {
    }

    public Person(String personName) {
        this.personName = personName;
    }

}
