package com.example.FootballStats.aggregation;

import jakarta.persistence.criteria.CriteriaBuilder;

public interface IGkPlayersByClubCount {
    Integer getClubId();
    Integer getPlayerId();
    String getPlayerName();
    String getPlayerPosition();
    String getNationalityName();
    String getClubName();
    Long getAllNbGames();
    Long getAllWins();
    Long getAllDraws();
    Long getAllLoses();
    Long getAllGas();
    Long getAllSaves();
    Long getAllSotas();
    Long getAllCs();
    Float getAvgGa90();
    Float getAvgSavePercent();
    Float getAvgCsPercent();
}
