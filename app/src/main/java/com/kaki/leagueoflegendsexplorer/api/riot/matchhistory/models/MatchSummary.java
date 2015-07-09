package com.kaki.leagueoflegendsexplorer.api.riot.matchhistory.models;

import java.util.List;

/**
 * Created by kevinrodrigues on 09/07/15.
 */
public class MatchSummary {

    // This object contains match summary information

    public int mapId;
    public long matchCreation;
    public long matchDuration;
    public long matchId;
    public String matchMode;
    public String matchType;
    public String matchVersion;
    public List<ParticipantIdentity> participantIdentities;
    public List<Participant> participants;
    public String platformId;
    public String queueType;
    public String region;
    public String season;
}
