package com.example.FootballStats.controller;

import com.example.FootballStats.entity.Club;
import com.example.FootballStats.entity.Player;
import com.example.FootballStats.entity.StatLeagueClub;
import com.example.FootballStats.entity.StatPlayer;
import com.example.FootballStats.repo.PlayerRepository;
import com.example.FootballStats.repo.StatPlayerRepository;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path="/players")
public class PlayerController {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    StatPlayerRepository statPlayerRepository;

    @RequestMapping(path="", method= RequestMethod.GET)
    public List<Player> getAllPlayers(){
        return (List<Player>) playerRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @RequestMapping(path="/{player_id}", method= RequestMethod.GET)
    public Player getPlayerById (@PathVariable("player_id") Long player_id){
        Optional<Player> player = playerRepository.findById(player_id);
        return player.orElse(null);
    }

}
