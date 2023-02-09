package com.example.FootballStats.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Table(name="stat_league")
public class StatLeague {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long rank;

    private Long nb_game;

    private Long win;

    private Long draw;

    private Long lose;

    private Long point;

    private String season;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "club_id", referencedColumnName = "id")
    private Club club;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "league_id", referencedColumnName = "id")
    @JsonBackReference
    private League league;
}

