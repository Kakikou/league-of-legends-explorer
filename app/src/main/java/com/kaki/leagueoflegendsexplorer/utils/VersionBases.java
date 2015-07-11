package com.kaki.leagueoflegendsexplorer.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by kevinrodrigues on 10/07/15.
 */
public class VersionBases {

    private static final String NAME_PREF = "com.kaki.leagueoflegendsexplorer.utils.VersionBases";
    private static final String VERSION_CHAMP = "com.kaki.leagueoflegendsexplorer.utils.VERSION_CHAMP";
    private static final String VERSION_ITEMS = "com.kaki.leagueoflegendsexplorer.utils.VERSION_ITEMS";
    private static final String VERSION_DRAGON_MAGIC_SERVER = "com.kaki.leagueoflegendsexplorer.utils.DRAGON_MAGIC_SERVER";


    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public VersionBases(Context context) {
        sharedPreferences = context.getSharedPreferences(NAME_PREF, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public String getVersionChamp() {
        return sharedPreferences.getString(VERSION_CHAMP, "");
    }

    public String getVersionItems() {
        return sharedPreferences.getString(VERSION_ITEMS, "");
    }

    public String getVersionDragonMagicServer() {
        return sharedPreferences.getString(VERSION_DRAGON_MAGIC_SERVER, "");
    }

    public void updateVersionChamp(String version) {
        editor.putString(VERSION_CHAMP, version);
        editor.apply();
    }

    public void updateVersionItem(String version) {
        editor.putString(VERSION_ITEMS, version);
        editor.apply();
    }

    public void updateVersionDragonMagicServer(String version) {
        editor.putString(VERSION_DRAGON_MAGIC_SERVER, version);
        editor.apply();
    }

    public void cleanVersions() {
        editor.clear();
        editor.commit();
    }
}
