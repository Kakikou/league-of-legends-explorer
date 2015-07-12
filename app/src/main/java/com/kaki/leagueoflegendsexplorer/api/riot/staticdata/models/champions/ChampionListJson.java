package com.kaki.leagueoflegendsexplorer.api.riot.staticdata.models.champions;

import com.google.gson.JsonObject;

import java.util.Map;

/**
 * Created by kevinrodrigues on 10/07/15.
 */
public class ChampionListJson {

    // This object contains champion list data on json.

    public Map<String, JsonObject> data;
    public String format;
    public Map<String, String> keys;
    public String type;
    public String version;
}
