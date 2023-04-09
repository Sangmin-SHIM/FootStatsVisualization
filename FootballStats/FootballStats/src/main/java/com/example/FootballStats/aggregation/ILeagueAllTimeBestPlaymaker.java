package com.example.FootballStats.aggregation;

public interface ILeagueAllTimeBestPlaymaker {
    String getPlayerName();
    Integer getPlayerId();
    String getLeagueName();
    Integer getLeagueId();
    Long getTotalAssists();
    Long getTotalMatches();
    Float getAssistsPerMatches();
}
