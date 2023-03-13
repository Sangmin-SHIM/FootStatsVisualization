package com.example.FootballStats.repo;

import com.example.FootballStats.entity.League;
import com.example.FootballStats.entity.RankingLeague;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RankingLeagueRepository extends CrudRepository<RankingLeague, Long> {
    Iterable<RankingLeague> findAll(Sort id);
    List<RankingLeague> findByLeague(Optional<League> league);
}
