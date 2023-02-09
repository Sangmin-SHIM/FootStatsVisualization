package com.example.FootballStats.controller;

import com.example.FootballStats.entity.StatPlayer;
import com.example.FootballStats.repo.StatPlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/statsplayer")
public class StatPlayerController {

    @Autowired
    StatPlayerRepository statPlayerRepository;

    @RequestMapping(path="", method= RequestMethod.GET)
    public List<StatPlayer> getAllStatsPlayer(){
        return (List<StatPlayer>) statPlayerRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @RequestMapping(path="/{id}", method= RequestMethod.GET)
    public StatPlayer getStatPlayerById (@PathVariable("id") Long id){
        Optional<StatPlayer> statPlayer = statPlayerRepository.findById(id);
        return statPlayer.orElse(null);
    }
}
