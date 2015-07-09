package com.kaki.leagueoflegendsexplorer.api.riot.matchhistory;

import com.kaki.leagueoflegendsexplorer.api.HttpRequest;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by kevinrodrigues on 09/07/15.
 */
public interface MatchHistoryRoutes {

    @GET("/api/lol/{region}/v2.2/matchhistory/{summonerId}")
    void getMatch(@Path("region") String region,
                  @Path("summonerId") String summonerId,
                  Callback<?> callback);
}
