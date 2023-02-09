package com.example.FootballStats.controller;

import com.example.FootballStats.entity.StatGkPlayer;
import com.example.FootballStats.repo.StatGkPlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/statsgkplayer")
public class StatGkPlayerController {

    @Autowired
    StatGkPlayerRepository statGkPlayerRepository;

    @RequestMapping(path="", method= RequestMethod.GET)
    public List<StatGkPlayer> getAllStatsGkPlayer(){
        return (List<StatGkPlayer>) statGkPlayerRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @RequestMapping(path="/{id}", method= RequestMethod.GET)
    public StatGkPlayer getStatGkPlayerById (@PathVariable("id") Long id){
        Optional<StatGkPlayer> statGkPlayer = statGkPlayerRepository.findById(id);
        return statGkPlayer.orElse(null);
    }
}
