package com.kaki.leagueoflegendsexplorer.api.riot.match;

import com.kaki.leagueoflegendsexplorer.api.riot.match.models.MatchDetail;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by kevinrodrigues on 11/07/15.
 */
public interface MatchRoute {

    @GET("/api/lol/{region}/v2.2/match/{matchId}")
    void getMatch(@Path("region") String region,
                  @Path("matchId") long matchId,
                  @Query("includeTimeline") boolean includeTimeline,
                  Callback<MatchDetail> callback);
}
