package com.goit.spacetravel.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Table(name = "planet")
@Data
@NoArgsConstructor
@ToString
public class Planet {

    @Id
    private String id; // Should be upper case like "MARS"

    @Column(name = "name", nullable = false, length = 500)
    private String name;

    // Constructors, getters, setters
}
