package com.example.FootballStats.aggregation;

public interface IPlayerCount {

    Integer getPlayerId();
    String getPlayerName();
    String getPlayerPosition();
    String getNationalityName();
    Long getAllNbGames();
    Long getAllGoals();
    Long getAllAssists();
    Long getAllYellowCards();
    Long getAllRedCards();
    Float getAvgMinutes();
}
