package com.kaki.leagueoflegendsexplorer.api.riot.league;

import com.kaki.leagueoflegendsexplorer.api.riot.league.models.LeagueDto;

import java.util.List;
import java.util.Map;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by kevinrodrigues on 13/07/15.
 */
public interface LeagueRoutes {

    @GET("/api/lol/{region}/v2.5/league/by-summoner/{summonerIds}/entry")
    void getLeagueBySummonerId(@Path("region") String region,
                             @Path("summonerIds") String id,
                             Callback<Map<String, List<LeagueDto>>> callback);
}
