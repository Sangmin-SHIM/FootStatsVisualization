package com.example.FootballStats.aggregation;

public interface ILeagueAllTimeBestStriker {
    String getPlayerName();
    Integer getPlayerId();
    String getLeagueName();
    Integer getLeagueId();
    Long getTotalGoals();
    Long getTotalMatches();
    Float getGoalsPerMatches();
}
