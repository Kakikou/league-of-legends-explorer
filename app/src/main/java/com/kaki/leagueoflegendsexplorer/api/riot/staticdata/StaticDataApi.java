package com.kaki.leagueoflegendsexplorer.api.riot.staticdata;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.kaki.leagueoflegendsexplorer.R;
import com.kaki.leagueoflegendsexplorer.api.HttpRequest;
import com.kaki.leagueoflegendsexplorer.api.riot.RequestInterception;
import com.kaki.leagueoflegendsexplorer.api.riot.staticdata.models.champions.ChampionDto;
import com.kaki.leagueoflegendsexplorer.api.riot.staticdata.models.champions.ChampionListDto;
import com.kaki.leagueoflegendsexplorer.api.riot.staticdata.models.champions.ChampionListJson;
import com.kaki.leagueoflegendsexplorer.api.riot.staticdata.models.items.ItemDto;
import com.kaki.leagueoflegendsexplorer.api.riot.staticdata.models.items.ItemListJson;
import com.kaki.leagueoflegendsexplorer.api.riot.staticdata.models.realms.RealmDto;

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
                handleCommonError(context, error);
                request.failure(error);
            }
        });
    }

    public void getChampionsListOnJson(final Context context, final HttpRequest<ChampionListJson> request) {
        m_api.getChampionListOnJson("euw", "all", "fr_FR", new Callback<ChampionListJson>() {
            @Override
            public void success(ChampionListJson championListDto, Response response) {
                Log.d("response", response.getBody().toString());
                request.success(championListDto, response);
            }

            @Override
            public void failure(RetrofitError error) {
                handleCommonError(context, error);
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
                handleCommonError(context, error);
                request.failure(error);
            }
        });
    }

    public void getVersion(final Context context, final HttpRequest<RealmDto> request) {
        m_api.getVersion("euw", new Callback<RealmDto>() {
            @Override
            public void success(RealmDto realmDto, Response response) {
                Log.d("response", response.getBody().toString());
                request.success(realmDto, response);
            }

            @Override
            public void failure(RetrofitError error) {
                handleCommonError(context, error);
                request.failure(error);
            }
        });
    }

    public void getItem(final Context context, Integer id, final HttpRequest<ItemDto> request) {
        m_api.getItem(id, "euw", "all", "fr_FR", new Callback<ItemDto>() {
            @Override
            public void success(ItemDto itemDto, Response response) {
                Log.d("response", response.getBody().toString());
                request.success(itemDto, response);
            }

            @Override
            public void failure(RetrofitError error) {
                handleCommonError(context, error);
                request.failure(error);
            }
        });
    }

    public void getListItemJson(final Context context, final HttpRequest<ItemListJson> request) {
        m_api.getItemListOnJson("euw", "fr_FR", "all", new Callback<ItemListJson>() {
            @Override
            public void success(ItemListJson itemListJson, Response response) {
                Log.d("response", response.getBody().toString());
                request.success(itemListJson, response);
            }

            @Override
            public void failure(RetrofitError error) {
                handleCommonError(context, error);
                request.failure(error);
            }
        });
    }

    private void handleCommonError(Context context, RetrofitError error) {
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
