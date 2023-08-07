package com.example.FootballStats.repo;

import com.example.FootballStats.aggregation.IPlayersByClubCount;
import com.example.FootballStats.customization.NationalityNameByClub;
import com.example.FootballStats.customization.NationalityNameByClubAndSeason;
import com.example.FootballStats.entity.Nationality;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NationalityRepository extends CrudRepository<Nationality, Long> {

    Iterable<Nationality> findAll(Sort id);

    @Query(value=
            """
                    SELECT DISTINCT
                          nationality_name as nationalityname
                    FROM materialized_view_players_by_club_aggregated_data
                        WHERE (:club_id IS NULL OR club_id = :club_id)
                            AND player_position NOT LIKE 'GK'   
                    """, nativeQuery = true)
    List<NationalityNameByClub> findTotalNationalitiesByClub(@Param("club_id") Integer club_id);



    @Query(value=
            """
                    SELECT DISTINCT 
                        stat_player.season as season,
                        nationality.name_original as nationalityname
                    FROM stat_player
                        INNER JOIN player ON player.id = stat_player.player_id
                        INNER JOIN nationality ON player.nationality_id = nationality.id
                    WHERE (:club_id IS NULL OR club_id = :club_id)
                        AND (:season IS NULL OR stat_player.season = :season)
                        AND player.position NOT LIKE 'GK'  
                    """, nativeQuery = true)
    List<NationalityNameByClubAndSeason> findNationalitiesByClubAndSeason(@Param("club_id") Integer club_id, @Param("season") String season);
}
