package com.example.FootballStats.controller;

import com.example.FootballStats.entity.RankingLeague;
import com.example.FootballStats.repo.RankingLeagueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path="/rankingsleague")
public class RankingLeagueController {

        @Autowired
        RankingLeagueRepository rankingLeagueRepository;

        @RequestMapping(path="", method= RequestMethod.GET)
        public List<RankingLeague> getAllRankingsLeague(){
            return (List<RankingLeague>) rankingLeagueRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        }

        @RequestMapping(path="/{ranking_league_id}", method= RequestMethod.GET)
        public RankingLeague getRankingLeagueById (@PathVariable("ranking_league_id") Long ranking_league_id){
            Optional<RankingLeague> rankingLeague = rankingLeagueRepository.findById(ranking_league_id);
            return rankingLeague.orElse(null);
        }

}
