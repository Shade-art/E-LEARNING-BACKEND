package org.example.jpa.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Resource {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private Integer Size;

    private String url;

    @OneToOne(mappedBy = "resources")
    private Lecture lectures;
}
