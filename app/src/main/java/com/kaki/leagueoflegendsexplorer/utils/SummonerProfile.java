package com.kaki.leagueoflegendsexplorer.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by kevinrodrigues on 12/07/15.
 */
public class SummonerProfile {

    private static final String NAME = "SummonerProfile";
    private static final String SUMMONER_NAME = "Summoner_Name";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public SummonerProfile(Context context) {
        sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public String getSummonerName() {
        return sharedPreferences.getString(SUMMONER_NAME, "");
    }
}
