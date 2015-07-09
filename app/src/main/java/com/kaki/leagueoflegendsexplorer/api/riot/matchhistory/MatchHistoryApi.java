package com.kaki.leagueoflegendsexplorer.api.riot.matchhistory;

import android.content.Context;

import com.kaki.leagueoflegendsexplorer.api.HttpRequest;
import com.kaki.leagueoflegendsexplorer.api.riot.RequestInterception;

import retrofit.RestAdapter;

/**
 * Created by kevinrodrigues on 09/07/15.
 */
public class MatchHistoryApi {

    private MatchHistoryRoutes m_api;

    public MatchHistoryApi(Context context) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint("https://global.api.pvp.net")
                .setRequestInterceptor(new RequestInterception(context.getResources()))
                .build();

        m_api = restAdapter.create(MatchHistoryRoutes.class);
    }

    public void getMatch(final Context context, final HttpRequest<?> request) {

    }
}
