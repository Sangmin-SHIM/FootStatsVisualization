package com.example.FootballStats.aggregation;

public interface ILeagueAllTimeBestClub {
    String getLeagueName();
    Integer getLeagueId();
    String getClubName();
    Integer getClubId();
    Integer getNbVictory();
    Float getAvgRank();

}
