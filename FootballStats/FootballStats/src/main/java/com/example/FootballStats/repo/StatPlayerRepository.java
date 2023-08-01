package com.example.FootballStats.repo;

import com.example.FootballStats.aggregation.*;
import com.example.FootballStats.entity.Club;
import com.example.FootballStats.entity.Player;
import com.example.FootballStats.entity.StatPlayer;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StatPlayerRepository extends CrudRepository<StatPlayer, Long> {

    Iterable<StatPlayer> findAll(Sort id);

    List<StatPlayer> findByPlayer(Optional<Player> player);

    List<StatPlayer> findPlayersByClubAndSeason(Optional<Club> club,String season);
    List<StatPlayer> findPlayersByClub(Optional<Club> club);

    // All Time Best Striker in 5 Leagues
    @Query(value=
            """
                    SELECT
                          leagueid,
                          leaguename,
                          playerid,
                          playername,
                          totalmatches,
                          totalgoals,
                          goalspermatches
                    FROM materialized_view_league_all_time_best_striker_aggregated_data
                         WHERE (:league_id IS NULL OR leagueid = :league_id)
                    """, nativeQuery = true)
    List<ILeagueAllTimeBestStriker> findAllTimeBestStriker(@Param("league_id") Integer league_id);

    // Best Striker in 5 Leagues in a Season
    @Query(value=
            """
                    SELECT
                          season,
                          league_id as leagueid,
                          league_name as leaguename,
                          player_id as playerid,
                          player_name as playername,
                          total_games as totalgames,
                          total_goals as totalgoals,
                          goals_per_match as goalspermatch  
                    FROM materialized_view_league_best_striker_aggregated_data
                        WHERE (:season IS NULL OR season LIKE :season)
                        AND (:league_id IS NULL OR league_id = :league_id)
                    """, nativeQuery = true)
    List<ILeagueSeasonBestStriker> findSeasonBestStriker(@Param("season") String season, @Param("league_id") Integer league_id);

    // All Time Best Playmaker in 5 Leagues
    @Query(value=
            """
                    SELECT
                          leagueid,
                          leaguename,
                          playerid,
                          playername,
                          totalmatches,
                          totalassists,
                          assistspermatches
                    FROM materialized_view_league_all_time_best_playmaker_aggregated_data
                     WHERE (:league_id IS NULL OR leagueid = :league_id)
                    """, nativeQuery = true)
    List<ILeagueAllTimeBestPlaymaker> findAllTimeBestPlaymaker(@Param("league_id") Integer league_id);

    // Best Playmaker in 5 Leagues in a Season
    @Query(value=
            """
                    SELECT
                          season,
                          league_id as leagueid,
                          league_name as leaguename,
                          player_id as playerid,
                          player_name as playername,
                          total_games as totalgames,
                          total_assists as totalassists,
                          assists_per_match as assistspermatch  
                    FROM materialized_view_league_best_playmaker_aggregated_data
                        WHERE (:season IS NULL OR season LIKE :season)
                        AND (:league_id IS NULL OR league_id = :league_id)
                    """, nativeQuery = true)
    List<ILeagueSeasonBestPlaymaker> findSeasonBestPlaymaker(@Param("season") String season, @Param("league_id") Integer league_id);

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
                          all_goals as allgoals,
                          all_assists as allassists,
                          ROUND(avg_minutes,1) as avgminutes,
                          all_yellow_cards as allyellowcards,
                          all_red_cards as allredcards,
                          goals_per_game as goalspergame,
                          assists_per_game as assistspergame
                    FROM materialized_view_players_by_club_aggregated_data
                        WHERE (:player_id IS NULL OR player_id = :player_id)
                        AND (:club_id IS NULL OR club_id = :club_id)
                        AND player_position NOT LIKE 'GK'
                    """, nativeQuery = true)
    List<IPlayersByClubCount> findTotalCountOfPlayersByClub(@Param("club_id") Integer club_id, @Param("player_id") Integer player_id);

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
                          all_goals as allgoals,
                          all_assists as allassists,
                          ROUND(avg_minutes,1) as avgminutes,
                          all_yellow_cards as allyellowcards,
                          all_red_cards as allredcards,
                          goals_per_game as goalspergame,
                          assists_per_game as assistspergame
                    FROM materialized_view_players_by_club_aggregated_data
                        WHERE (:club_id IS NULL OR club_id = :club_id)
                    ) as sub
                    ORDER BY allgoals DESC
                    LIMIT 10
                    """, nativeQuery = true)
    List<IPlayersByClubCount> findTop10BestStrikersByClub(@Param("club_id") Integer club_id);

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
                          all_goals as allgoals,
                          all_assists as allassists,
                          ROUND(avg_minutes,1) as avgminutes,
                          all_yellow_cards as allyellowcards,
                          all_red_cards as allredcards,
                          goals_per_game as goalspergame,
                          assists_per_game as assistspergame
                    FROM materialized_view_players_by_club_aggregated_data
                        WHERE (:club_id IS NULL OR club_id = :club_id)
                    ) as sub
                    ORDER BY allassists DESC
                    LIMIT 10
                    """, nativeQuery = true)
    List<IPlayersByClubCount> findTop10BestPlaymakersByClub(@Param("club_id") Integer club_id);
}
