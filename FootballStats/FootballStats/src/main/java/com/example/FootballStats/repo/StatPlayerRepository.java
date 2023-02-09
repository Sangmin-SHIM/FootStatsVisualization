package com.example.FootballStats.repo;

import com.example.FootballStats.entity.StatPlayer;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

public interface StatPlayerRepository extends CrudRepository<StatPlayer, Long> {

    Iterable<StatPlayer> findAll(Sort id);

}
