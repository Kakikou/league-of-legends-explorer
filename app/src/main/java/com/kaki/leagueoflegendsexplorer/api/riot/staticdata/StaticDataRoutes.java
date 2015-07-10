package com.kaki.leagueoflegendsexplorer.api.riot.staticdata;

import com.kaki.leagueoflegendsexplorer.api.riot.staticdata.models.champions.ChampionDto;
import com.kaki.leagueoflegendsexplorer.api.riot.staticdata.models.champions.ChampionListDto;
import com.kaki.leagueoflegendsexplorer.api.riot.staticdata.models.champions.ChampionListJson;
import com.kaki.leagueoflegendsexplorer.api.riot.staticdata.models.realms.RealmDto;

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

    @GET("/api/lol/static-data/{region}/v1.2/champion")
    void getChampionListOnJson(@Path("region") String region,
                         @Query("champData") String champData,
                         @Query("locale") String locale,
                         Callback<ChampionListJson> callback);

    @GET("/api/lol/static-data/{region}/v1.2/champion/{id}")
    void getChampion(@Path("id") Integer id,
                     @Path("region") String region,
                     @Query("champData") String champData,
                     @Query("locale") String locale,
                     Callback<ChampionDto> callback);

    @GET("/api/lol/static-data/{region}/v1.2/realm")
    void getVersion(@Path("region") String region,
                    Callback<RealmDto> callback);

}
