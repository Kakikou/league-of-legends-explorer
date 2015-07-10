package com.kaki.leagueoflegendsexplorer.api.riot.game;

import com.kaki.leagueoflegendsexplorer.api.riot.game.models.RecentGamesDto;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by kevinrodrigues on 10/07/15.
 */
public interface GameRoutes {

    @GET("/api/lol/{region}/v1.3/game/by-summoner/{summonerId}/recent")
    void getGames(@Path("region") String region,
                  @Path("summonerId") long summonerId,
                  Callback<RecentGamesDto> callback);
}
