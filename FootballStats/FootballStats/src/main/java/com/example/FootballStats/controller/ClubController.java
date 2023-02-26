package com.example.FootballStats.controller;

import com.example.FootballStats.entity.Club;
import com.example.FootballStats.entity.League;
import com.example.FootballStats.entity.StatLeagueClub;
import com.example.FootballStats.repo.ClubRepository;
import com.example.FootballStats.repo.StatLeagueClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path="/clubs")
public class ClubController {

    @Autowired
    ClubRepository clubRepository;

    @Autowired
    StatLeagueClubRepository statLeagueClubRepository;

    @RequestMapping(path="", method= RequestMethod.GET)
    public List<Club> getAllClubs(){
        return (List<Club>) clubRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @RequestMapping(path="/{club_id}", method= RequestMethod.GET)
    public Club getClubById (@PathVariable("club_id") Long club_id){
        Optional<Club> club = clubRepository.findById(club_id);
        return club.orElse(null);
    }

    @RequestMapping(path="/{club_id}/stats", method= RequestMethod.GET)
    public List<StatLeagueClub> getClubAllStatsById (@PathVariable("club_id") Long club_id){
        Optional<Club> club = clubRepository.findById(club_id);
        return statLeagueClubRepository.findByClub(club);
    }

    @RequestMapping(path="/{club_id}/stats/{season}", method= RequestMethod.GET)
    public List<StatLeagueClub> getClubAllStatsByIdAndSeason (@PathVariable("club_id") Long club_id, @PathVariable("season") String season){
        Optional<Club> club = clubRepository.findById(club_id);
        return statLeagueClubRepository.findByClubAndSeason(club,season);
    }
}
