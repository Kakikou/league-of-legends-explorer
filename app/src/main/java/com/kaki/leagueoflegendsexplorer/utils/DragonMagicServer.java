package com.kaki.leagueoflegendsexplorer.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.kaki.leagueoflegendsexplorer.api.riot.staticdata.models.realms.RealmDto;

/**
 * Created by kevinrodrigues on 11/07/15.
 */
public class DragonMagicServer {

    private static final String NAME_SHARED = "com.kaki.leagueoflegendsexplorer.utils.DragonMagicServer";
    private static final String VERSION = "com.kaki.leagueoflegendsexplorer.utils.SET_ID";
    private static final String URL_CDN = "com.kaki.leagueoflegendsexplorer.utils.URL_CDN";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public DragonMagicServer(Context context) {
        sharedPreferences = context.getSharedPreferences(NAME_SHARED, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void updateUrl(RealmDto realmDto) {
        editor.putString(URL_CDN, realmDto.cdn);
        editor.putString(VERSION, realmDto.dd);
        editor.apply();
    }

    public String getItemImageUrl(String itemUrl) {
        return String.format(
                "%s/%s/img/item/%s",
                sharedPreferences.getString(URL_CDN, ""),
                sharedPreferences.getString(VERSION, ""),
                itemUrl);
    }

    public String getChampionSquareImageUrl(String championUrl) {
        return String.format(
                "%s/%s/img/champion/%s",
                sharedPreferences.getString(URL_CDN, ""),
                sharedPreferences.getString(VERSION, ""),
                championUrl);
    }
}
