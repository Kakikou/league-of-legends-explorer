package com.kaki.leagueoflegendsexplorer.api.riot.game.models;

import java.util.List;

/**
 * Created by kevinrodrigues on 10/07/15.
 */
public class GameDto {

    // This object contains game information.

    public int championId;
    public long createDate;
    public List<PlayerDto> fellowPlayers;
    public long gameId;
    public String gameMode;
    public String gameType;
    public boolean invalid;
    public int ipEarned;
    public int level;
    public int mapId;
    public int spell1;
    public int spell2;
    public RawStatsDto stats;
    public String subType;
    public int teamId;
}
