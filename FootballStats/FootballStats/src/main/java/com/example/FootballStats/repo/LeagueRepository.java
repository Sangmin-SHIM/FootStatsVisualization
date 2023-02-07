package com.example.FootballStats.repo;

import com.example.FootballStats.entity.League;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

public interface LeagueRepository  extends CrudRepository<League,Long> {

    Iterable<League> findAll(Sort id);
}