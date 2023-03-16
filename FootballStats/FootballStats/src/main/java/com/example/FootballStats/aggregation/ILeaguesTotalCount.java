package com.example.FootballStats.aggregation;

public interface ILeaguesTotalCount {
    String getName();
    Long getTotalMatches();
    Long getTotalGoals();
    Long getTotalAssists();
    Float getGoalsPerMatch();
    Float getAssistsPerMatch();
}
