package com.ajsmithsw.switchout;

/**
 * Created by Alexander Smith on 09/07/15.
 */
public class SaveGameState {

    public static int savedMoves;
    public static int level;
    public static int[] savedLightsOn;

    public static int getLevel() {
        return level;
    }

    public static void setLevel(int level) {
        SaveGameState.level = level;
    }

    public static int getSavedMoves() {
        return savedMoves;
    }

    public static void setSavedMoves(int savedMoves) {

        SaveGameState.savedMoves = savedMoves;
    }

    public static int[] getSavedLightsOn() {
        return savedLightsOn;
    }

    public static void setSavedLightsOn(int[] savedLightsOn) {
        SaveGameState.savedLightsOn = savedLightsOn;
    }





}
