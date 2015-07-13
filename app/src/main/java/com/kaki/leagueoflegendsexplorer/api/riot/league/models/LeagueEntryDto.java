package com.kaki.leagueoflegendsexplorer.api.riot.league.models;

/**
 * Created by kevinrodrigues on 13/07/15.
 */
public class LeagueEntryDto {

    // This object contains league participant information representing a summoner or team.

    public String division;
    public boolean isFreshBlood;
    public boolean isHotStreak;
    public boolean isInactive;
    public boolean isVeteran;
    public int leaguePoints;
    public int losses;
    public MiniSeriesDto miniSeries;
    public String playerOrTeamId;
    public String playerOrTeamName;
    public int wins;
}
