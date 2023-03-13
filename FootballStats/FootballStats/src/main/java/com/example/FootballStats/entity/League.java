package com.example.FootballStats.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Table(name="league")
public class League {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "league",fetch=FetchType.LAZY)
    @JsonManagedReference
    @JsonIgnoreProperties("league")
    private List<Club> clubs;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nationality_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"players","hibernateLazyInitializer"})
    private Nationality nationality;
}
