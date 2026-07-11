package org.example.jpa.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Lecture extends BaseEntity {



    @ManyToOne
    @JoinColumn(name="section_id")
    private Section section;



    @OneToOne
    @JoinColumn(name="resource_id")
    private Resource resources;
}
