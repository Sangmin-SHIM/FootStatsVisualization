package com.example.FootballStats.repo;

import com.example.FootballStats.entity.Player;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PlayerRepository extends CrudRepository<Player, Long> {

    Iterable<Player> findAll(Sort id);

    Optional<Player> findById(Integer id);

}
