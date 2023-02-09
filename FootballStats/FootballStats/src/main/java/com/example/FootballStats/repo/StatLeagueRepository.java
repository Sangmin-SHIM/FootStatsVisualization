package com.example.FootballStats.repo;

import com.example.FootballStats.entity.StatLeague;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

public interface StatLeagueRepository extends CrudRepository<StatLeague, Long>{

    Iterable<StatLeague> findAll(Sort id);
}
