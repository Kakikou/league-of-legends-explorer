package com.kaki.leagueoflegendsexplorer.api.riot.current_game;

import com.kaki.leagueoflegendsexplorer.api.riot.current_game.models.CurrentGameInfo;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by kevinrodrigues on 14/07/15.
 */
public interface CurrentGameRoutes {

    @GET("/observer-mode/rest/consumer/getSpectatorGameInfo/{platformId}/{summonerId}")
    void getCurrentGame(@Path("platformId") String platformId,
                        @Path("summonerId") String summonerId,
                        Callback<CurrentGameInfo> callback);
}
