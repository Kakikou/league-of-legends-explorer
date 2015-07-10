package com.kaki.leagueoflegendsexplorer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.kaki.leagueoflegendsexplorer.api.HttpRequest;
import com.kaki.leagueoflegendsexplorer.api.riot.staticdata.StaticDataApi;
import com.kaki.leagueoflegendsexplorer.api.riot.staticdata.models.champions.ChampionListJson;
import com.kaki.leagueoflegendsexplorer.api.riot.staticdata.models.realms.RealmDto;
import com.kaki.leagueoflegendsexplorer.utils.CacheChampions;
import com.kaki.leagueoflegendsexplorer.utils.VersionBases;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by kevinrodrigues on 10/07/15.
 */
public class SplashActivity extends Activity {

    private VersionBases versionBases;
    private CacheChampions cacheChampions;
    private StaticDataApi api;
    private int nbToUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = new StaticDataApi(this);
        api.getVersion(this, onGetVersion);
        versionBases = new VersionBases(this);
        cacheChampions = new CacheChampions(this);
    }

    private void startActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }

    private boolean updateBases(RealmDto realmDto) {
        String versionChamp;
        String versionChampBase;

        versionChamp = realmDto.n.get("champion");
        versionChampBase = versionBases.getVersionChamp();
        Log.d("Version", "Champion server " + versionChamp);
        Log.d("Version", "Champion local " + versionChampBase);
        if (!versionChamp.equals(versionChampBase)) {
            api.getChampionsListOnJson(getBaseContext(), onGetAllChamp);
            nbToUpdate++;
        }
        return nbToUpdate != 0;
    }

    private HttpRequest<RealmDto> onGetVersion = new HttpRequest<RealmDto>() {
        @Override
        public void success(RealmDto realmDto, Response response) {
            if (!updateBases(realmDto))
                startActivity();
        }

        @Override
        public void failure(RetrofitError error) {

        }
    };

    private HttpRequest<ChampionListJson> onGetAllChamp = new HttpRequest<ChampionListJson>() {
        @Override
        public void success(ChampionListJson championListJson, Response response) {
            cacheChampions.storeChampions(championListJson);
            versionBases.updateVersionChamp(championListJson.version);
            nbToUpdate--;
            if (nbToUpdate == 0)
                startActivity();
        }

        @Override
        public void failure(RetrofitError error) {
            startActivity();
        }
    };
}
