package com.example.FootballStats.controller;


import com.example.FootballStats.entity.League;
import com.example.FootballStats.entity.StatLeagueClub;
import com.example.FootballStats.repo.LeagueRepository;
import com.example.FootballStats.repo.StatLeagueClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@CrossOrigin
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

    // Get Image
    @RequestMapping(path="/images/{league_name}", method = RequestMethod.GET,
        produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable("league_name") String league_name) throws IOException {
        var imgFile = new ClassPathResource("images/leagues/"+league_name+".png");
        byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(bytes);
    }

}
