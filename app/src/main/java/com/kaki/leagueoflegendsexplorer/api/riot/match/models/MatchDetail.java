package com.kaki.leagueoflegendsexplorer.api.riot.match.models;

import com.kaki.leagueoflegendsexplorer.api.riot.matchhistory.models.Participant;
import com.kaki.leagueoflegendsexplorer.api.riot.matchhistory.models.ParticipantIdentity;

import java.util.List;

/**
 * Created by kevinrodrigues on 11/07/15.
 */
public class MatchDetail {

    // This object contains match detail information

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
    public List<Team> teams;
    public Timeline timeline;
}
