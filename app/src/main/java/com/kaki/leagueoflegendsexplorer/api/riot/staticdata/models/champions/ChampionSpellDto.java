package com.kaki.leagueoflegendsexplorer.api.riot.staticdata.models.champions;

import java.util.List;

/**
 * Created by kevinrodrigues on 04/07/15.
 */
public class ChampionSpellDto {

    // This object contains champion spell data.

    public List<ImageDto> altimages;
    public List<Double> cooldown;
    public String cooldownBurn;
    public List<Integer> cost;
    public String costBurn;
    public String costType;
    public String description;
    public List<Double> effect;
    public List<String> effectBurn;
    public ImageDto image;
    public String key;
    public LevelTipDto leveltip;
    public int maxrank;
    public String name;
    public List<String> range;
    public String rangeBurn;
    public String resource;
    public String sanitizedDescription;
    public String sanitizedTooltip;
    public String tooltip;
    public List<SpellVarsDto> vars;
}
