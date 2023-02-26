package com.example.FootballStats.controller;

import com.example.FootballStats.entity.StatLeagueClub;
import com.example.FootballStats.repo.StatLeagueClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path="/statsleagueclub")
public class StatLeagueClubController {

    @Autowired
    StatLeagueClubRepository statLeagueClubRepository;

    @RequestMapping(path="", method= RequestMethod.GET)
    public List<StatLeagueClub> getAllStatsLeague(){
        return (List<StatLeagueClub>) statLeagueClubRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @RequestMapping(path="/{stat_league_club_id}", method= RequestMethod.GET)
    public StatLeagueClub getStatLeagueById (@PathVariable("stat_league_club_id") Long stat_league_club_id){
        Optional<StatLeagueClub> statLeagueClub = statLeagueClubRepository.findById(stat_league_club_id);
        return statLeagueClub.orElse(null);
    }
}
