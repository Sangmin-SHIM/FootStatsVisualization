package com.example.FootballStats.aggregation;

public interface ILeagueSeasonBestPlaymaker {
    String getSeason();
    String getLeagueId();
    String getLeagueName();
    String getPlayerId();
    String getPlayerName();
    Long getTotalGames();
    Long getTotalAssists();
    Float getAssistsPerMatch();
}
