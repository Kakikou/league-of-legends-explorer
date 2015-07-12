package com.kaki.leagueoflegendsexplorer.api.riot.match.models;

import java.util.List;
import java.util.Map;

/**
 * Created by kevinrodrigues on 11/07/15.
 */
public class Frame {

    // This object contains game frame information

    public List<Event> events;
    public Map<String, ParticipantFrame> participantFrames;
    public long timestamp;
}
