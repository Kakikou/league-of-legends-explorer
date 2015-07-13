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
                sharedPreferences.getString(URL_CDN, "http://ddragon.leagueoflegends.com/cdn"),
                sharedPreferences.getString(VERSION, "5.13.1"),
                itemUrl);
    }

    public String getChampionSquareImageUrl(String championUrl) {
        return String.format(
                "%s/%s/img/champion/%s",
                sharedPreferences.getString(URL_CDN, "http://ddragon.leagueoflegends.com/cdn"),
                sharedPreferences.getString(VERSION, "5.13.1"),
                championUrl);
    }

    public String getSkillImageUrl(String skillUrl) {
        return String.format(
                "%s/%s/img/spell/%s",
                sharedPreferences.getString(URL_CDN, "http://ddragon.leagueoflegends.com/cdn"),
                sharedPreferences.getString(VERSION, "5.13.1"),
                skillUrl);
    }

    public String getPassiveSkillImageUrl(String passiveUrl) {
        return String.format(
                "%s/%s/img/passive/%s",
                sharedPreferences.getString(URL_CDN, "http://ddragon.leagueoflegends.com/cdn"),
                sharedPreferences.getString(VERSION, "5.13.1"),
                passiveUrl);
    }

    public String getSplashSkinUrl(String championName, int nbSkin) {
        return String.format(
                "%s/img/champion/splash/%s_%d.jpg",
                sharedPreferences.getString(URL_CDN, "http://ddragon.leagueoflegends.com/cdn"),
                championName,
                nbSkin);
    }

    public String getProfileIconUrl(String nb) {
        return String.format(
                "%s/%s/img/profileicon/%s.png",
                sharedPreferences.getString(URL_CDN, "http://ddragon.leagueoflegends.com/cdn"),
                sharedPreferences.getString(VERSION, "5.13.1"),
                nb);
    }
// http://ddragon.leagueoflegends.com/cdn/5.2.1/img/profileicon/588.png
}
