package org.example.jpa.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data //this contains getters and setters + toString + equals and hashcode comparison its ALL IN ONE
@NoArgsConstructor
@Entity //Hibernate detects this
public class Author {
    @Id
    private Integer id; //why Integer as its default value is null and int default value is 0

    private String firstName;

    private String lastName;

    private String email;

    private int age; // as age is important age = 0 can be detected but null throws error
}
