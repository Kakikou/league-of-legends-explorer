package com.kaki.leagueoflegendsexplorer.api.riot.summoner;

import com.kaki.leagueoflegendsexplorer.api.riot.summoner.models.SummonerDto;

import java.util.Map;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by kevinrodrigues on 12/07/15.
 */
public interface SummonerRoutes {

    @GET("/api/lol/{region}/v1.4/summoner/by-name/{summonerNames}")
    void getSummonerByName(@Path("region") String region,
                           @Path("summonerNames") String summonerName,
                           Callback<Map<String, SummonerDto>> callback);
}
