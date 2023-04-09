package com.example.FootballStats.aggregation;

public interface ILeagueAllTimeBestGoalkeeper {
    String getPlayerName();
    Integer getPlayerId();
    String getLeagueName();
    Integer getLeagueId();
    Long getTotalGoalsAgainst();
    Long getTotalMatches();
    Float getGoalsAgainstPerMatches();
}
