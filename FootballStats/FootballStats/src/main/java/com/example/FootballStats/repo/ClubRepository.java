package com.example.FootballStats.repo;

import com.example.FootballStats.entity.Club;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClubRepository extends CrudRepository<Club,Long> {

    Iterable<Club> findAll(Sort id);

    Optional<Club> findById(Integer id);

}
