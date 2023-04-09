package com.example.FootballStats.aggregation;

public interface ILeagueSeasonBestGoalkeeper {
    String getSeason();
    String getLeagueId();
    String getLeagueName();
    String getPlayerId();
    String getPlayerName();
    Long getTotalGames();
    Long getTotalGoalAgainst();
    Float getGoalAgainstPerMatch();
}
