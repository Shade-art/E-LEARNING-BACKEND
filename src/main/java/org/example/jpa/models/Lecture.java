package org.example.jpa.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Lecture {

    @Id
    @GeneratedValue
    private Integer id;


    @ManyToOne
    @JoinColumn(name="section_id")
    private Section section;



    @OneToOne
    @JoinColumn(name="resource_id")
    private Resource resources;
}
