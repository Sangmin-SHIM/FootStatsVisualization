package com.example.FootballStats.controller;

import com.example.FootballStats.entity.Club;
import com.example.FootballStats.entity.Player;
import com.example.FootballStats.entity.StatLeagueClub;
import com.example.FootballStats.entity.StatPlayer;
import com.example.FootballStats.repo.PlayerRepository;
import com.example.FootballStats.repo.StatPlayerRepository;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public Page<Player> getAllPlayers(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            Pageable pageable){

        Sort sort = Sort.by(Sort.Direction.ASC, "id");

        Pageable pageRequest = PageRequest.of(page, size, sort);

        return playerRepository.findAll(pageRequest);
    }

    @RequestMapping(path="/{player_id}", method= RequestMethod.GET)
    public Player getPlayerById (@PathVariable("player_id") Long player_id){
        Optional<Player> player = playerRepository.findById(player_id);
        return player.orElse(null);
    }

}
