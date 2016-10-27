package com.ajsmithsw.switchout;

/**
 * Created by Alex on 18/05/15.
 */
public class Light {


    private int iD;
    private boolean switchedOn;
    private boolean hintActive;

    //Constructor
    public Light(int newId) {
        iD = newId;
        //hintActive = false;
    }

    public int getId() {
        return iD;
    }

    public boolean getBoolean() {
        return switchedOn;
    }
    public void setBoolean(boolean newBoolean) {
        switchedOn = newBoolean;
    }

    public boolean getHintActive() { return hintActive; }
    public void setHintActive(boolean newBoolean) { hintActive = newBoolean; }

}
