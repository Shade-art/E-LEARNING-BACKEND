package org.example.jpa.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Resource extends BaseEntity {


    private String name;

    private Integer Size;

    private String url;

    @OneToOne
    @JoinColumn(name="lecture_id")
    private Lecture lectures;
}
