package com.example.FootballStats.controller;

import com.example.FootballStats.entity.StatLeague;
import com.example.FootballStats.repo.StatLeagueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/statsleague")
public class StatLeagueController {

    @Autowired
    StatLeagueRepository statLeagueRepository;

    @RequestMapping(path="", method= RequestMethod.GET)
    public List<StatLeague> getAllStatsLeague(){
        return (List<StatLeague>) statLeagueRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @RequestMapping(path="/{id}", method= RequestMethod.GET)
    public StatLeague getStatLeagueById (@PathVariable("id") Long id){
        Optional<StatLeague> statLeague = statLeagueRepository.findById(id);
        return statLeague.orElse(null);
    }
}
