package com.kaki.leagueoflegendsexplorer.api.riot.staticdata.models.items;

import java.util.List;
import java.util.Map;

/**
 * Created by kevinrodrigues on 10/07/15.
 */
public class ItemListDto {

    // This object contains item list data.

    public BasicDataDto basic;
    public Map<String, ItemDto> data;
    public List<GroupDto> groups;
    public List<ItemTreeDto> tree;
    public String type;
    public String version;
}
