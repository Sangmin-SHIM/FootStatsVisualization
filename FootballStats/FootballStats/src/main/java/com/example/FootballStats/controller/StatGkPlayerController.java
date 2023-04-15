package com.example.FootballStats.controller;

import com.example.FootballStats.aggregation.ILeagueAllTimeBestGoalkeeper;
import com.example.FootballStats.aggregation.ILeagueSeasonBestGoalkeeper;
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

    // All Time Best Goalkeeper in 5 Leagues
    @RequestMapping(path="/all_time_best_goalkeepers", method= RequestMethod.GET)
    public List<ILeagueAllTimeBestGoalkeeper> getAllTimeBestGoalkeepers (@RequestParam(name="league_id", required = false) Integer league_id){
        return statGkPlayerRepository.findAllTimeBestGoalkeeper(league_id);
    }

    // Best Goalkeeper in 5 Leagues in a Season
    @RequestMapping(path="/best_goalkeepers", method= RequestMethod.GET)
    public List<ILeagueSeasonBestGoalkeeper> getBestGoalkeepers (@RequestParam(name="season", required = false) String season, @RequestParam(name="league_id", required = false) Integer league_id){
        return statGkPlayerRepository.findSeasonBestGoalkeeper(season, league_id);
    }
}
