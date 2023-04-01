package com.example.FootballStats.aggregation;

public interface ILeaguesTotalCount {
    String getName();
    Long getAllMatches();
    Long getAllGoals();
    Long getAllAssists();
    Long getAllYellowCards();
    Long getAllRedCards();
    Float getGoalsPerMatch();
    Float getAssistsPerMatch();
    Float getYellowCardsPerMatch();
    Float getRedCardsPerMatch();
}
