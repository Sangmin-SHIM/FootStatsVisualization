package com.example.FootballStats.repo;

import com.example.FootballStats.entity.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PlayerRepository extends CrudRepository<Player, Long> {

    Page<Player> findAll(Pageable pageable);

    Optional<Player> findById(Integer id);

}
