package com.kaki.leagueoflegendsexplorer.api.riot.staticdata.models.champions;

import java.util.List;

/**
 * Created by kevinrodrigues on 04/07/15.
 */
public class RecommendedDto {

    // This object contains champion recommended data.

    public List<BlockDto> blocks;
    public String champion;
    public String map;
    public String mode;
    public boolean priority;
    public String title;
    public String type;
}
