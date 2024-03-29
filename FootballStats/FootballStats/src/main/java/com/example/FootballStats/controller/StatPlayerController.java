package com.example.FootballStats.controller;

import com.example.FootballStats.aggregation.*;
import com.example.FootballStats.entity.Club;
import com.example.FootballStats.entity.Player;
import com.example.FootballStats.entity.StatPlayer;
import com.example.FootballStats.customization.PlayersByClubCount;
import com.example.FootballStats.repo.ClubRepository;
import com.example.FootballStats.repo.PlayerRepository;
import com.example.FootballStats.repo.StatPlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path="/stats_player")
public class StatPlayerController {

    @Autowired
    StatPlayerRepository statPlayerRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    ClubRepository clubRepository;

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
    @RequestMapping(path="/all_time_best_strikers", method= RequestMethod.GET)
    public List<ILeagueAllTimeBestStriker> getAllTimeBestStrikers (@RequestParam(name="league_id", required = false) Integer league_id){
        return statPlayerRepository.findAllTimeBestStriker(league_id);
    }

    // Best Goalkeeper in 5 Leagues in a Season
    @RequestMapping(path="/best_strikers", method= RequestMethod.GET)
    public List<ILeagueSeasonBestStriker> getBestStrikers (@RequestParam(name="season", required = false) String season, @RequestParam(name="league_id", required = false) Integer league_id){
        return statPlayerRepository.findSeasonBestStriker(season, league_id);
    }

    // All Time Best Playmaker in 5 Leagues
    @RequestMapping(path="/all_time_best_playmakers", method= RequestMethod.GET)
    public List<ILeagueAllTimeBestPlaymaker> getAllTimeBestPlaymakers (@RequestParam(name="league_id", required = false) Integer league_id){
        return statPlayerRepository.findAllTimeBestPlaymaker(league_id);
    }

    // Best Playmaker in 5 Leagues in a Season
    @RequestMapping(path="/best_playmakers", method= RequestMethod.GET)
    public List<ILeagueSeasonBestPlaymaker> getBestPlaymakers (@RequestParam(name="season", required = false) String season, @RequestParam(name="league_id", required = false) Integer league_id){
        return statPlayerRepository.findSeasonBestPlaymaker(season,league_id);
    }

    @RequestMapping(path="/total_players_by_club", method= RequestMethod.GET)
    public PlayersByClubCount getTotalCountOfPlayersByClub (@RequestParam(name="club_id", required = false) Integer club_id,
                                                                  @RequestParam(name="player_id", required = false) Integer player_id,
                                                                  @RequestParam(name="player_name", required = false) String player_name,
                                                                  @RequestParam(name="player_position", required = false) String player_position,
                                                                  @RequestParam(name="nationality_name", required = false) String nationality_name,
                                                                  @RequestParam(name="sort_field", defaultValue = "all_nb_games") String sort_field,

                                                                  @RequestParam(name="sort_order", defaultValue = "asc") String sort_order,
                                                                  @RequestParam(name="page", defaultValue = "0") Integer pageNumber,
                                                                  @RequestParam(name="size", defaultValue = "16") Integer pageSize){

        Pageable pageable;
        if (sort_order.equals("desc")) {
            Sort sort = Sort.by(Sort.Order.desc(sort_field));
            pageable = PageRequest.of(pageNumber, pageSize, sort);
        } else {
            Sort sort = Sort.by(Sort.Order.asc(sort_field));
            pageable = PageRequest.of(pageNumber, pageSize, sort);
        }
        // Fetch the list of players
        List<IPlayersByClubCount> countOfPlayersByClubPerPage = statPlayerRepository.findTotalCountOfPlayersByClub(club_id, player_id, player_name, player_position, nationality_name, sort_field, pageable);
        List<IPlayersByClubCount> totalCountOfPlayersByClub = statPlayerRepository.findTotalCountOfPlayersByClub(club_id, player_id);

        // Calculate the length of the list
        long total_count = totalCountOfPlayersByClub.size();

        // Create an instance of PlayersByClubData
        PlayersByClubCount playersByClubCount = new PlayersByClubCount(countOfPlayersByClubPerPage, total_count);

        return playersByClubCount;
    }

    @RequestMapping(path="/total_best_10_strikers_by_club", method= RequestMethod.GET)
    public List<IPlayersByClubCount> getBest10StrikersByClub (@RequestParam(name="club_id") Integer club_id){
        return statPlayerRepository.findTop10BestStrikersByClub(club_id);
    }

    @RequestMapping(path="/total_best_10_playmakers_by_club", method= RequestMethod.GET)
    public List<IPlayersByClubCount> getBest10PlaymakersByClub (@RequestParam(name="club_id") Integer club_id){
        return statPlayerRepository.findTop10BestPlaymakersByClub(club_id);
    }

    @RequestMapping(path= "/club_all_players", method = RequestMethod.GET)
    public List<StatPlayer> getClubAllPlayersBySeason (@RequestParam(name="club_id") Integer club_id,
                                                       @RequestParam(name="season", required = false) String season,
                                                       @RequestParam(name="player_position", required = false) String player_position,
                                                       @RequestParam(name="player_name", required = false) String player_name,
                                                       @RequestParam(name="nationality_name", required = false) String nationality_name,
                                                       @RequestParam(name="sort_field", required = false, defaultValue = "player.name") String sort_field,
                                                       @RequestParam(name="sort_order", defaultValue = "asc") String sort_order
                                                        ){

        Optional<Club> club = clubRepository.findById(club_id);

        Sort sort;
        if (sort_order.equals("asc")){
            sort = Sort.by(Sort.Order.asc(sort_field));
        } else {
            sort = Sort.by(Sort.Order.desc(sort_field));
        }

        if (season != null) {
            return statPlayerRepository.findPlayersByClubAndSeason(club,season, player_position, player_name, nationality_name,sort);
        } else {
            return statPlayerRepository.findPlayersByClub(club, player_position, player_name, nationality_name, sort);
        }

    }
    @RequestMapping(path="/all_aggregated_stats_per_season", method= RequestMethod.GET)
    public List<StatPlayer> getPlayerAllStatsById (@RequestParam("player_id") Long player_id){
        Optional<Player> player = playerRepository.findById(player_id);
        return statPlayerRepository.findByPlayer(player);
    }

    @RequestMapping(path="/all_aggregated_stats_per_club", method= RequestMethod.GET)
    public List<IPlayersByClubCount> getPlayerAllStatsPerClub(@RequestParam("player_id") Long player_id){
        return statPlayerRepository.findPlayerStatsPerClub(player_id);
    }

    @RequestMapping(path="/all_aggregated_stats", method= RequestMethod.GET)
    public List<IPlayerCount> getPlayerAllStats(@RequestParam("player_id") Long player_id) {
        return statPlayerRepository.findPlayerAllStats(player_id);
    }

}
