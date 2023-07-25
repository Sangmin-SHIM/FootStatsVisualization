package com.example.FootballStats.aggregation;

public interface IPlayersByClubCount {
    Integer getClubId();
    Integer getPlayerId();
    String getPlayerName();
    String getPlayerPosition();
    String getNationalityName();
    String getClubName();
    Long getAllNbGames();
    Long getAllGoals();
    Long getAllAssists();
    Long getAllYellowCards();
    Long getAllRedCards();
    Float getAvgMinutes();
    Float getGoalsPerGame();
    Float getAssistsPerGame();
}
