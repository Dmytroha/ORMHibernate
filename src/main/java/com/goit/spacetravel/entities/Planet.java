package com.goit.spacetravel.entities;

import jakarta.persistence.*;



@Entity
@Table(name = "planet")
public class Planet {

    @Id
    private String id; // Should be upper case like "MARS"

    @Column(name = "name", nullable = false, length = 500)
    private String name;

    // Constructors, getters, setters
}
