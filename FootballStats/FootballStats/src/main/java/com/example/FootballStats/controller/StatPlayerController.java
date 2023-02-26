package com.example.FootballStats.controller;

import com.example.FootballStats.entity.StatPlayer;
import com.example.FootballStats.repo.StatPlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path="/statsplayer")
public class StatPlayerController {

    @Autowired
    StatPlayerRepository statPlayerRepository;

    @RequestMapping(path="", method= RequestMethod.GET)
    public List<StatPlayer> getAllStatsPlayer(){
        return (List<StatPlayer>) statPlayerRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @RequestMapping(path="/{stat_player_id}", method= RequestMethod.GET)
    public StatPlayer getStatPlayerById (@PathVariable("stat_player_id") Long stat_player_id){
        Optional<StatPlayer> statPlayer = statPlayerRepository.findById(stat_player_id);
        return statPlayer.orElse(null);
    }
}
