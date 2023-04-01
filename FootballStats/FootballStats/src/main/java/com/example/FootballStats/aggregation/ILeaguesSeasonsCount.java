package com.example.FootballStats.aggregation;

public interface ILeaguesSeasonsCount {
    String getSeason();
    String getName();
    Long getTotalMatches();
    Long getTotalGoals();
    Long getTotalAssists();
    Long getTotalYellowCards();
    Long getTotalRedCards();
    Float getGoalsPerMatch();
    Float getAssistsPerMatch();
    Float getYellowCardsPerMatch();
    Float getRedCardsPerMatch();


}
