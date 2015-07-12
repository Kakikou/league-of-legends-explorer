package com.kaki.leagueoflegendsexplorer.api.riot.staticdata.models.items;

import com.kaki.leagueoflegendsexplorer.api.riot.staticdata.models.champions.ImageDto;

import java.util.List;
import java.util.Map;

/**
 * Created by kevinrodrigues on 10/07/15.
 */
public class ItemDto {

    // This object contains item data.

    public String colloq;
    public boolean consumeOnFull;
    public boolean consumed;
    public int depth;
    public String description;
    public Map<String, String> effect;
    public List<String> from;
    public GoldDto gold;
    public String group;
    public boolean hideFromAll;
    public int id;
    public ImageDto image;
    public boolean inStore;
    public List<String> into;
    public Map<String, Boolean> maps;
    public String name;
    public String plaintext;
    public String requiredChampion;
    public MetaDataDto rune;
    public String sanitizedDescription;
    public int specialRecipe;
    public int stacks;
    public BasicDataStatsDto stats;
    public List<String> tags;
}
