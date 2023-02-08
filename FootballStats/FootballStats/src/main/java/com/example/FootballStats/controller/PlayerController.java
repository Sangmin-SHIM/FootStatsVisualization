package com.example.FootballStats.controller;

import com.example.FootballStats.entity.Player;
import com.example.FootballStats.repo.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/players")
public class PlayerController {

    @Autowired
    PlayerRepository playerRepository;

    @RequestMapping(path="", method= RequestMethod.GET)
    public List<Player> getAllPlayers(){
        return (List<Player>) playerRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @RequestMapping(path="/{id}", method= RequestMethod.GET)
    public Player getPlayerById (@PathVariable("id") Long id){
        Optional<Player> player = playerRepository.findById(id);
        return player.orElse(null);
    }
}
