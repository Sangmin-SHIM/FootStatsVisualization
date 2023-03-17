package com.example.FootballStats.aggregation;

public interface ILeaguesSeasonsCount {
    String getSeason();
    String getName();
    Long getTotalMatches();
    Long getTotalGoals();
    Long getTotalAssists();
    Float getGoalsPerMatch();
    Float getAssistsPerMatch();

}
