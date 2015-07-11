package com.kaki.leagueoflegendsexplorer.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.kaki.leagueoflegendsexplorer.api.riot.staticdata.models.items.ItemDto;
import com.kaki.leagueoflegendsexplorer.api.riot.staticdata.models.items.ItemListDto;
import com.kaki.leagueoflegendsexplorer.api.riot.staticdata.models.items.ItemListJson;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by kevinrodrigues on 10/07/15.
 */
public class CacheItem {

    private static final String NAME_SHARED = "com.kaki.leagueoflegendsexplorer.utils.CacheItem";
    private static final String SET_ID = "com.kaki.leagueoflegendsexplorer.utils.SET_ID";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public CacheItem(Context context) {
        sharedPreferences = context.getSharedPreferences(NAME_SHARED, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void storeItem(ItemListJson itemListJson) {
        Set<String> idSet = new HashSet<>();

        for (Map.Entry<String, JsonObject> entry : itemListJson.data.entrySet()) {
            int id = entry.getValue().get("id").getAsInt();
            idSet.add("" + id);
            editor.putString("" + id, "" + entry.getValue());
            Log.d("Items", "" + entry.getValue().get("name").getAsString());
        }
        editor.putStringSet(SET_ID, idSet);
        editor.apply();
    }

    public ItemDto getItem(int id) {
        ItemDto itemDto;
        String json;

        json = sharedPreferences.getString("" + id, null);
        if (json == null)
            return null;
        itemDto = new Gson().fromJson(json, ItemDto.class);
        return itemDto;
    }

    public void cleanBase() {
        editor.clear();
        editor.commit();
    }
}
