package com.example.FootballStats.aggregation;

public interface IClubsTotalCount {
    Integer getClubId();
    Long getNbSeasons();
    Float getAvgRanks();
    String getClubName();
    Long getAllGoals();
    Long getAllAssists();
    Long getAllYellowCards();
    Long getAllRedCards();
    Float getGoalsPerSeason();
    Float getAssistsPerSeason();
    Float getYellowCardsPerSeason();
    Float getRedCardsPerSeason();
}
