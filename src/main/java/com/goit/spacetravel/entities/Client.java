package com.goit.spacetravel.entities;

import jakarta.persistence.*;


@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 200)
    private String name;

    // Constructors, getters, setters
}
