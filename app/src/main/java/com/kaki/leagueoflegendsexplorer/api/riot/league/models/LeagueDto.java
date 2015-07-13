package com.kaki.leagueoflegendsexplorer.api.riot.league.models;

import java.util.List;

/**
 * Created by kevinrodrigues on 13/07/15.
 */
public class LeagueDto {

    // This object contains league information.

    public List<LeagueEntryDto> entries;
    public String name;
    public String participantId;
    public String queue;
    public String tier;
}
