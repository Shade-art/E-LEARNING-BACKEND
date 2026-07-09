package org.example.jpa.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    @GeneratedValue
    private Integer id;

    private String title;

    private String content;


    //making Courses the owner of our join table and is responsible for maintaining foreign key values in joint table
    @ManyToMany
    @JoinTable(
            name="authors_courses", //name of joint table
            joinColumns = { //first column of this new table should be of owner
                    @JoinColumn(name="courses_id")
            },
            inverseJoinColumns = {// second column should be of other
                    @JoinColumn(name="author_id")
            }
    )
    private List<Author> authors;

    @OneToMany(mappedBy = "course")
    private List<Section> sections;
}
