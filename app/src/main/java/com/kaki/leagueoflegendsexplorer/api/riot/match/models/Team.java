package com.kaki.leagueoflegendsexplorer.api.riot.match.models;

import java.util.List;

/**
 * Created by kevinrodrigues on 11/07/15.
 */
public class Team {

    // This object contains team information

    public List<BannedChampion> bans;
    public int baronKills;
    public long dominionVictoryScore;
    public int dragonKills;
    public boolean firstBaron;
    public boolean firstBlood;
    public boolean firstDragon;
    public boolean firstInhibitor;
    public boolean firstTower;
    public int inhibitorKills;
    public int teamId;
    public int towerKills;
    public int vilemawKills;
    public boolean winner;
}
