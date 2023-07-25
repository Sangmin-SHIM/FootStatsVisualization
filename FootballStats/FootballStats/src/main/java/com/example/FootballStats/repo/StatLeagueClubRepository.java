package com.example.FootballStats.repo;
import com.example.FootballStats.aggregation.*;
import com.example.FootballStats.entity.Club;
import com.example.FootballStats.entity.League;

import com.example.FootballStats.entity.StatLeagueClub;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StatLeagueClubRepository extends CrudRepository<StatLeagueClub, Long>{

    Iterable<StatLeagueClub> findAll(Sort id);

    List<StatLeagueClub> findByLeague(Optional<League> league);

    List<StatLeagueClub> findByClub(Optional<Club> club);

    List<StatLeagueClub> findByClubAndSeason(Optional<Club> club, String season);
    List<StatLeagueClub> findByLeagueAndSeason(Optional<League> league, String season);

    // Total Count of Leagues : All Time Goals, Assists, Yellow Cards, Red Cards
    @Query(value=
            """
                    SELECT
                    	name,
                    	SUM(total_matches) as allmatches,
                    	SUM(total_goals) as allgoals,
                    	SUM(total_assists) as allassists,
                    	SUM(total_yellow_cards) as allyellowcards,
                    	SUM(total_red_cards) as allredcards,
                    	ROUND(SUM(goals_per_match)/COUNT(*),2) as goalspermatch,
                    	ROUND(SUM(assists_per_match)/COUNT(*),2) as assistspermatch,
                    	ROUND(SUM(yellow_cards_per_match)/COUNT(*),2) as yellowcardspermatch,
                    	ROUND(SUM(red_cards_per_match)/COUNT(*),2) as redcardspermatch	
                    FROM materialized_view_leagues_aggregated_data
                    GROUP BY name"""
            , nativeQuery = true)
    List<ILeaguesTotalCount> findTotalCountOfLeagues();

    // Total Count of Leagues in Season : Goals, Assists, Yellow Cards, Red Cards
    @Query(value=
            """
                       SELECT 
                            name,
                            season,
                            total_goals as totalgoals,
                            total_matches as totalmatches,
                            total_assists as totalassists,
                            total_yellow_cards as totalyellowcards,
                            total_red_cards as totalredcards,
                            goals_per_match as goalspermatch,
                            assists_per_match as assistspermatch,
                            yellow_cards_per_match as yellowcardspermatch,
                            red_cards_per_match as redcardspermatch
                        FROM materialized_view_leagues_aggregated_data
                           WHERE (:season IS NULL OR season LIKE :season)
                    """, nativeQuery = true)
    List<ILeaguesSeasonsCount> findCountBySeasonOfLeagues(@Param("season")String season);

    // All Time Best Clubs in 5 Leagues
    @Query(value=
            """
                    SELECT
                          leagueid,
                          leaguename,
                          clubid,
                          clubname,
                          rankaverage,
                          nbwin
                    FROM materialized_view_league_all_time_best_club_aggregated_data
                         WHERE (:league_id IS NULL OR leagueid = :league_id)
                    """, nativeQuery = true)
    List<ILeagueAllTimeBestClub> findAllTimeBestClub(@Param("league_id") Integer league_id);

    // Best Club in 5 Leagues in a Season
    @Query(value=
            """
                       SELECT 
                            season,
                            leaguename,
                            leagueid,
                            clubid,
                            clubname
                        FROM materialized_view_league_best_club_aggregated_data
                           WHERE (:season IS NULL OR season LIKE :season)
                           AND (:league_id IS NULL OR leagueid = :league_id)
                    """, nativeQuery = true)
    List<ILeagueSeasonBestClub> findSeasonBestClub(@Param("season")String season,@Param("league_id") Integer league_id);

    @Query(value= """
                        SELECT
                            season,
                            league_id as leagueid,
                            league_name as leaguename,
                            nationality_name as nationalityname,
                            nb_nationalities as nbnationalities
                        FROM materialized_view_leagues_nationalities_by_season_aggregated_data
                           WHERE (:season IS NULL OR season LIKE :season)
                           AND (:league_id IS NULL OR league_id = :league_id)                        
            """, nativeQuery = true)
    List<ILeagueSeasonNationalitiesNumber> findSeasonNationalitiesNumber(@Param("season")String season,@Param("league_id") Integer league_id);

    @Query(value= """
                        SELECT
                            league_id as leagueid,
                            league_name as leaguename,
                            nationality_name as nationalityname,
                            nb_nationalities as nbnationalities
                        FROM materialized_view_leagues_nationalities_aggregated_data
                           WHERE (:league_id IS NULL OR league_id = :league_id)                        
            """, nativeQuery = true)
    List<ILeagueNationalitiesNumber> findNationalitiesNumber(@Param("league_id") Integer league_id);

    @Query(value= """
                        SELECT
                            club_id as clubid,
                            nb_seasons as nbseasons,
                            avg_ranks as avgranks,
                            club_name as clubname,
                            all_goals as allgoals,
                            all_assists as allassists,
                            all_yellow_cards as allyellowcards,
                            all_red_cards as allredcards,
                            goals_per_season as goalsperseason,
                            assists_per_season as assistsperseason,
                            yellow_cards_per_season as yellowcardsperseason,
                            red_cards_per_season as redcardsperseason
                        FROM materialized_view_clubs_aggregated_data
                           WHERE (:club_id IS NULL OR club_id = :club_id)                        
            """, nativeQuery = true)
    List<IClubsTotalCount> findTotalCountOfClubs(@Param("club_id") Integer club_id);
}