package com.ajsmithsw.switchout;

/**
 * Created by Alexander Smith on 23/05/15.
 */
public class Progress {



    int levelId, bestResult, bestPossible, stars;
    boolean levelComplete;

    public Progress() {
        bestResult = 0;
        bestPossible = 0;
        stars = 0;
    }

    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }

    public int getLevelId() {
        return levelId;
    }

    public void setBestResult(int i) {
        bestResult = i;
    }

    public int getBestResult() {
        return bestResult;
    }

    public void setBestPossible(int i) {
        bestPossible = i;
    }

    public int getBestPossible() {
        return bestPossible;
    }

    public void setStars(int i) {
        stars = i;
    }

    public int getStars() {
        return stars;
    }


}
