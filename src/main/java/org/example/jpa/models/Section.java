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
public class Section extends BaseEntity {



    private String name;

    private int sectionOrder;

    @ManyToOne
    @JoinColumn(name="course_id ")
    private Course courses;

    @OneToMany(mappedBy = "section")
    private List<Lecture> lectures;
}
