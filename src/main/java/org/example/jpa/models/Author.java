package org.example.jpa.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data //this contains getters and setters + toString + equals and hashcode comparison it's ALL IN ONE
@NoArgsConstructor
@Entity //Hibernate detects this
public class Author extends BaseEntity {

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
