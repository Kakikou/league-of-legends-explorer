package com.kaki.leagueoflegendsexplorer.api.riot.match.models;

import java.util.List;

/**
 * Created by kevinrodrigues on 11/07/15.
 */
public class Event {

    // This object contains game event information.
    // Note that not all legal type values documented below are valid for all games.
    // Event data evolves over time and certain values may be relevant only for older or newer games.

    public String ascendedType;
    public List<Integer> assistingParticipantIds;
    public String buildingType;
    public int creatorId;
    public String eventType;
    public int itemAfter;
    public int itemBefore;
    public int itemId;
    public int killerId;
    public String laneType;
    public String levelUpType;
    public String monsterType;
    public int participantId;
    public Position position;
    public int skillSlot;
    public int teamId;
    public long timestamp;
    public String towerType;
    public int victimId;
    public String wardType;
}
