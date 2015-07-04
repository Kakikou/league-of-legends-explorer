package com.kaki.leagueoflegendsexplorer.api.riot.champion_v1_2;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.kaki.leagueoflegendsexplorer.R;
import com.kaki.leagueoflegendsexplorer.api.HttpRequest;
import com.kaki.leagueoflegendsexplorer.api.riot.RequestInterception;
import com.kaki.leagueoflegendsexplorer.api.riot.champion_v1_2.models.ChampionListDto;
import com.kaki.leagueoflegendsexplorer.api.riot.staticdata.StaticDataRoutes;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by kevinrodrigues on 04/07/15.
 */
public class ChampionV12Api {

    private ChampionRoutes m_api;

    public ChampionV12Api(Context context) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint("https://euw.api.pvp.net")
                .setRequestInterceptor(new RequestInterception(context.getResources()))
                .build();

        m_api = restAdapter.create(ChampionRoutes.class);
    }

    public void getFreeChampList(final Context context, final HttpRequest<ChampionListDto> request) {
        m_api.getListChamp("euw", true, new Callback<ChampionListDto>() {
            @Override
            public void success(ChampionListDto championListDto, Response response) {
                Log.d("response", response.getBody().toString());
                request.success(championListDto, response);
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
