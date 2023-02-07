package com.example.FootballStats.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Table(name="player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String position;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nationality_id", referencedColumnName = "id")
    private Nationality nationality;
}
