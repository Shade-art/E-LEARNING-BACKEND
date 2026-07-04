package org.example.jpa.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.AnyDiscriminatorImplicitValues;


@Data //this contains getters and setters + toString + equals and hashcode comparison it's ALL IN ONE
@NoArgsConstructor
@Entity //Hibernate detects this
public class Author {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "author_sequence" //give name to our sequence but hibernate won't find it
    )
    @SequenceGenerator(
            name = "author_sequence", //same as generator
            sequenceName = "Suiiiiiiii",//any name
            allocationSize = 1
    )

    private Integer id; //why Integer as its default value is null and int default value is 0

    private String firstName;

    private String lastName;

    private String email;

    private int age; // as age is important age = 0 can be detected but null throws error
}
