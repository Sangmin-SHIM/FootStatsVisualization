package com.example.FootballStats.repo;

import com.example.FootballStats.entity.Nationality;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NationalityRepository extends CrudRepository<Nationality, Long> {

    Iterable<Nationality> findAll(Sort id);
}