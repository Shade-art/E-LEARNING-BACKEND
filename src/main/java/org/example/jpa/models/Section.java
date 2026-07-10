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
public class Section {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private int sectionOrder;

    @ManyToOne
    @JoinColumn(name="course_id ")
    private Course courses;

    @OneToMany(mappedBy = "section")
    private List<Lecture> lectures;
}
