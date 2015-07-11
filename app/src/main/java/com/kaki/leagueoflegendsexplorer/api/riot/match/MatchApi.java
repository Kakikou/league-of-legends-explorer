package com.kaki.leagueoflegendsexplorer.api.riot.match;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.kaki.leagueoflegendsexplorer.R;
import com.kaki.leagueoflegendsexplorer.api.HttpRequest;
import com.kaki.leagueoflegendsexplorer.api.riot.RequestInterception;
import com.kaki.leagueoflegendsexplorer.api.riot.match.models.MatchDetail;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by kevinrodrigues on 11/07/15.
 */
public class MatchApi {

    private final MatchRoute api;

    public MatchApi(Context context) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint("https://euw.api.pvp.net")
                .setRequestInterceptor(new RequestInterception(context.getResources()))
                .build();

        api = restAdapter.create(MatchRoute.class);
    }

    public void getMatch(final Context context, final long matchId, final HttpRequest<MatchDetail> request) {
        api.getMatch("euw", matchId, true, new Callback<MatchDetail>() {
            @Override
            public void success(MatchDetail matchDetail, Response response) {
                Log.d("response", response.getBody().toString());
                request.success(matchDetail, response);
            }

            @Override
            public void failure(RetrofitError error) {
                if (error.getResponse() == null) {
                    Toast.makeText(context, R.string.no_internet, Toast.LENGTH_LONG).show();
                } else {
                    switch (error.getResponse().getStatus()) {
                        case 400:
                        case 401:
                        case 429:
                        case 500:
                        case 503:
                            Toast.makeText(context, R.string.error_with_server, Toast.LENGTH_LONG).show();
                            break;
                    }
                }
                request.failure(error);
            }
        });
    }
}
