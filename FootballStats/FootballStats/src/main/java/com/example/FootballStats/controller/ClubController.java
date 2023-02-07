package com.example.FootballStats.controller;

import com.example.FootballStats.entity.Club;
import com.example.FootballStats.entity.League;
import com.example.FootballStats.repo.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/clubs")
public class ClubController {

    @Autowired
    ClubRepository clubRepository;

    @RequestMapping(path="", method= RequestMethod.GET)
    public List<Club> getAllClubs(){
        return (List<Club>) clubRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @RequestMapping(path="/{id}", method= RequestMethod.GET)
    public Club getClubById (@PathVariable("id") Long id){
        Optional<Club> club = clubRepository.findById(id);
        return club.orElse(null);
    }
}
