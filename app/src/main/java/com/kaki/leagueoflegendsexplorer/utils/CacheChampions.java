package com.kaki.leagueoflegendsexplorer.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.kaki.leagueoflegendsexplorer.api.riot.staticdata.models.champions.ChampionDto;
import com.kaki.leagueoflegendsexplorer.api.riot.staticdata.models.champions.ChampionListDto;
import com.kaki.leagueoflegendsexplorer.api.riot.staticdata.models.champions.ChampionListJson;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by kevinrodrigues on 10/07/15.
 */
public class CacheChampions {

    private static final String NAME_SHARED = "com.kaki.leagueoflegendsexplorer.utils.CacheChampions";
    private static final String SET_ID = "com.kaki.leagueoflegendsexplorer.utils.SET_ID";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public CacheChampions(Context context) {
        sharedPreferences = context.getSharedPreferences(NAME_SHARED, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void storeChampions(ChampionListJson championListJson) {
        Set<String> idSet = new HashSet<>();

        for (Map.Entry<String, JsonObject> entry : championListJson.data.entrySet()) {
            int id = entry.getValue().get("id").getAsInt();
            idSet.add("" + id);
            editor.putString("" + id, "" + entry.getValue());
        }
        editor.putStringSet(SET_ID, idSet);
        editor.apply();
    }

    public ChampionDto getChampion(int id) {
        ChampionDto championDto;
        String json;
        Gson gson;

        Log.d("Champion", "Id = " + id);
        json = sharedPreferences.getString("" + id, null);
        if (json == null)
            return null;
        gson = new Gson();
        championDto = gson.fromJson(json, ChampionDto.class);
        return championDto;
    }

    public void cleanBase() {
        editor.clear();
        editor.commit();
    }
}
