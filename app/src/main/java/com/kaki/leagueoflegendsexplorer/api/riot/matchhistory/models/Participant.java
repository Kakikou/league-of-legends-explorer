package com.kaki.leagueoflegendsexplorer.api.riot.matchhistory.models;

import java.util.List;

/**
 * Created by kevinrodrigues on 09/07/15.
 */
public class Participant {

    // This object contains match participant information

    public int championId;
    public String highestAchievedSeasonTier;
    public List<Mastery> masteries;
    public int participantId;
    public List<Rune> runes;
    public int spell1Id;
    public int spell2Id;
    public ParticipantStats stats;
    public int teamId;
    public ParticipantTimeline timeline;
}
