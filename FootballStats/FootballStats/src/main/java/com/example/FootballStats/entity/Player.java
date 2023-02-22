package com.example.FootballStats.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nationality_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"players","hibernateLazyInitializer"})
    private Nationality nationality;
}
