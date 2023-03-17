package com.example.FootballStats.repo;
import com.example.FootballStats.aggregation.ILeaguesSeasonsCount;
import com.example.FootballStats.aggregation.ILeaguesTotalCount;
import com.example.FootballStats.entity.Club;
import com.example.FootballStats.entity.League;

import com.example.FootballStats.entity.StatLeagueClub;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface StatLeagueClubRepository extends CrudRepository<StatLeagueClub, Long>{

    Iterable<StatLeagueClub> findAll(Sort id);

    List<StatLeagueClub> findByLeague(Optional<League> league);

    List<StatLeagueClub> findByClub(Optional<Club> club);

    List<StatLeagueClub> findByClubAndSeason(Optional<Club> club, String season);

    @Query(value=
            """
                    SELECT player_total_goal_assist_by_league.name, totalmatches, totalgoals, totalassists, ROUND(totalgoals/totalmatches,2) as goalspermatch, ROUND(totalassists/totalmatches,2) as assistspermatch
                    FROM
                    (SELECT ROUND(SUM(nb_game)/2) as totalmatches, league.name FROM stat_league_club
                    INNER JOIN league ON league.id = stat_league_club.league_id
                    GROUP BY league.name) league_total_match_by_league
                    INNER JOIN
                    (SELECT SUM(goal) as totalgoals,SUM(assist) as totalassists, league.name  FROM stat_player
                    INNER JOIN club ON stat_player.club_id = club.id
                    INNER JOIN league ON club.league_id = league.id
                    GROUP BY league.name) player_total_goal_assist_by_league
                    ON league_total_match_by_league.name = player_total_goal_assist_by_league.name"""
            , nativeQuery = true)
    List<ILeaguesTotalCount> findTotalCountOfLeagues();

    @Query(value=
            """
                    SELECT player_season_goal_assist_by_league.season, player_season_goal_assist_by_league.name, totalmatches, totalgoals, totalassists, ROUND(totalgoals/totalmatches,2) as goalspermatch, ROUND(totalassists/totalmatches,2) as assistspermatch\s
                    FROM
                    (SELECT stat_league_club.season,ROUND(SUM(nb_game)/2) as totalmatches, league.name FROM stat_league_club
                    INNER JOIN league ON league.id = stat_league_club.league_id
                    GROUP BY league.name, stat_league_club.season) league_season_match_by_league
                    INNER JOIN
                    (SELECT stat_player.season, SUM(goal) as totalgoals,SUM(assist) as totalassists, league.name FROM stat_player
                    INNER JOIN club ON stat_player.club_id = club.id
                    INNER JOIN league ON club.league_id = league.id
                    GROUP BY league.name, stat_player.season) player_season_goal_assist_by_league
                    ON league_season_match_by_league.name = player_season_goal_assist_by_league.name
                    WHERE league_season_match_by_league.season = player_season_goal_assist_by_league.season
                 """, nativeQuery = true)
    List<ILeaguesSeasonsCount> findCountBySeasonOfLeagues();

}
