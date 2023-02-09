package com.example.FootballStats.repo;

import com.example.FootballStats.entity.StatGkPlayer;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

public interface StatGkPlayerRepository extends CrudRepository<StatGkPlayer, Long> {

    Iterable<StatGkPlayer> findAll(Sort id);
}
