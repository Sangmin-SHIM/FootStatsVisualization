package com.example.FootballStats.aggregation;

public interface ILeagueAllTimeBestClub {
    String getLeagueName();
    Integer getLeagueId();
    String getClubName();
    Integer getClubId();
    Float getRankAverage();
    Long getNbWin();

}
