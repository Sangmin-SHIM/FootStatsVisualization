package com.example.FootballStats.repo;

import com.example.FootballStats.entity.Player;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, Long> {

    Iterable<Player> findAll(Sort id);

}
