package com.kaki.leagueoflegendsexplorer.api.riot.league;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.kaki.leagueoflegendsexplorer.R;
import com.kaki.leagueoflegendsexplorer.api.HttpRequest;
import com.kaki.leagueoflegendsexplorer.api.riot.RequestInterception;
import com.kaki.leagueoflegendsexplorer.api.riot.champion_v1_2.models.ChampionListDto;
import com.kaki.leagueoflegendsexplorer.api.riot.league.models.LeagueDto;

import java.util.List;
import java.util.Map;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by kevinrodrigues on 13/07/15.
 */
public class LeagueApi {

    public LeagueRoutes api;

    public LeagueApi(Context context) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint("https://euw.api.pvp.net")
                .setRequestInterceptor(new RequestInterception(context.getResources()))
                .build();
        api = restAdapter.create(LeagueRoutes.class);
    }

    public void getLeagueBySummonerId(final Context context, final String id, final HttpRequest<Map<String, List<LeagueDto>>> request) {
        api.getLeagueBySummonerId("euw", id, new Callback<Map<String, List<LeagueDto>>>() {
            @Override
            public void success(Map<String, List<LeagueDto>> stringListMap, Response response) {
                Log.d("response", response.getBody().toString());
                request.success(stringListMap, response);
            }

            @Override
            public void failure(RetrofitError error) {
                handleError(context, error);
                request.failure(error);
            }
        });
    }

    public void getLeagueBySummonerId(final Context context, final String[] id, final HttpRequest<Map<String, List<LeagueDto>>> request) {
        String ids = new String();
        for (int i = 0; i < id.length; i++) {
            if (id[i] == null)
                continue;
            ids = ids + id[i];
            if (i != id.length - 1)
                ids = ids + ",";
        }
        api.getLeagueBySummonerId("euw", ids, new Callback<Map<String, List<LeagueDto>>>() {
            @Override
            public void success(Map<String, List<LeagueDto>> stringListMap, Response response) {
                Log.d("response", response.getBody().toString());
                request.success(stringListMap, response);
            }

            @Override
            public void failure(RetrofitError error) {
                handleError(context, error);
                request.failure(error);
            }
        });
    }

    private void handleError(Context context, RetrofitError error) {
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
    }
}
