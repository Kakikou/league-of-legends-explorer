package com.kaki.leagueoflegendsexplorer.api.riot.staticdata;

import com.kaki.leagueoflegendsexplorer.api.riot.staticdata.models.champions.ChampionDto;
import com.kaki.leagueoflegendsexplorer.api.riot.staticdata.models.champions.ChampionListDto;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by kevinrodrigues on 04/07/15.
 */
public interface StaticDataRoutes {

    @GET("/api/lol/static-data/{region}/v1.2/champion")
    void getChampionList(@Path("region") String region,
                         @Query("champData") String champData,
                         @Query("locale") String locale,
                         Callback<ChampionListDto> callback);

    @GET("/api/lol/static-data/{region}/v1.2/champion/{id}")
    void getChampion(@Path("id") Integer id,
                     @Path("region") String region,
                     @Query("champData") String champData,
                     @Query("locale") String locale,
                     Callback<ChampionDto> callback);

}
