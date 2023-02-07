package com.example.FootballStats.repo;

import com.example.FootballStats.entity.Nationality;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

public interface NationalityRepository extends CrudRepository<Nationality, Long> {

    Iterable<Nationality> findAll(Sort id);
}
