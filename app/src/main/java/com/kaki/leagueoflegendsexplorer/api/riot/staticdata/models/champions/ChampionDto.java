package com.kaki.leagueoflegendsexplorer.api.riot.staticdata.models.champions;

import java.util.List;

/**
 * Created by kevinrodrigues on 04/07/15.
 */
public class ChampionDto {

    // This object contains champion data.

    public List<String> allytips;
    public String blurb;
    public List<String> enemytips;
    public int id;
    public ImageDto image;
    public InfoDto info;
    public String key;
    public String lore;
    public String name;
    public String partype;
    public PassiveDto passive;
    public List<RecommendedDto> recommended;
    public List<SkinDto> skins;
    public List<ChampionSpellDto> spells;
    public StatsDto stats;
    public List<String> tags;
    public String title;
}
