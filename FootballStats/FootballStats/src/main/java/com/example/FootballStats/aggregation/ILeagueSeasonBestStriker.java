package com.example.FootballStats.aggregation;

public interface ILeagueSeasonBestStriker {
    String getSeason();
    String getLeagueId();
    String getLeagueName();
    String getPlayerId();
    String getPlayerName();
    Long getTotalGames();
    Long getTotalGoals();
    Float getGoalsPerMatch();
}
