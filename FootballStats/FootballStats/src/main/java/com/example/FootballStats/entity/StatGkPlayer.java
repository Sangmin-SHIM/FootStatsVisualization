package com.example.FootballStats.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Table(name="stat_gk_player")
public class StatGkPlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long rank;

    private Long nb_game;

    private Long win;

    private Long draw;

    private Long lose;

    private Long game_start;

    private Float minute;
    private Float minute_90s;

    private Long ga;

    @Column(name = "ga90")
    private Float ga_90;

    private Long sota;

    private Long saves;

    @Column(name="save%")
    private Float save_per;

    private Long cs;

    @Column(name="cs%")
    private Float cs_per;

    private String season;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "club_id", referencedColumnName = "id")
    private Club club;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private Player player;

}
