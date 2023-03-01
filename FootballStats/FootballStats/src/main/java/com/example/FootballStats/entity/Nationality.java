package com.example.FootballStats.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Table(name="nationality")
public class Nationality {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String name_short;

    private String name_original;
    @OneToMany(mappedBy = "nationality",fetch=FetchType.LAZY)
    @JsonManagedReference
    @JsonIgnoreProperties("nationality")
    private List<Player> players;

}
