package com.goit.spacetravel.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.time.ZonedDateTime;

@Entity
@Table(name = "ticket")
@Data
@NoArgsConstructor
@ToString
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="created_at")
    private ZonedDateTime createdAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id", referencedColumnName = "id", nullable = false)
    private Client clientId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "from_planet_id", referencedColumnName = "id", nullable = false)
    private Planet fromPlanetId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "to_planet_id", referencedColumnName = "id", nullable = false)
    private Planet toPlanetId;


}