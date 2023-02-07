package com.example.FootballStats.repo;

import com.example.FootballStats.entity.Club;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

public interface ClubRepository extends CrudRepository<Club,Long> {

    Iterable<Club> findAll(Sort id);

}
