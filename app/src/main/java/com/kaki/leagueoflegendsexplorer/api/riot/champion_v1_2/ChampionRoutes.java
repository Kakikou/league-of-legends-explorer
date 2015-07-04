package com.kaki.leagueoflegendsexplorer.api.riot.champion_v1_2;

import com.kaki.leagueoflegendsexplorer.api.riot.champion_v1_2.models.ChampionListDto;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by kevinrodrigues on 04/07/15.
 */
public interface ChampionRoutes {

    @GET("/api/lol/{region}/v1.2/champion")
    void getListChamp(@Path("region") String region, @Query("freeToPlay") boolean freeToPlay, Callback<ChampionListDto> callback);
}
