package com.example.FootballStats.controller;

import com.example.FootballStats.repo.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.ClassPathResource;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping(path="/images")
public class ImageController {

    @Autowired
    PlayerRepository playerRepository;

    @RequestMapping(path="/leagues/{league_name}", method = RequestMethod.GET,
        produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getLeagueImage(@PathVariable("league_name") String league_name) throws IOException {
        var imgFile = new ClassPathResource("images/leagues/"+league_name+".png");
        byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(bytes);
    }

    @RequestMapping(path="/clubs/{club_name}", method = RequestMethod.GET,
        produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getClubImage(@PathVariable("club_name") String club_name) throws IOException {
        var imgFile = new ClassPathResource("images/clubs/"+club_name+".png");
        byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(bytes);
    }

    @RequestMapping(path="/players/{player_id}", method = RequestMethod.GET,
        produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getPlayerImage(@PathVariable("player_id") Long player_id) throws IOException {

        String player_name = Objects.requireNonNull(playerRepository.findById(player_id).orElse(null)).getName();

        var imgFile = new ClassPathResource("images/players/"+player_id+player_name+".png");

         if (imgFile.exists()) {
             byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());
             return ResponseEntity
                     .ok()
                     .contentType(MediaType.IMAGE_PNG)
                     .body(bytes);
         } else {
             var defaultImgFile = new ClassPathResource("images/players/default.png");
             byte[] bytes = StreamUtils.copyToByteArray(defaultImgFile.getInputStream());
             return ResponseEntity
                     .ok()
                     .contentType(MediaType.IMAGE_PNG)
                     .body(bytes);
         }
    }

    @RequestMapping(path="/nationalities/{nationality_name}", method = RequestMethod.GET,
        produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getNationalityImage(@PathVariable("nationality_name") String nationality_name) throws IOException {
        var imgFile = new ClassPathResource("/home/ubuntu/images/nationalities/"+nationality_name+".png",this.getClass().getClassLoader());

        byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(bytes);
    }
}
