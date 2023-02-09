package com.example.FootballStats.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Table(name="stat_player")
public class StatPlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long nb_game;

    private Long game_start;

    private Long assist;

    private Long goal;

    private Float minute;

    private Float minute_90s;

    private Long yellow_card;

    private Long red_card;

    private String season;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "club_id", referencedColumnName = "id")
    private Club club;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private Player player;

}
