package org.example.jpa.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Course extends BaseEntity {



    private String title;

    private String content;


    //making Courses the owner of our join table and is responsible for maintaining foreign key values in joint table
    @ManyToMany
    @JoinTable( //making a separate table joining many to many relation
            name="authors_courses", //name of joint table
            joinColumns = { //first column of this new table should be of owner
                    @JoinColumn(name="courses_id")
            },
            inverseJoinColumns = {// second column should be of other
                    @JoinColumn(name="author_id")
            }
    )
    private List<Author> authors;

    @OneToMany(mappedBy = "courses")
    private List<Section> sections;
}
