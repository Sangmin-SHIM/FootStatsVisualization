package com.example.FootballStats.controller;

import com.example.FootballStats.aggregation.*;
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

    // All Time Best Goalkeeper in 5 Leagues
    @RequestMapping(path="/alltimebeststrikers", method= RequestMethod.GET)
    public List<ILeagueAllTimeBestStriker> getAllTimeBestStrikers (){
        return statPlayerRepository.findAllTimeBestStriker();
    }

    // Best Goalkeeper in 5 Leagues in a Season
    @RequestMapping(path="/beststrikers", method= RequestMethod.GET)
    public List<ILeagueSeasonBestStriker> getBestStrikers (@RequestParam("season") String season){
        return statPlayerRepository.findSeasonBestStriker(season);
    }

    // All Time Best Playmaker in 5 Leagues
    @RequestMapping(path="/alltimebestplaymakers", method= RequestMethod.GET)
    public List<ILeagueAllTimeBestPlaymaker> getAllTimeBestPlaymakers (){
        return statPlayerRepository.findAllTimeBestPlaymaker();
    }

    // Best Playmaker in 5 Leagues in a Season
    @RequestMapping(path="/bestsplaymakers", method= RequestMethod.GET)
    public List<ILeagueSeasonBestPlaymaker> getBestPlaymakers (@RequestParam("season") String season){
        return statPlayerRepository.findSeasonBestPlaymaker(season);
    }

    // All Time Best Goalkeeper in 5 Leagues
    @RequestMapping(path="/alltimebestgoalkeepers", method= RequestMethod.GET)
    public List<ILeagueAllTimeBestGoalkeeper> getAllTimeBestGoalkeepers (){
        return statPlayerRepository.findAllTimeBestGoalkeeper();
    }

    // Best Goalkeeper in 5 Leagues in a Season
    @RequestMapping(path="/bestgoalkeepers", method= RequestMethod.GET)
    public List<ILeagueSeasonBestGoalkeeper> getBestGoalkeepers (@RequestParam("season") String season){
        return statPlayerRepository.findSeasonBestGoalkeeper(season);
    }
}
