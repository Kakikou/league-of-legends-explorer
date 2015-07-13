package com.kaki.leagueoflegendsexplorer.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.kaki.leagueoflegendsexplorer.api.riot.league.models.LeagueDto;
import com.kaki.leagueoflegendsexplorer.api.riot.league.models.LeagueEntryDto;
import com.kaki.leagueoflegendsexplorer.api.riot.summoner.models.SummonerDto;

/**
 * Created by kevinrodrigues on 12/07/15.
 */
public class SummonerProfile {

    private static final String NAME = "SummonerProfile";
    private static final String SUMMONER_ID = "Summoner_Id";
    private static final String SUMMONER_NAME = "Summoner_Name";
    private static final String SUMMONER_ICON = "Summoner_Icon";
    private static final String SUMMONER_LEVEL = "Summoner_Level";
    private static final String SUMMONER_REVISION_DATE = "Summoner_Revision_Date";

    private static final String SUMMONER_TIER = "Summoner_Tier";
    private static final String SUMMONER_DIVISION = "Summoner_Division";
    private static final String SUMMONER_LEAGUE_POINTS = "Summoner_League_Points";
    private static final String SUMMONER_WINS = "Summoner_Wins";
    private static final String SUMMONER_LOSSES = "Summoner_Losses";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public SummonerProfile(Context context) {
        sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void clean() {
        editor.putString(SUMMONER_ID, "");
        editor.putString(SUMMONER_NAME, "");
        editor.putString(SUMMONER_ICON, "");
        editor.putString(SUMMONER_LEVEL, "");
        editor.putString(SUMMONER_REVISION_DATE, "");
        editor.putString(SUMMONER_TIER, "");
        editor.putString(SUMMONER_DIVISION, "");
        editor.putInt(SUMMONER_LEAGUE_POINTS, 0);
        editor.putInt(SUMMONER_WINS, 0);
        editor.putInt(SUMMONER_LOSSES, 0);
        editor.apply();
    }

    public void storeSummoner(SummonerDto summonerDto) {
        editor.putString(SUMMONER_ID, Long.toString(summonerDto.id));
        editor.putString(SUMMONER_NAME, summonerDto.name);
        editor.putString(SUMMONER_ICON, Long.toString(summonerDto.profileIconId));
        editor.putString(SUMMONER_LEVEL, Long.toString(summonerDto.summonerLevel));
        editor.putString(SUMMONER_REVISION_DATE, Long.toString(summonerDto.revisionDate));
        editor.apply();
    }

    public void storeSummonerRankedStats(LeagueDto leagueDto) {
        editor.putString(SUMMONER_TIER, leagueDto.tier);
        editor.putString(SUMMONER_DIVISION, leagueDto.entries.get(0).division);
        editor.putInt(SUMMONER_LEAGUE_POINTS, leagueDto.entries.get(0).leaguePoints);
        editor.putInt(SUMMONER_WINS, leagueDto.entries.get(0).wins);
        editor.putInt(SUMMONER_LOSSES, leagueDto.entries.get(0).losses);
        editor.apply();
    }

    public String getSummonerId() { return sharedPreferences.getString(SUMMONER_ID, ""); }

    public String getSummonerName() { return sharedPreferences.getString(SUMMONER_NAME, ""); }

    public String getSummonerIcon() { return sharedPreferences.getString(SUMMONER_ICON, ""); }

    public String getSummonerLevel() { return sharedPreferences.getString(SUMMONER_LEVEL, ""); }

    public String getSummonerTier() { return sharedPreferences.getString(SUMMONER_TIER, ""); }

    public String getSummonerDivision() { return sharedPreferences.getString(SUMMONER_DIVISION, ""); }

    public int getSummonerLeaguePoints() { return sharedPreferences.getInt(SUMMONER_LEAGUE_POINTS, 0); }

    public int getSummonerWin() { return sharedPreferences.getInt(SUMMONER_WINS, 0); }

    public int getSummonerLose() { return sharedPreferences.getInt(SUMMONER_LOSSES, 0); }

}
