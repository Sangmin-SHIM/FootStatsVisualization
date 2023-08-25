package com.example.FootballStats.repo;

import com.example.FootballStats.aggregation.*;
import com.example.FootballStats.entity.Club;
import com.example.FootballStats.entity.Player;
import com.example.FootballStats.entity.StatPlayer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StatPlayerRepository extends CrudRepository<StatPlayer, Long> {

    Iterable<StatPlayer> findAll(Sort id);

    List<StatPlayer> findByPlayer(Optional<Player> player);

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
                        WHERE (:player_id IS NULL OR player_id = :player_id)
                    ) as sub
                    """, nativeQuery = true)
    List<IPlayersByClubCount> findPlayerStatsPerClub(@Param("player_id") Long player_id);

    @Query(value=
            """
                    SELECT
                    playerid,
                    playername,
                    playerposition,
                    nationalityname,
                    allnbgames,
                    allgoals,
                    allassists,
                    allyellowcards,
                    allredcards,
                    avgminutes
                    FROM
                        materialized_view_player_aggregated_data
                    WHERE (:playerid IS NULL OR playerid=:playerid)
                    """, nativeQuery = true)
    List<IPlayerCount> findPlayerAllStats(@Param("playerid") Long playerid);


    @Query(""" 
            SELECT st FROM StatPlayer st 
            
            WHERE (:club IS NULL OR st.club = :club) 
                AND st.season = :season 
                AND (:player_position IS NULL OR st.player.position LIKE '%' || :player_position || '%')
                AND (:player_name IS NULL OR lower(st.player.name) LIKE lower(concat('%', :player_name, '%')))
                AND (:nationality_name IS NULL OR st.player.nationality.name_original LIKE :nationality_name)
                AND st.player.position NOT LIKE 'GK'     

        
            """)
    List<StatPlayer> findPlayersByClubAndSeason(Optional<Club> club,
                                                @Param("season") String season,
                                                @Param("player_position") String player_position,
                                                @Param("player_name") String player_name,
                                                @Param("nationality_name") String nationality_name,
                                                Sort sort
                                                );

    @Query(""" 
            SELECT st FROM StatPlayer st 
            
            WHERE (:club IS NULL OR st.club = :club)  
                AND (:player_position IS NULL OR st.player.position LIKE '%' || :player_position || '%')
                AND (:player_name IS NULL OR lower(st.player.name) LIKE lower(concat('%', :player_name, '%')))
                AND (:nationality_name IS NULL OR st.player.nationality.name_original LIKE :nationality_name)
                AND st.player.position NOT LIKE 'GK'     

        
            """)
    List<StatPlayer> findPlayersByClub(Optional<Club> club,
                                       @Param("player_position") String player_position,
                                       @Param("player_name") String player_name,
                                       @Param("nationality_name") String nationality_name,
                                       Sort sort);

    // All-Time Best Striker in 5 Leagues
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

    // All-Time Best Playmaker in 5 Leagues
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
                            AND (:player_name IS NULL OR LOWER(player_name) LIKE '%' || LOWER(:player_name) || '%')
                            AND (:player_position IS NULL OR player_position LIKE '%' || :player_position || '%')
                            AND (:nationality_name IS NULL OR nationality_name LIKE :nationality_name)
                            AND player_position NOT LIKE 'GK'
                        ORDER BY
                            CASE
                                WHEN :sort_field = 'player_name' THEN 'player_name'
                                WHEN :sort_field = 'nationality_name' THEN 'nationality_name'
                                WHEN :sort_field = 'all_nb_games' THEN 'all_nb_games'
                                WHEN :sort_field = 'all_goals' THEN 'all_goals'
                                WHEN :sort_field = 'all_assists' THEN 'all_assists'
                                WHEN :sort_field = 'all_yellow_cards' THEN 'all_yellow_cards'
                                WHEN :sort_field = 'all_red_cards' THEN 'all_red_cards'
                            END    
                    """, nativeQuery = true)
    List<IPlayersByClubCount> findTotalCountOfPlayersByClub(@Param("club_id") Integer club_id,
                                                            @Param("player_id") Integer player_id,
                                                            @Param("player_name") String player_name,
                                                            @Param("player_position") String player_position,
                                                            @Param("nationality_name") String nationality_name,
                                                            @Param("sort_field") String sort_field,
                                                            Pageable pageable);

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
