package com.example.FootballStats.repo;

import com.example.FootballStats.aggregation.IGkPlayersByClubCount;
import com.example.FootballStats.aggregation.ILeagueAllTimeBestGoalkeeper;
import com.example.FootballStats.aggregation.ILeagueSeasonBestGoalkeeper;
import com.example.FootballStats.entity.Club;
import com.example.FootballStats.entity.Player;
import com.example.FootballStats.entity.StatGkPlayer;
import com.example.FootballStats.entity.StatPlayer;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StatGkPlayerRepository extends CrudRepository<StatGkPlayer, Long> {

    Iterable<StatGkPlayer> findAll(Sort id);

    List<StatGkPlayer> findByPlayer(Optional<Player> player);

    List<StatGkPlayer> findGkPlayerByClubAndSeason(Optional<Club> club, String season);
    List<StatGkPlayer> findGkPlayerByClub(Optional<Club> club);

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
                        WHERE (:league_id IS NULL OR leagueid = :league_id)
                    """, nativeQuery = true)
    List<ILeagueAllTimeBestGoalkeeper> findAllTimeBestGoalkeeper(@Param("league_id") Integer league_id);

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
                        AND (:league_id IS NULL OR league_id = :league_id)
                    """, nativeQuery = true)
    List<ILeagueSeasonBestGoalkeeper> findSeasonBestGoalkeeper(@Param("season") String season, @Param("league_id") Integer league_id);

    @Query(value=
            """
                    SELECT
                          club_id as clubid,
                          player_id as playerid,
                          player_name as playername,
                          player_position as playerposition,
                          nationality_name as nationalityname,
                          club_name as clubname,
                          all_nb_games as allnbgames,
                          all_wins as allwins,
                          all_draws as alldraws,
                          all_loses as allloses, 
                          all_gas as allgas,
                          all_saves as allsaves,
                          all_sotas as allsotas,
                          all_cs as allcs,
                          avg_ga90 as avgga90,
                          avg_save_percent as avgssavepercent,
                          avg_cs_percent as avgcspercent 
                    FROM materialized_view_players_goalkeeper_by_club_aggregated_data
                        WHERE (:player_id IS NULL OR player_id = :player_id)
                        AND (:club_id IS NULL OR club_id = :club_id)
                    """, nativeQuery = true)
    List<IGkPlayersByClubCount> findTotalCountOfGkPlayersByClub(@Param("club_id") Integer club_id, @Param("player_id") Integer player_id);

    @Query(value=
            """     
                    SELECT
                    *
                    FROM
                    (
                    SELECT
                          club_id as clubid,
                          player_id as playerid,
                          player_name as playername,
                          player_position as playerposition,
                          nationality_name as nationalityname,
                          club_name as clubname,
                          all_nb_games as allnbgames,
                          all_wins as allwins,
                          all_draws as alldraws,
                          all_loses as allloses, 
                          all_gas as allgas,
                          all_saves as allsaves,
                          all_sotas as allsotas,
                          all_cs as allcs,
                          avg_ga90 as avgga90,
                          avg_save_percent as avgssavepercent,
                          avg_cs_percent as avgcspercent
                    FROM materialized_view_players_goalkeeper_by_club_aggregated_data
                        WHERE (:club_id IS NULL OR club_id = :club_id)
                    ) as sub
                    ORDER BY allgas DESC
                    LIMIT 10
                    """, nativeQuery = true)
    List<IGkPlayersByClubCount> findTop10BestGoalkeepersByClub(@Param("club_id") Integer club_id);
}
