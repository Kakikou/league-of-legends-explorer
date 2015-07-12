package com.kaki.leagueoflegendsexplorer.api.riot.matchhistory;

import com.kaki.leagueoflegendsexplorer.api.HttpRequest;
import com.kaki.leagueoflegendsexplorer.api.riot.matchhistory.models.PlayerHistory;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by kevinrodrigues on 09/07/15.
 */
public interface MatchHistoryRoutes {

    @GET("/api/lol/{region}/v2.2/matchhistory/{summonerId}")
    void getMatch(@Path("region") String region,
                  @Path("summonerId") long summonerId,
                  @Query("beginIndex") int beginIndex,
                  @Query("endIndex") int endIndex,
                  Callback<PlayerHistory> callback);
}
