package com.example.FootballStats.repo;

import com.example.FootballStats.aggregation.ILeagueAllTimeBestGoalkeeper;
import com.example.FootballStats.aggregation.ILeagueSeasonBestGoalkeeper;
import com.example.FootballStats.entity.StatGkPlayer;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StatGkPlayerRepository extends CrudRepository<StatGkPlayer, Long> {

    Iterable<StatGkPlayer> findAll(Sort id);

    // All Time Best Goalkeeper in 5 Leagues
    @Query(value=
            """
                    SELECT
                          leagueid,
                          leaguename,
                          playerid,
                          playername,
                          totalmatches,
                          totalgoalsagainst,
                          goalsagainstpermatches
                    FROM materialized_view_league_all_time_best_goalkeeper_aggregated_data
                    """, nativeQuery = true)
    List<ILeagueAllTimeBestGoalkeeper> findAllTimeBestGoalkeeper();

    // Best Goalkeeper in 5 Leagues in a Season
    @Query(value=
            """
                    SELECT
                          season,
                          league_id as leagueid,
                          league_name as leaguename,
                          player_id as playerid,
                          player_name as playername,
                          total_games as totalgames,
                          total_goal_against as totalgoalagainst,
                          goal_against_per_match as goalagainstpermatch  
                    FROM materialized_view_league_best_goalkeeper_aggregated_data
                        WHERE (:season IS NULL OR season LIKE :season)
                    """, nativeQuery = true)
    List<ILeagueSeasonBestGoalkeeper> findSeasonBestGoalkeeper(@Param("season") String season);
}
