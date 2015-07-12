package com.kaki.leagueoflegendsexplorer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.kaki.leagueoflegendsexplorer.api.HttpRequest;
import com.kaki.leagueoflegendsexplorer.api.riot.staticdata.StaticDataApi;
import com.kaki.leagueoflegendsexplorer.api.riot.staticdata.models.champions.ChampionListJson;
import com.kaki.leagueoflegendsexplorer.api.riot.staticdata.models.items.ItemListJson;
import com.kaki.leagueoflegendsexplorer.api.riot.staticdata.models.realms.RealmDto;
import com.kaki.leagueoflegendsexplorer.utils.CacheChampions;
import com.kaki.leagueoflegendsexplorer.utils.CacheItem;
import com.kaki.leagueoflegendsexplorer.utils.DragonMagicServer;
import com.kaki.leagueoflegendsexplorer.utils.VersionBases;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by kevinrodrigues on 10/07/15.
 */
public class SplashActivity extends Activity {

    private VersionBases versionBases;
    private CacheChampions cacheChampions;
    private CacheItem cacheItem;
    private DragonMagicServer dragonMagicServer;
    private StaticDataApi api;
    private int nbToUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = new StaticDataApi(this);
        api.getVersion(this, onGetVersion);
        versionBases = new VersionBases(this);
        cacheChampions = new CacheChampions(this);
        cacheItem = new CacheItem(this);
        dragonMagicServer = new DragonMagicServer(this);
    }

    private void startActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }

    private boolean updateBases(RealmDto realmDto) {
        updateChamp(realmDto);
        updateItems(realmDto);
        updateDragonMagicServer(realmDto);
        return nbToUpdate != 0;
    }

    private void updateDragonMagicServer(RealmDto realmDto) {
        String versionServer;
        String versionLocal;

        versionServer = realmDto.dd;
        versionLocal = versionBases.getVersionDragonMagicServer();
        Log.d("Version", "DragonMagicServer server : " + versionServer);
        Log.d("Version", "DragonMagicServer local : " + versionLocal);
        if (!versionServer.equals(versionLocal)) {
            dragonMagicServer.updateUrl(realmDto);
            versionBases.updateVersionDragonMagicServer(realmDto.dd);
        }
    }

    private void updateItems(RealmDto realmDto) {
        String versionServer;
        String versionLocal;

        versionServer = realmDto.n.get("item");
        versionLocal = versionBases.getVersionItems();
        Log.d("Version", "Item server : " + versionServer);
        Log.d("Version", "Item local : " + versionLocal);
        if (!versionServer.equals(versionLocal)) {
            api.getListItemJson(this, onGetItems);
            nbToUpdate++;
        }
    }

    private void updateChamp(RealmDto realmDto) {
        String versionChampServer;
        String versionChampBase;

        versionChampServer = realmDto.n.get("champion");
        versionChampBase = versionBases.getVersionChamp();
        Log.d("Version", "Champion server " + versionChampServer);
        Log.d("Version", "Champion local " + versionChampBase);
        if (!versionChampServer.equals(versionChampBase)) {
            api.getChampionsListOnJson(this, onGetAllChamp);
            nbToUpdate++;
        }
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

    private HttpRequest<ItemListJson> onGetItems = new HttpRequest<ItemListJson>() {
        @Override
        public void success(ItemListJson itemListJson, Response response) {
            cacheItem.storeItem(itemListJson);
            versionBases.updateVersionItem(itemListJson.version);
            nbToUpdate--;
            if (nbToUpdate == 0)
                startActivity();
        }

        @Override
        public void failure(RetrofitError error) {
            startActivity();
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
