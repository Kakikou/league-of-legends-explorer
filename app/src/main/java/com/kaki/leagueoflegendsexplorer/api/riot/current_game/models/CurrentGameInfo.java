package com.kaki.leagueoflegendsexplorer.api.riot.current_game.models;

import java.util.List;

/**
 * Created by kevinrodrigues on 14/07/15.
 */
public class CurrentGameInfo {

    public List<BannedChampion> bannedChampions;
    public long gameId;
    public long gameLength;
    public String gameMode;
    public long gameQueueConfigId;
    public long gameStartTime;
    public String gameType;
    public long mapId;
    public Observer observers;
    public List<CurrentGameParticipant> participants;
    public String platformId;

}
