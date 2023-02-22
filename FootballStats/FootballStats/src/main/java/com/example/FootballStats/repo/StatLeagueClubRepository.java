package com.example.FootballStats.repo;
import com.example.FootballStats.entity.Club;
import com.example.FootballStats.entity.League;

import com.example.FootballStats.entity.StatLeagueClub;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface StatLeagueClubRepository extends CrudRepository<StatLeagueClub, Long>{

    Iterable<StatLeagueClub> findAll(Sort id);

    List<StatLeagueClub> findByLeague(Optional<League> league);

    List<StatLeagueClub> findByClub(Optional<Club> club);

    List<StatLeagueClub> findByClubAndSeason(Optional<Club> club, String season);

}
