package com.kaki.leagueoflegendsexplorer.enums;

import android.support.annotation.DrawableRes;

import com.kaki.leagueoflegendsexplorer.R;

/**
 * Created by kevinrodrigues on 13/07/15.
 */
public enum Ranking {

    UNRANKED(R.drawable.provisional, "UNRANKED", ""),
    BRONZE_I(R.drawable.bronze_i, "BRONZE", "I"),
    BRONZE_II(R.drawable.bronze_ii, "BRONZE", "II"),
    BRONZE_III(R.drawable.bronze_iii, "BRONZE", "III"),
    BRONZE_IV(R.drawable.bronze_iv, "BRONZE", "IV"),
    BRONZE_V(R.drawable.bronze_v, "BRONZE", "V"),
    SILVER_I(R.drawable.silver_i, "SILVER", "I"),
    SILVER_II(R.drawable.silver_ii, "SILVER", "II"),
    SILVER_III(R.drawable.silver_iii, "SILVER", "III"),
    SILVER_IV(R.drawable.silver_iv, "SILVER", "IV"),
    SILVER_V(R.drawable.silver_v, "SILVER", "V"),
    GOLD_I(R.drawable.gold_i, "GOLD", "I"),
    GOLD_II(R.drawable.gold_ii, "GOLD", "II"),
    GOLD_III(R.drawable.gold_iii, "GOLD", "III"),
    GOLD_IV(R.drawable.gold_iv, "GOLD", "IV"),
    GOLD_V(R.drawable.gold_v, "GOLD", "V"),
    PLATINUM_I(R.drawable.platinum_i, "PLATINUM", "I"),
    PLATINUM_II(R.drawable.platinum_ii, "PLATINUM", "II"),
    PLATINUM_III(R.drawable.platinum_iii, "PLATINUM", "III"),
    PLATINUM_IV(R.drawable.platinum_iv, "PLATINUM", "IV"),
    PLATINUM_V(R.drawable.platinum_v, "PLATINUM", "V"),
    DIAMOND_I(R.drawable.diamond_i, "DIAMOND", "I"),
    DIAMOND_II(R.drawable.diamond_ii, "DIAMOND", "II"),
    DIAMOND_III(R.drawable.diamond_iii, "DIAMOND", "III"),
    DIAMOND_IV(R.drawable.diamond_iv, "DIAMOND", "IV"),
    DIAMOND_V(R.drawable.diamond_v, "DIAMOND", "V"),
    MASTER(R.drawable.master, "MASTER", ""),
    CHALLENGER(R.drawable.challenger, "CHALLENGER", "I");

    @DrawableRes
    public final int img;
    public final String tier;
    public final String division;

    Ranking(@DrawableRes int img, String tier, String division) {
        this.img = img;
        this.tier = tier;
        this.division = division;
    }

    public static Ranking getRanking(String tier, String division) {
        for (Ranking ranking : values()) {
            if (ranking.tier.equals(tier)) {
                if (ranking.division.equals(division))
                    return ranking;
            }
        }
        return UNRANKED;
    }
}
