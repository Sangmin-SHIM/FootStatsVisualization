package com.example.FootballStats.repo;

import com.example.FootballStats.entity.Player;
import com.example.FootballStats.entity.StatPlayer;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface StatPlayerRepository extends CrudRepository<StatPlayer, Long> {

    Iterable<StatPlayer> findAll(Sort id);

    List<StatPlayer> findByPlayer(Optional<Player> player);

}
