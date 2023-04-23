package com.example.FootballStats.controller;

import com.example.FootballStats.aggregation.*;
import com.example.FootballStats.entity.StatLeagueClub;
import com.example.FootballStats.repo.StatLeagueClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path="/stats_league_club")
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

    // ---------------------------------- //
    // --------- LEAGUE(S) DATA --------- //
    // ---------------------------------- //

    // Total Count of Leagues in Season : Goals, Assists, Yellow Cards, Red Cards
    @RequestMapping(path="/total", method= RequestMethod.GET)
    public List<ILeaguesTotalCount> getTotalCountOfLeagues (){
        return statLeagueClubRepository.findTotalCountOfLeagues();
    }

    // Total Count of Leagues in Season : Goals, Assists, Yellow Cards, Red Cards
    @RequestMapping(path="/total_by_season", method= RequestMethod.GET)
    public List<ILeaguesSeasonsCount> getTotalCountBySeasonOfLeagues (@RequestParam(name="season", required = false) String season){
        return statLeagueClubRepository.findCountBySeasonOfLeagues(season);
    }

    // All Time Best Club in 5 Leagues
    @RequestMapping(path="/all_time_best_clubs", method= RequestMethod.GET)
    public List<ILeagueAllTimeBestClub> getAllTimeBestClubs (@RequestParam(name="league_id", required = false) Integer league_id){
        return statLeagueClubRepository.findAllTimeBestClub(league_id);
    }

    // Best Club in 5 Leagues in a Season
    @RequestMapping(path="/best_clubs", method= RequestMethod.GET)
    public List<ILeagueSeasonBestClub> getBestClubs (@RequestParam(name="season", required = false) String season, @RequestParam(name="league_id", required = false) Integer league_id){
        return statLeagueClubRepository.findSeasonBestClub(season, league_id);
    }

    @RequestMapping(path="/nationalities_by_season", method = RequestMethod.GET)
    public List<ILeagueSeasonNationalitiesNumber> getNationalitiesBySeason (@RequestParam(name="season", required = false) String season, @RequestParam(name="league_id", required = false) Integer league_id){
        return statLeagueClubRepository.findSeasonNationalitiesNumber(season, league_id);
    }

    @RequestMapping(path="/nationalities", method = RequestMethod.GET)
    public List<ILeagueNationalitiesNumber> getNationalities (@RequestParam(name="league_id", required = false) Integer league_id){
        return statLeagueClubRepository.findNationalitiesNumber(league_id);
    }




    // ---------------------------------- //
    // END------ LEAGUE(S) DATA --------- //
    // ---------------------------------- //

}

