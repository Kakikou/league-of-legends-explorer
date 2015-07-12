package com.kaki.leagueoflegendsexplorer.api.riot.staticdata.models.items;

import com.google.gson.JsonObject;

import java.util.List;
import java.util.Map;

/**
 * Created by kevinrodrigues on 11/07/15.
 */
public class ItemListJson {

    // This object contains item list data on json.

    public BasicDataDto basic;
    public Map<String, JsonObject> data;
    public List<GroupDto> groups;
    public List<ItemTreeDto> tree;
    public String type;
    public String version;
}
