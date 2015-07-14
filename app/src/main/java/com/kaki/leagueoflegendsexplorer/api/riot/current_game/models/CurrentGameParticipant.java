package com.kaki.leagueoflegendsexplorer.api.riot.current_game.models;

import java.util.List;

/**
 * Created by kevinrodrigues on 14/07/15.
 */
public class CurrentGameParticipant {

    public boolean bot;
    public long championId;
    public List<Mastery> masteries;
    public long profileIconId;
    public List<Rune> runes;
    public long spell1Id;
    public long spell2Id;
    public long summonerId;
    public String summonerName;
    public long teamId;

}
