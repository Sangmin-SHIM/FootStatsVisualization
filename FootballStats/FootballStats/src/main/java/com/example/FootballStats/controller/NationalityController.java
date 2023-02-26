package com.example.FootballStats.controller;

import com.example.FootballStats.entity.League;
import com.example.FootballStats.entity.Nationality;
import com.example.FootballStats.repo.NationalityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path="/nationalities")
public class NationalityController {

    @Autowired
    NationalityRepository nationalityRepository;

    @RequestMapping(path="", method= RequestMethod.GET)
    public List<Nationality> getAllNationalities(){
        return (List<Nationality>) nationalityRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @RequestMapping(path="/{nationality_id}", method= RequestMethod.GET)
    public Nationality getNationalityById (@PathVariable("nationality_id") Long nationality_id){
        Optional<Nationality> nationality = nationalityRepository.findById(nationality_id);
        return nationality.orElse(null);
    }

}
