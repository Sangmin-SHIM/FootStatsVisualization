package com.example.FootballStats.controller;

import com.example.FootballStats.entity.StatGkPlayer;
import com.example.FootballStats.repo.StatGkPlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path="/stats_gk_player")
public class StatGkPlayerController {

    @Autowired
    StatGkPlayerRepository statGkPlayerRepository;

    @RequestMapping(path="", method= RequestMethod.GET)
    public List<StatGkPlayer> getAllStatsGkPlayer(){
        return (List<StatGkPlayer>) statGkPlayerRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @RequestMapping(path="/{stat_gk_id}", method= RequestMethod.GET)
    public StatGkPlayer getStatGkPlayerById (@PathVariable("stat_gk_id") Long stat_gk_id){
        Optional<StatGkPlayer> statGkPlayer = statGkPlayerRepository.findById(stat_gk_id);
        return statGkPlayer.orElse(null);
    }
}
