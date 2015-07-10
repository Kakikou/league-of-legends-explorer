package com.kaki.leagueoflegendsexplorer.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by kevinrodrigues on 10/07/15.
 */
public class VersionBases {

    private static final String NAME_PREF = "com.kaki.leagueoflegendsexplorer.utils.VersionBases";
    private static final String VERSION_CHAMP = "com.kaki.leagueoflegendsexplorer.utils.VERSION_CHAMP";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public VersionBases(Context context) {
        sharedPreferences = context.getSharedPreferences(NAME_PREF, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public String getVersionChamp() {
        return sharedPreferences.getString(VERSION_CHAMP, "");
    }

    public void updateVersionChamp(String version) {
        editor.putString(VERSION_CHAMP, version);
        editor.apply();
    }

    public void cleanVersions() {
        editor.clear();
        editor.commit();
    }
}
