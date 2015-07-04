package com.kaki.leagueoflegendsexplorer.api.riot.staticdata.models.champions;

import java.util.Map;

/**
 * Created by kevinrodrigues on 04/07/15.
 */
public class ChampionListDto {

    // This object contains champion list data.

    public Map<String, ChampionDto> data;
    public String format;
    public Map<String, String> keys;
    public String type;
    public String version;
}
