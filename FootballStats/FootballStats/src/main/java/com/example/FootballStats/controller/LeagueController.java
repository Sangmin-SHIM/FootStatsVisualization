package com.example.FootballStats.controller;

import com.example.FootballStats.entity.League;
import com.example.FootballStats.repo.LeagueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/leagues")
public class LeagueController {

    @Autowired
    LeagueRepository leagueRepository;

    @RequestMapping(path="", method= RequestMethod.GET)
    public List<League> getAllLeagues(){
        return (List<League>) leagueRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @RequestMapping(path="/{id}", method= RequestMethod.GET)
    public League getLeagueById (@PathVariable("id") Long id){
        Optional<League> league = leagueRepository.findById(id);
        return league.orElse(null);
    }
}
