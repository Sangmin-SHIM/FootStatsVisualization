package com.example.FootballStats.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Table(name="ranking_league")
public class RankingLeague {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long year;

    private Long rank;

    private Float point;

    private Long nb_match;

    private Long nb_team;

    private Float year_1_point;

    private Float year_2_point;

    private Float year_3_point;

    private Float year_4_point;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "league_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"clubs","hibernateLazyInitializer"})
    private League league;

}
