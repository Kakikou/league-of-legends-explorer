package com.kaki.leagueoflegendsexplorer.api.riot.champion_v1_2.models;

/**
 * Created by kevinrodrigues on 04/07/15.
 */
public class ChampionDto {

    // This object contains a collection of champion information.

    /**
     * Champion ID. For static information correlating to champion IDs, please refer to the LoL Static Data API.
     */
    public int id;
    /**
     * Indicates if the champion is active.
     */
    public boolean active;
    /**
     * Bot enabled flag (for custom games).
     */
    public boolean botEnabled;
    /**
     * Bot Match Made enabled flag (for Co-op vs. AI games).
     */
    public boolean botMmEnabled;
    /**
     * Indicates if the champion is free to play. Free to play champions are rotated periodically.
     */
    public boolean freeToPlay;
    /**
     * Ranked play enabled flag.
     */
    public boolean rankedPlayEnabled;
}
