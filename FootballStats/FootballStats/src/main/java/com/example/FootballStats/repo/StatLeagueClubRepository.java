package com.example.FootballStats.repo;
import com.example.FootballStats.aggregation.ILeaguesSeasonsCount;
import com.example.FootballStats.aggregation.ILeaguesTotalCount;
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
                    FROM View_leagues_aggregated_data
                    GROUP BY name"""
            , nativeQuery = true)
    List<ILeaguesTotalCount> findTotalCountOfLeagues();

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
                        FROM View_leagues_aggregated_data
                          WHERE season LIKE :season
                    """, nativeQuery = true)
    List<ILeaguesSeasonsCount> findCountBySeasonOfLeagues(@Param("season")String season);

}
