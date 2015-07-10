package com.kaki.leagueoflegendsexplorer.api.riot.game.models;

import java.util.Set;

/**
 * Created by kevinrodrigues on 10/07/15.
 */
public class RecentGamesDto {

    // This object contains recent games information.

    public long summonerId;
    public Set<GameDto> games;
}
