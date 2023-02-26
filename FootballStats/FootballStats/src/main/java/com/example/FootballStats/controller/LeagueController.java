package com.example.FootballStats.controller;

import com.example.FootballStats.entity.League;
import com.example.FootballStats.entity.StatLeagueClub;
import com.example.FootballStats.repo.LeagueRepository;
import com.example.FootballStats.repo.StatLeagueClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/leagues")
public class LeagueController {

    @Autowired
    LeagueRepository leagueRepository;
    @Autowired
    StatLeagueClubRepository statLeagueClubRepository;

    @RequestMapping(path="", method= RequestMethod.GET)
    public List<League> getAllLeagues(){
        return (List<League>) leagueRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @RequestMapping(path="/{league_id}", method= RequestMethod.GET)
    public League getLeagueById (@PathVariable("league_id") Long league_id){
        Optional<League> league = leagueRepository.findById(league_id);
        return league.orElse(null);
    }

    @RequestMapping(path="/{league_id}/stats", method= RequestMethod.GET)
    public List<StatLeagueClub> getLeagueAllStatsById (@PathVariable("league_id") Long league_id){
        Optional<League> league = leagueRepository.findById(league_id);
        return statLeagueClubRepository.findByLeague(league);

    }
}
