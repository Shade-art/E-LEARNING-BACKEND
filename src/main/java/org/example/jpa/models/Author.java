package org.example.jpa.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data //this contains getters and setters + toString + equals and hashcode comparison it's ALL IN ONE
@NoArgsConstructor
@Entity //Hibernate detects this
public class Author {
    @Id
    @GeneratedValue
    private Integer id; //why Integer as its default value is null and int default value is 0

    private String firstName;

    private String lastName;

    @Column(
            unique = true,
            nullable = false
    )
    private String email;

    private int age; // as age is important age = 0 can be detected but null throws error

    @ManyToMany(mappedBy = "authors")
    private List <Course> courses;
}
