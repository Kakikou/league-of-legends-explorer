package com.kaki.leagueoflegendsexplorer.api.riot.staticdata;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.kaki.leagueoflegendsexplorer.R;
import com.kaki.leagueoflegendsexplorer.api.HttpRequest;
import com.kaki.leagueoflegendsexplorer.api.riot.RequestInterception;
import com.kaki.leagueoflegendsexplorer.api.riot.staticdata.models.champions.ChampionDto;
import com.kaki.leagueoflegendsexplorer.api.riot.staticdata.models.champions.ChampionListDto;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by kevinrodrigues on 04/07/15.
 */
public class StaticDataApi {

    private StaticDataRoutes m_api;

    public StaticDataApi(Context context) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint("https://global.api.pvp.net")
                .setRequestInterceptor(new RequestInterception(context.getResources()))
                .build();

        m_api = restAdapter.create(StaticDataRoutes.class);
    }

    public void getChampionsList(final Context context, final HttpRequest<ChampionListDto> request) {
        m_api.getChampionList("euw", "all", "fr_FR", new Callback<ChampionListDto>() {
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

    public void getChampion(final Context context, Integer id, final HttpRequest<ChampionDto> request) {
        m_api.getChampion(id, "euw", "all", "fr_FR", new Callback<ChampionDto>() {
            @Override
            public void success(ChampionDto championDto, Response response) {
                Log.d("response", response.getBody().toString());
                request.success(championDto, response);
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
