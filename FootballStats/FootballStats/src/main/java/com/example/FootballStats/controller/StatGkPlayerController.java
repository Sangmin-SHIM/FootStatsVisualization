package com.example.FootballStats.controller;

import com.example.FootballStats.aggregation.IGkPlayersByClubCount;
import com.example.FootballStats.aggregation.ILeagueAllTimeBestGoalkeeper;
import com.example.FootballStats.aggregation.ILeagueSeasonBestGoalkeeper;
import com.example.FootballStats.entity.Club;
import com.example.FootballStats.entity.Player;
import com.example.FootballStats.entity.StatGkPlayer;
import com.example.FootballStats.entity.StatPlayer;
import com.example.FootballStats.repo.ClubRepository;
import com.example.FootballStats.repo.PlayerRepository;
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

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    ClubRepository clubRepository;


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

    @RequestMapping(path="/total_gk_players_by_club", method= RequestMethod.GET)
    public List<IGkPlayersByClubCount> getTotalCountOfGkPlayersByClub (@RequestParam(name="club_id", required = false) Integer club_id, @RequestParam(name="player_id", required = false) Integer player_id){
        return statGkPlayerRepository.findTotalCountOfGkPlayersByClub(club_id, player_id);
    }

    @RequestMapping(path="/total_best_10_gk_players_by_club", method= RequestMethod.GET)
    public List<IGkPlayersByClubCount> getBest10GoalkeepersByClub (@RequestParam(name="club_id") Integer club_id){
        return statGkPlayerRepository.findTop10BestGoalkeepersByClub(club_id);
    }

    @RequestMapping(path="/club_all_players_by_season", method = RequestMethod.GET)
    public List<StatGkPlayer> getClubAllGkPlayersBySeason (@RequestParam(name="club_id") Integer club_id,
                                                       @RequestParam(name="season", required = false) String season){
        Optional<Club> club = clubRepository.findById(club_id);

        if (season != null) {
            return statGkPlayerRepository.findGkPlayerByClubAndSeason(club,season);
        } else {
            return statGkPlayerRepository.findGkPlayerByClub(club);
        }

    }
    @RequestMapping(path="/{player_id}/stats", method= RequestMethod.GET)
    public List<StatGkPlayer> getGkPlayerAllStatsById (@PathVariable("player_id") Long player_id){
        Optional<Player> player = playerRepository.findById(player_id);
        return statGkPlayerRepository.findByPlayer(player);
    }
}
