package com.ajsmithsw.switchout;
import android.util.Log;

import com.ajsmithsw.switchout.GameBoard;

import java.util.Random;

/**
 * Created by Alex on 18/05/15.
 */
public class Levels {

    public static void setLevel(int level) {

        switch (level) {

            case 1:
                //par 1
                GameBoard.lights.get(7).setBoolean(true);
                GameBoard.lights.get(11).setBoolean(true);
                GameBoard.lights.get(12).setBoolean(true);
                GameBoard.lights.get(13).setBoolean(true);
                GameBoard.lights.get(17).setBoolean(true);
                break;

            case 2:
                //par 2
                GameBoard.lights.get(2).setBoolean(true);
                GameBoard.lights.get(6).setBoolean(true);
                GameBoard.lights.get(7).setBoolean(true);
                GameBoard.lights.get(8).setBoolean(true);
                GameBoard.lights.get(16).setBoolean(true);
                GameBoard.lights.get(17).setBoolean(true);
                GameBoard.lights.get(18).setBoolean(true);
                GameBoard.lights.get(22).setBoolean(true);
                break;

            case 3:
                //par 2
                GameBoard.lights.get(0).setBoolean(true);
                GameBoard.lights.get(1).setBoolean(true);
                GameBoard.lights.get(5).setBoolean(true);
                GameBoard.lights.get(19).setBoolean(true);
                GameBoard.lights.get(23).setBoolean(true);
                GameBoard.lights.get(24).setBoolean(true);
                break;

            case 4:
                //Par 4
                GameBoard.lights.get(0).setBoolean(true);
                GameBoard.lights.get(1).setBoolean(true);
                GameBoard.lights.get(4).setBoolean(true);
                GameBoard.lights.get(5).setBoolean(true);
                GameBoard.lights.get(8).setBoolean(true);
                GameBoard.lights.get(9).setBoolean(true);
                GameBoard.lights.get(14).setBoolean(true);
                GameBoard.lights.get(15).setBoolean(true);
                GameBoard.lights.get(17).setBoolean(true);
                GameBoard.lights.get(20).setBoolean(true);
                GameBoard.lights.get(22).setBoolean(true);
                GameBoard.lights.get(23).setBoolean(true);
                break;

            case 5:
                //Par 5
                GameBoard.lights.get(0).setBoolean(true);
                GameBoard.lights.get(1).setBoolean(true);
                GameBoard.lights.get(3).setBoolean(true);
                GameBoard.lights.get(4).setBoolean(true);
                GameBoard.lights.get(5).setBoolean(true);
                GameBoard.lights.get(7).setBoolean(true);
                GameBoard.lights.get(9).setBoolean(true);
                GameBoard.lights.get(11).setBoolean(true);
                GameBoard.lights.get(12).setBoolean(true);
                GameBoard.lights.get(15).setBoolean(true);
                GameBoard.lights.get(18).setBoolean(true);
                GameBoard.lights.get(19).setBoolean(true);
                GameBoard.lights.get(20).setBoolean(true);
                GameBoard.lights.get(21).setBoolean(true);
                GameBoard.lights.get(23).setBoolean(true);
                break;

            case 6:
                //Par 3 TODO move this later, it's harder than 7 & 8
                GameBoard.lights.get(15).setBoolean(true);
                GameBoard.lights.get(17).setBoolean(true);
                GameBoard.lights.get(19).setBoolean(true);
                GameBoard.lights.get(20).setBoolean(true);
                GameBoard.lights.get(22).setBoolean(true);
                GameBoard.lights.get(24).setBoolean(true);
                break;

            case 7:
                //Par 5
                GameBoard.lights.get(4).setBoolean(true);
                GameBoard.lights.get(8).setBoolean(true);
                GameBoard.lights.get(12).setBoolean(true);
                GameBoard.lights.get(16).setBoolean(true);
                GameBoard.lights.get(20).setBoolean(true);
                break;

            case 8:
                //Par 4
                GameBoard.lights.get(1).setBoolean(true);
                GameBoard.lights.get(3).setBoolean(true);
                GameBoard.lights.get(5).setBoolean(true);
                GameBoard.lights.get(6).setBoolean(true);
                GameBoard.lights.get(8).setBoolean(true);
                GameBoard.lights.get(9).setBoolean(true);
                GameBoard.lights.get(15).setBoolean(true);
                GameBoard.lights.get(16).setBoolean(true);
                GameBoard.lights.get(18).setBoolean(true);
                GameBoard.lights.get(19).setBoolean(true);
                GameBoard.lights.get(21).setBoolean(true);
                GameBoard.lights.get(23).setBoolean(true);
                break;

            case 9:
                //Par 5
                GameBoard.lights.get(5).setBoolean(true);
                GameBoard.lights.get(7).setBoolean(true);
                GameBoard.lights.get(10).setBoolean(true);
                GameBoard.lights.get(12).setBoolean(true);
                GameBoard.lights.get(13).setBoolean(true);
                GameBoard.lights.get(14).setBoolean(true);
                GameBoard.lights.get(18).setBoolean(true);
                GameBoard.lights.get(19).setBoolean(true);
                GameBoard.lights.get(20).setBoolean(true);
                GameBoard.lights.get(22).setBoolean(true);
                GameBoard.lights.get(23).setBoolean(true);
                GameBoard.lights.get(24).setBoolean(true);
                break;

            case 10:
                //Par 9
                GameBoard.lights.get(0).setBoolean(true);
                GameBoard.lights.get(2).setBoolean(true);
                GameBoard.lights.get(4).setBoolean(true);
                GameBoard.lights.get(10).setBoolean(true);
                GameBoard.lights.get(12).setBoolean(true);
                GameBoard.lights.get(14).setBoolean(true);
                GameBoard.lights.get(20).setBoolean(true);
                GameBoard.lights.get(22).setBoolean(true);
                GameBoard.lights.get(24).setBoolean(true);
                break;

            case 11:
                //Par 6
                GameBoard.lights.get(10).setBoolean(true);
                GameBoard.lights.get(12).setBoolean(true);
                GameBoard.lights.get(14).setBoolean(true);
                break;

            case 12:
                //Par 6
                GameBoard.lights.get(1).setBoolean(true);
                GameBoard.lights.get(3).setBoolean(true);
                GameBoard.lights.get(5).setBoolean(true);
                GameBoard.lights.get(6).setBoolean(true);
                GameBoard.lights.get(8).setBoolean(true);
                GameBoard.lights.get(9).setBoolean(true);
                GameBoard.lights.get(10).setBoolean(true);
                GameBoard.lights.get(11).setBoolean(true);
                GameBoard.lights.get(13).setBoolean(true);
                GameBoard.lights.get(14).setBoolean(true);
                GameBoard.lights.get(15).setBoolean(true);
                GameBoard.lights.get(16).setBoolean(true);
                GameBoard.lights.get(18).setBoolean(true);
                GameBoard.lights.get(19).setBoolean(true);
                GameBoard.lights.get(21).setBoolean(true);
                GameBoard.lights.get(23).setBoolean(true);
                break;

            case 13:
                //Par 6
                GameBoard.lights.get(0).setBoolean(true);
                GameBoard.lights.get(4).setBoolean(true);
                GameBoard.lights.get(20).setBoolean(true);
                GameBoard.lights.get(24).setBoolean(true);
                break;

            case 14:
                //Par 12
                GameBoard.lights.get(0).setBoolean(true);
                GameBoard.lights.get(1).setBoolean(true);
                GameBoard.lights.get(3).setBoolean(true);
                GameBoard.lights.get(4).setBoolean(true);
                GameBoard.lights.get(5).setBoolean(true);
                GameBoard.lights.get(6).setBoolean(true);
                GameBoard.lights.get(8).setBoolean(true);
                GameBoard.lights.get(9).setBoolean(true);
                GameBoard.lights.get(15).setBoolean(true);
                GameBoard.lights.get(16).setBoolean(true);
                GameBoard.lights.get(18).setBoolean(true);
                GameBoard.lights.get(19).setBoolean(true);
                GameBoard.lights.get(20).setBoolean(true);
                GameBoard.lights.get(21).setBoolean(true);
                GameBoard.lights.get(23).setBoolean(true);
                GameBoard.lights.get(24).setBoolean(true);
                break;

            case 15:
                //Par 6
                GameBoard.lights.get(0).setBoolean(true);
                GameBoard.lights.get(1).setBoolean(true);
                GameBoard.lights.get(2).setBoolean(true);
                GameBoard.lights.get(4).setBoolean(true);
                GameBoard.lights.get(5).setBoolean(true);
                GameBoard.lights.get(6).setBoolean(true);
                GameBoard.lights.get(7).setBoolean(true);
                GameBoard.lights.get(9).setBoolean(true);
                GameBoard.lights.get(10).setBoolean(true);
                GameBoard.lights.get(12).setBoolean(true);
                GameBoard.lights.get(14).setBoolean(true);
                GameBoard.lights.get(15).setBoolean(true);
                GameBoard.lights.get(16).setBoolean(true);
                GameBoard.lights.get(17).setBoolean(true);
                GameBoard.lights.get(19).setBoolean(true);
                GameBoard.lights.get(20).setBoolean(true);
                GameBoard.lights.get(21).setBoolean(true);
                GameBoard.lights.get(22).setBoolean(true);
                GameBoard.lights.get(24).setBoolean(true);
                break;

            case 16:
                // par 11
                GameBoard.lights.get(12).setBoolean(true);
                break;

            case 17:
                //par 10
                GameBoard.lights.get(2).setBoolean(true);
                GameBoard.lights.get(7).setBoolean(true);
                GameBoard.lights.get(17).setBoolean(true);
                GameBoard.lights.get(22).setBoolean(true);
                break;

            case 18:
                //par 10
                GameBoard.lights.get(6).setBoolean(true);
                GameBoard.lights.get(10).setBoolean(true);
                GameBoard.lights.get(11).setBoolean(true);
                GameBoard.lights.get(12).setBoolean(true);
                break;

            case 19:
                //par 15
                GameBoard.lights.get(0).setBoolean(true);
                GameBoard.lights.get(4).setBoolean(true);
                GameBoard.lights.get(6).setBoolean(true);
                GameBoard.lights.get(8).setBoolean(true);
                GameBoard.lights.get(12).setBoolean(true);
                GameBoard.lights.get(16).setBoolean(true);
                GameBoard.lights.get(18).setBoolean(true);
                GameBoard.lights.get(20).setBoolean(true);
                GameBoard.lights.get(24).setBoolean(true);
                break;

            case 20:
                //par 8
                GameBoard.lights.get(1).setBoolean(true);
                GameBoard.lights.get(3).setBoolean(true);
                GameBoard.lights.get(7).setBoolean(true);
                GameBoard.lights.get(12).setBoolean(true);
                GameBoard.lights.get(15).setBoolean(true);
                GameBoard.lights.get(19).setBoolean(true);
                GameBoard.lights.get(21).setBoolean(true);
                GameBoard.lights.get(22).setBoolean(true);
                GameBoard.lights.get(23).setBoolean(true);
                break;

            case 21:
                //par 15
                GameBoard.lights.get(1).setBoolean(true);
                GameBoard.lights.get(3).setBoolean(true);
                GameBoard.lights.get(5).setBoolean(true);
                GameBoard.lights.get(6).setBoolean(true);
                GameBoard.lights.get(8).setBoolean(true);
                GameBoard.lights.get(9).setBoolean(true);
                GameBoard.lights.get(12).setBoolean(true);
                GameBoard.lights.get(15).setBoolean(true);
                GameBoard.lights.get(16).setBoolean(true);
                GameBoard.lights.get(18).setBoolean(true);
                GameBoard.lights.get(19).setBoolean(true);
                GameBoard.lights.get(21).setBoolean(true);
                GameBoard.lights.get(23).setBoolean(true);
                break;

            case 22:
                //par 15
                GameBoard.lights.get(10).setBoolean(true);
                GameBoard.lights.get(11).setBoolean(true);
                GameBoard.lights.get(12).setBoolean(true);
                GameBoard.lights.get(13).setBoolean(true);
                GameBoard.lights.get(14).setBoolean(true);
                break;

            case 23:
                //par 11
                GameBoard.lights.get(2).setBoolean(true);
                GameBoard.lights.get(7).setBoolean(true);
                GameBoard.lights.get(10).setBoolean(true);
                GameBoard.lights.get(11).setBoolean(true);
                GameBoard.lights.get(12).setBoolean(true);
                GameBoard.lights.get(13).setBoolean(true);
                GameBoard.lights.get(14).setBoolean(true);
                GameBoard.lights.get(17).setBoolean(true);
                GameBoard.lights.get(22).setBoolean(true);
                break;

            case 24:
                //par 14
                GameBoard.lights.get(6).setBoolean(true);
                GameBoard.lights.get(7).setBoolean(true);
                GameBoard.lights.get(8).setBoolean(true);
                GameBoard.lights.get(11).setBoolean(true);
                GameBoard.lights.get(13).setBoolean(true);
                GameBoard.lights.get(16).setBoolean(true);
                GameBoard.lights.get(17).setBoolean(true);
                GameBoard.lights.get(18).setBoolean(true);
                break;

            case 25:
                //par 13
                GameBoard.lights.get(0).setBoolean(true);
                GameBoard.lights.get(2).setBoolean(true);
                GameBoard.lights.get(4).setBoolean(true);
                GameBoard.lights.get(6).setBoolean(true);
                GameBoard.lights.get(8).setBoolean(true);
                GameBoard.lights.get(10).setBoolean(true);
                GameBoard.lights.get(12).setBoolean(true);
                GameBoard.lights.get(14).setBoolean(true);
                GameBoard.lights.get(16).setBoolean(true);
                GameBoard.lights.get(18).setBoolean(true);
                GameBoard.lights.get(20).setBoolean(true);
                GameBoard.lights.get(22).setBoolean(true);
                GameBoard.lights.get(24).setBoolean(true);
                break;

            case 26:
                //par 9
                GameBoard.lights.get(1).setBoolean(true);
                GameBoard.lights.get(2).setBoolean(true);
                GameBoard.lights.get(3).setBoolean(true);
                GameBoard.lights.get(5).setBoolean(true);
                GameBoard.lights.get(6).setBoolean(true);
                GameBoard.lights.get(8).setBoolean(true);
                GameBoard.lights.get(9).setBoolean(true);
                GameBoard.lights.get(10).setBoolean(true);
                GameBoard.lights.get(12).setBoolean(true);
                GameBoard.lights.get(14).setBoolean(true);
                GameBoard.lights.get(15).setBoolean(true);
                GameBoard.lights.get(16).setBoolean(true);
                GameBoard.lights.get(18).setBoolean(true);
                GameBoard.lights.get(19).setBoolean(true);
                GameBoard.lights.get(21).setBoolean(true);
                GameBoard.lights.get(22).setBoolean(true);
                GameBoard.lights.get(23).setBoolean(true);
                break;

            case 27:
                //par 15
                GameBoard.lights.get(0).setBoolean(true);
                GameBoard.lights.get(1).setBoolean(true);
                GameBoard.lights.get(2).setBoolean(true);
                GameBoard.lights.get(3).setBoolean(true);
                GameBoard.lights.get(4).setBoolean(true);
                GameBoard.lights.get(5).setBoolean(true);
                GameBoard.lights.get(6).setBoolean(true);
                GameBoard.lights.get(7).setBoolean(true);
                GameBoard.lights.get(8).setBoolean(true);
                GameBoard.lights.get(9).setBoolean(true);
                GameBoard.lights.get(10).setBoolean(true);
                GameBoard.lights.get(11).setBoolean(true);
                GameBoard.lights.get(12).setBoolean(true);
                GameBoard.lights.get(13).setBoolean(true);
                GameBoard.lights.get(14).setBoolean(true);
                GameBoard.lights.get(15).setBoolean(true);
                GameBoard.lights.get(16).setBoolean(true);
                GameBoard.lights.get(17).setBoolean(true);
                GameBoard.lights.get(18).setBoolean(true);
                GameBoard.lights.get(19).setBoolean(true);
                GameBoard.lights.get(20).setBoolean(true);
                GameBoard.lights.get(21).setBoolean(true);
                GameBoard.lights.get(22).setBoolean(true);
                GameBoard.lights.get(23).setBoolean(true);
                GameBoard.lights.get(24).setBoolean(true);
                break;

            case 28:
                //par 14
                GameBoard.lights.get(2).setBoolean(true);
                GameBoard.lights.get(10).setBoolean(true);
                GameBoard.lights.get(11).setBoolean(true);
                GameBoard.lights.get(12).setBoolean(true);
                GameBoard.lights.get(13).setBoolean(true);
                GameBoard.lights.get(14).setBoolean(true);
                GameBoard.lights.get(22).setBoolean(true);
                break;

            case 29:
                //par 15
                GameBoard.lights.get(1).setBoolean(true);
                GameBoard.lights.get(3).setBoolean(true);
                GameBoard.lights.get(15).setBoolean(true);
                GameBoard.lights.get(16).setBoolean(true);
                GameBoard.lights.get(18).setBoolean(true);
                GameBoard.lights.get(19).setBoolean(true);
                GameBoard.lights.get(20).setBoolean(true);
                GameBoard.lights.get(21).setBoolean(true);
                GameBoard.lights.get(23).setBoolean(true);
                GameBoard.lights.get(24).setBoolean(true);
                break;

            case 30:
                //par 7
                GameBoard.lights.get(0).setBoolean(true);
                GameBoard.lights.get(1).setBoolean(true);
                GameBoard.lights.get(2).setBoolean(true);
                GameBoard.lights.get(4).setBoolean(true);
                GameBoard.lights.get(5).setBoolean(true);
                GameBoard.lights.get(6).setBoolean(true);
                GameBoard.lights.get(8).setBoolean(true);
                GameBoard.lights.get(9).setBoolean(true);
                GameBoard.lights.get(11).setBoolean(true);
                GameBoard.lights.get(13).setBoolean(true);
                GameBoard.lights.get(14).setBoolean(true);
                GameBoard.lights.get(15).setBoolean(true);
                GameBoard.lights.get(16).setBoolean(true);
                GameBoard.lights.get(18).setBoolean(true);
                GameBoard.lights.get(19).setBoolean(true);
                GameBoard.lights.get(22).setBoolean(true);
                break;

            case 31:
                //par 8
                GameBoard.lights.get(3).setBoolean(true);
                GameBoard.lights.get(23).setBoolean(true);
                break;

            case 32:
                //par 9
                GameBoard.lights.get(0).setBoolean(true);
                GameBoard.lights.get(4).setBoolean(true);
                GameBoard.lights.get(6).setBoolean(true);
                GameBoard.lights.get(7).setBoolean(true);
                GameBoard.lights.get(8).setBoolean(true);
                GameBoard.lights.get(11).setBoolean(true);
                GameBoard.lights.get(12).setBoolean(true);
                GameBoard.lights.get(13).setBoolean(true);
                GameBoard.lights.get(16).setBoolean(true);
                GameBoard.lights.get(17).setBoolean(true);
                GameBoard.lights.get(18).setBoolean(true);
                GameBoard.lights.get(20).setBoolean(true);
                GameBoard.lights.get(24).setBoolean(true);
                break;

            case 33:
                //Par 11
                GameBoard.lights.get(2).setBoolean(true);
                GameBoard.lights.get(5).setBoolean(true);
                GameBoard.lights.get(9).setBoolean(true);
                GameBoard.lights.get(15).setBoolean(true);
                GameBoard.lights.get(19).setBoolean(true);
                GameBoard.lights.get(20).setBoolean(true);
                GameBoard.lights.get(21).setBoolean(true);
                GameBoard.lights.get(22).setBoolean(true);
                GameBoard.lights.get(23).setBoolean(true);
                GameBoard.lights.get(24).setBoolean(true);
                break;

            case 34:
                //par 10
                GameBoard.lights.get(6).setBoolean(true);
                GameBoard.lights.get(8).setBoolean(true);
                GameBoard.lights.get(10).setBoolean(true);
                GameBoard.lights.get(12).setBoolean(true);
                GameBoard.lights.get(14).setBoolean(true);
                break;

            case 35:
                //par 9
                GameBoard.lights.get(8).setBoolean(true);
                GameBoard.lights.get(9).setBoolean(true);
                GameBoard.lights.get(12).setBoolean(true);
                GameBoard.lights.get(13).setBoolean(true);
                GameBoard.lights.get(16).setBoolean(true);
                GameBoard.lights.get(17).setBoolean(true);
                GameBoard.lights.get(21).setBoolean(true);
                break;

            case 36:
                //par 11
                GameBoard.lights.get(0).setBoolean(true);
                GameBoard.lights.get(1).setBoolean(true);
                GameBoard.lights.get(2).setBoolean(true);
                GameBoard.lights.get(5).setBoolean(true);
                GameBoard.lights.get(6).setBoolean(true);
                GameBoard.lights.get(8).setBoolean(true);
                GameBoard.lights.get(9).setBoolean(true);
                GameBoard.lights.get(10).setBoolean(true);
                GameBoard.lights.get(12).setBoolean(true);
                GameBoard.lights.get(13).setBoolean(true);
                GameBoard.lights.get(16).setBoolean(true);
                GameBoard.lights.get(17).setBoolean(true);
                GameBoard.lights.get(19).setBoolean(true);
                GameBoard.lights.get(20).setBoolean(true);
                GameBoard.lights.get(22).setBoolean(true);
                GameBoard.lights.get(23).setBoolean(true);
                break;

            case 37:
                //par 12
                GameBoard.lights.get(6).setBoolean(true);
                GameBoard.lights.get(8).setBoolean(true);
                GameBoard.lights.get(16).setBoolean(true);
                GameBoard.lights.get(18).setBoolean(true);
                break;

            case 38:
                //par 12
                GameBoard.lights.get(0).setBoolean(true);
                GameBoard.lights.get(2).setBoolean(true);
                GameBoard.lights.get(4).setBoolean(true);
                GameBoard.lights.get(6).setBoolean(true);
                GameBoard.lights.get(7).setBoolean(true);
                GameBoard.lights.get(8).setBoolean(true);
                GameBoard.lights.get(10).setBoolean(true);
                GameBoard.lights.get(11).setBoolean(true);
                GameBoard.lights.get(13).setBoolean(true);
                GameBoard.lights.get(14).setBoolean(true);
                GameBoard.lights.get(16).setBoolean(true);
                GameBoard.lights.get(17).setBoolean(true);
                GameBoard.lights.get(18).setBoolean(true);
                GameBoard.lights.get(20).setBoolean(true);
                GameBoard.lights.get(22).setBoolean(true);
                GameBoard.lights.get(24).setBoolean(true);
                break;

            case 39:
                //par 11
                GameBoard.lights.get(0).setBoolean(true);
                GameBoard.lights.get(1).setBoolean(true);
                GameBoard.lights.get(2).setBoolean(true);
                GameBoard.lights.get(3).setBoolean(true);
                GameBoard.lights.get(4).setBoolean(true);
                GameBoard.lights.get(5).setBoolean(true);
                GameBoard.lights.get(9).setBoolean(true);
                GameBoard.lights.get(11).setBoolean(true);
                GameBoard.lights.get(12).setBoolean(true);
                GameBoard.lights.get(13).setBoolean(true);
                GameBoard.lights.get(15).setBoolean(true);
                GameBoard.lights.get(19).setBoolean(true);
                GameBoard.lights.get(20).setBoolean(true);
                GameBoard.lights.get(21).setBoolean(true);
                GameBoard.lights.get(22).setBoolean(true);
                GameBoard.lights.get(23).setBoolean(true);
                GameBoard.lights.get(24).setBoolean(true);
                break;

            case 40:
                //par 9
                GameBoard.lights.get(20).setBoolean(true);
                GameBoard.lights.get(22).setBoolean(true);
                GameBoard.lights.get(23).setBoolean(true);
                break;

            case 41:
                //par 11
                GameBoard.lights.get(2).setBoolean(true);
                GameBoard.lights.get(3).setBoolean(true);
                GameBoard.lights.get(4).setBoolean(true);
                GameBoard.lights.get(5).setBoolean(true);
                GameBoard.lights.get(7).setBoolean(true);
                GameBoard.lights.get(17).setBoolean(true);
                GameBoard.lights.get(19).setBoolean(true);
                GameBoard.lights.get(20).setBoolean(true);
                GameBoard.lights.get(24).setBoolean(true);
                break;

            case 42:
                //par 9
                GameBoard.lights.get(0).setBoolean(true);
                GameBoard.lights.get(1).setBoolean(true);
                GameBoard.lights.get(4).setBoolean(true);
                GameBoard.lights.get(8).setBoolean(true);
                GameBoard.lights.get(9).setBoolean(true);
                GameBoard.lights.get(13).setBoolean(true);
                GameBoard.lights.get(14).setBoolean(true);
                GameBoard.lights.get(18).setBoolean(true);
                GameBoard.lights.get(19).setBoolean(true);
                GameBoard.lights.get(20).setBoolean(true);
                GameBoard.lights.get(21).setBoolean(true);
                GameBoard.lights.get(24).setBoolean(true);
                break;

            case 43:
                //par 11
                GameBoard.lights.get(0).setBoolean(true);
                GameBoard.lights.get(1).setBoolean(true);
                GameBoard.lights.get(2).setBoolean(true);
                GameBoard.lights.get(3).setBoolean(true);
                GameBoard.lights.get(4).setBoolean(true);
                GameBoard.lights.get(7).setBoolean(true);
                GameBoard.lights.get(10).setBoolean(true);
                GameBoard.lights.get(11).setBoolean(true);
                GameBoard.lights.get(12).setBoolean(true);
                GameBoard.lights.get(13).setBoolean(true);
                GameBoard.lights.get(14).setBoolean(true);
                GameBoard.lights.get(16).setBoolean(true);
                GameBoard.lights.get(18).setBoolean(true);
                GameBoard.lights.get(20).setBoolean(true);
                GameBoard.lights.get(21).setBoolean(true);
                GameBoard.lights.get(23).setBoolean(true);
                GameBoard.lights.get(24).setBoolean(true);
                break;

            case 44:
                //par 10
                GameBoard.lights.get(0).setBoolean(true);
                GameBoard.lights.get(1).setBoolean(true);
                GameBoard.lights.get(3).setBoolean(true);
                GameBoard.lights.get(4).setBoolean(true);
                break;

            case 45:
                GameBoard.lights.get(0).setBoolean(true);
                GameBoard.lights.get(2).setBoolean(true);
                GameBoard.lights.get(3).setBoolean(true);
                GameBoard.lights.get(5).setBoolean(true);
                GameBoard.lights.get(6).setBoolean(true);
                GameBoard.lights.get(8).setBoolean(true);
                GameBoard.lights.get(9).setBoolean(true);
                GameBoard.lights.get(12).setBoolean(true);
                GameBoard.lights.get(14).setBoolean(true);
                GameBoard.lights.get(17).setBoolean(true);
                GameBoard.lights.get(19).setBoolean(true);
                GameBoard.lights.get(23).setBoolean(true);
                break;

            case 46:
                GameBoard.lights.get(1).setBoolean(true);
                GameBoard.lights.get(3).setBoolean(true);
                GameBoard.lights.get(4).setBoolean(true);
                GameBoard.lights.get(5).setBoolean(true);
                GameBoard.lights.get(6).setBoolean(true);
                GameBoard.lights.get(7).setBoolean(true);
                GameBoard.lights.get(12).setBoolean(true);
                GameBoard.lights.get(14).setBoolean(true);
                GameBoard.lights.get(16).setBoolean(true);
                GameBoard.lights.get(18).setBoolean(true);
                GameBoard.lights.get(19).setBoolean(true);
                break;

            case 47:
                GameBoard.lights.get(1).setBoolean(true);
                GameBoard.lights.get(2).setBoolean(true);
                GameBoard.lights.get(8).setBoolean(true);
                GameBoard.lights.get(12).setBoolean(true);
                GameBoard.lights.get(13).setBoolean(true);
                GameBoard.lights.get(14).setBoolean(true);
                GameBoard.lights.get(15).setBoolean(true);
                GameBoard.lights.get(16).setBoolean(true);
                GameBoard.lights.get(17).setBoolean(true);
                GameBoard.lights.get(18).setBoolean(true);
                GameBoard.lights.get(24).setBoolean(true);
                break;

            case 48:
                GameBoard.lights.get(2).setBoolean(true);
                GameBoard.lights.get(3).setBoolean(true);
                GameBoard.lights.get(5).setBoolean(true);
                GameBoard.lights.get(10).setBoolean(true);
                GameBoard.lights.get(12).setBoolean(true);
                GameBoard.lights.get(14).setBoolean(true);
                GameBoard.lights.get(19).setBoolean(true);
                GameBoard.lights.get(21).setBoolean(true);
                GameBoard.lights.get(22).setBoolean(true);
                break;

            case 49:
                GameBoard.lights.get(0).setBoolean(true);
                GameBoard.lights.get(4).setBoolean(true);
                GameBoard.lights.get(5).setBoolean(true);
                GameBoard.lights.get(7).setBoolean(true);
                GameBoard.lights.get(9).setBoolean(true);
                GameBoard.lights.get(10).setBoolean(true);
                GameBoard.lights.get(12).setBoolean(true);
                GameBoard.lights.get(14).setBoolean(true);
                GameBoard.lights.get(15).setBoolean(true);
                GameBoard.lights.get(17).setBoolean(true);
                GameBoard.lights.get(19).setBoolean(true);
                GameBoard.lights.get(20).setBoolean(true);
                GameBoard.lights.get(24).setBoolean(true);
                break;

            case 50:
                GameBoard.lights.get(1).setBoolean(true);
                GameBoard.lights.get(9).setBoolean(true);
                GameBoard.lights.get(15).setBoolean(true);
                GameBoard.lights.get(23).setBoolean(true);
                break;

            case 51:
                GameBoard.lights.get(0).setBoolean(true);
                GameBoard.lights.get(1).setBoolean(true);
                GameBoard.lights.get(2).setBoolean(true);
                GameBoard.lights.get(4).setBoolean(true);
                GameBoard.lights.get(5).setBoolean(true);
                GameBoard.lights.get(6).setBoolean(true);
                GameBoard.lights.get(8).setBoolean(true);
                GameBoard.lights.get(9).setBoolean(true);
                GameBoard.lights.get(11).setBoolean(true);
                GameBoard.lights.get(13).setBoolean(true);
                GameBoard.lights.get(14).setBoolean(true);
                GameBoard.lights.get(15).setBoolean(true);
                GameBoard.lights.get(16).setBoolean(true);
                GameBoard.lights.get(18).setBoolean(true);
                GameBoard.lights.get(19).setBoolean(true);
                GameBoard.lights.get(22).setBoolean(true);
                break;

            case 52:
                GameBoard.lights.get(2).setBoolean(true);
                GameBoard.lights.get(4).setBoolean(true);
                GameBoard.lights.get(10).setBoolean(true);
                GameBoard.lights.get(12).setBoolean(true);
                break;

            case 53:
                GameBoard.lights.get(0).setBoolean(true);
                GameBoard.lights.get(1).setBoolean(true);
                GameBoard.lights.get(2).setBoolean(true);
                GameBoard.lights.get(3).setBoolean(true);
                GameBoard.lights.get(4).setBoolean(true);
                GameBoard.lights.get(5).setBoolean(true);
                GameBoard.lights.get(6).setBoolean(true);
                GameBoard.lights.get(7).setBoolean(true);
                GameBoard.lights.get(8).setBoolean(true);
                GameBoard.lights.get(9).setBoolean(true);
                GameBoard.lights.get(10).setBoolean(true);
                GameBoard.lights.get(11).setBoolean(true);
                GameBoard.lights.get(13).setBoolean(true);
                GameBoard.lights.get(14).setBoolean(true);
                GameBoard.lights.get(15).setBoolean(true);
                GameBoard.lights.get(16).setBoolean(true);
                GameBoard.lights.get(17).setBoolean(true);
                GameBoard.lights.get(18).setBoolean(true);
                GameBoard.lights.get(19).setBoolean(true);
                GameBoard.lights.get(20).setBoolean(true);
                GameBoard.lights.get(21).setBoolean(true);
                GameBoard.lights.get(22).setBoolean(true);
                GameBoard.lights.get(23).setBoolean(true);
                GameBoard.lights.get(24).setBoolean(true);
                break;

            case 54:
                GameBoard.lights.get(6).setBoolean(true);
                GameBoard.lights.get(18).setBoolean(true);
                break;

            case 55:
                GameBoard.lights.get(0).setBoolean(true);
                GameBoard.lights.get(1).setBoolean(true);
                GameBoard.lights.get(2).setBoolean(true);
                GameBoard.lights.get(4).setBoolean(true);
                GameBoard.lights.get(6).setBoolean(true);
                GameBoard.lights.get(7).setBoolean(true);
                GameBoard.lights.get(10).setBoolean(true);
                GameBoard.lights.get(11).setBoolean(true);
                GameBoard.lights.get(14).setBoolean(true);
                GameBoard.lights.get(15).setBoolean(true);
                GameBoard.lights.get(17).setBoolean(true);
                GameBoard.lights.get(18).setBoolean(true);
                GameBoard.lights.get(19).setBoolean(true);
                GameBoard.lights.get(23).setBoolean(true);
                GameBoard.lights.get(24).setBoolean(true);
                break;

            case 56:
                GameBoard.lights.get(0).setBoolean(true);
                GameBoard.lights.get(1).setBoolean(true);
                GameBoard.lights.get(2).setBoolean(true);
                GameBoard.lights.get(4).setBoolean(true);
                GameBoard.lights.get(7).setBoolean(true);
                GameBoard.lights.get(9).setBoolean(true);
                GameBoard.lights.get(10).setBoolean(true);
                GameBoard.lights.get(11).setBoolean(true);
                GameBoard.lights.get(12).setBoolean(true);
                GameBoard.lights.get(13).setBoolean(true);
                GameBoard.lights.get(17).setBoolean(true);
                GameBoard.lights.get(18).setBoolean(true);
                GameBoard.lights.get(19).setBoolean(true);
                GameBoard.lights.get(20).setBoolean(true);
                GameBoard.lights.get(22).setBoolean(true);
                GameBoard.lights.get(24).setBoolean(true);
                break;

            case 57:
                GameBoard.lights.get(1).setBoolean(true);
                GameBoard.lights.get(3).setBoolean(true);
                GameBoard.lights.get(7).setBoolean(true);
                GameBoard.lights.get(12).setBoolean(true);
                GameBoard.lights.get(16).setBoolean(true);
                GameBoard.lights.get(17).setBoolean(true);
                GameBoard.lights.get(18).setBoolean(true);
                GameBoard.lights.get(20).setBoolean(true);
                GameBoard.lights.get(24).setBoolean(true);
                break;

            case 58:
                GameBoard.lights.get(2).setBoolean(true);
                GameBoard.lights.get(6).setBoolean(true);
                GameBoard.lights.get(7).setBoolean(true);
                GameBoard.lights.get(8).setBoolean(true);
                GameBoard.lights.get(10).setBoolean(true);
                GameBoard.lights.get(12).setBoolean(true);
                GameBoard.lights.get(14).setBoolean(true);
                GameBoard.lights.get(17).setBoolean(true);
                GameBoard.lights.get(22).setBoolean(true);
                break;

            case 59:
                GameBoard.lights.get(6).setBoolean(true);
                GameBoard.lights.get(7).setBoolean(true);
                GameBoard.lights.get(8).setBoolean(true);
                GameBoard.lights.get(9).setBoolean(true);
                GameBoard.lights.get(15).setBoolean(true);
                GameBoard.lights.get(16).setBoolean(true);
                GameBoard.lights.get(17).setBoolean(true);
                GameBoard.lights.get(18).setBoolean(true);
                break;

            case 60:
                GameBoard.lights.get(0).setBoolean(true);
                GameBoard.lights.get(1).setBoolean(true);
                GameBoard.lights.get(2).setBoolean(true);
                GameBoard.lights.get(4).setBoolean(true);
                GameBoard.lights.get(5).setBoolean(true);
                GameBoard.lights.get(7).setBoolean(true);
                GameBoard.lights.get(9).setBoolean(true);
                GameBoard.lights.get(10).setBoolean(true);
                GameBoard.lights.get(12).setBoolean(true);
                GameBoard.lights.get(14).setBoolean(true);
                GameBoard.lights.get(15).setBoolean(true);
                GameBoard.lights.get(17).setBoolean(true);
                GameBoard.lights.get(19).setBoolean(true);
                GameBoard.lights.get(20).setBoolean(true);
                GameBoard.lights.get(22).setBoolean(true);
                GameBoard.lights.get(23).setBoolean(true);
                GameBoard.lights.get(24).setBoolean(true);
                break;

            case 61:
                GameBoard.lights.get(0).setBoolean(true);
                GameBoard.lights.get(6).setBoolean(true);
                GameBoard.lights.get(7).setBoolean(true);
                GameBoard.lights.get(8).setBoolean(true);
                GameBoard.lights.get(9).setBoolean(true);
                GameBoard.lights.get(11).setBoolean(true);
                GameBoard.lights.get(13).setBoolean(true);
                GameBoard.lights.get(14).setBoolean(true);
                GameBoard.lights.get(18).setBoolean(true);
                GameBoard.lights.get(20).setBoolean(true);
                GameBoard.lights.get(21).setBoolean(true);
                break;

            case 62:
                GameBoard.lights.get(1).setBoolean(true);
                GameBoard.lights.get(10).setBoolean(true);
                GameBoard.lights.get(11).setBoolean(true);
                GameBoard.lights.get(13).setBoolean(true);
                GameBoard.lights.get(14).setBoolean(true);
                GameBoard.lights.get(19).setBoolean(true);
                GameBoard.lights.get(22).setBoolean(true);
                GameBoard.lights.get(23).setBoolean(true);
                break;

            case 63:
                GameBoard.lights.get(0).setBoolean(true);
                GameBoard.lights.get(5).setBoolean(true);
                GameBoard.lights.get(6).setBoolean(true);
                GameBoard.lights.get(8).setBoolean(true);
                GameBoard.lights.get(12).setBoolean(true);
                GameBoard.lights.get(14).setBoolean(true);
                GameBoard.lights.get(16).setBoolean(true);
                GameBoard.lights.get(18).setBoolean(true);
                GameBoard.lights.get(19).setBoolean(true);
                GameBoard.lights.get(20).setBoolean(true);
                GameBoard.lights.get(21).setBoolean(true);
                GameBoard.lights.get(22).setBoolean(true);
                GameBoard.lights.get(23).setBoolean(true);
                GameBoard.lights.get(24).setBoolean(true);
                break;

            case 64:
                GameBoard.lights.get(0).setBoolean(true);
                GameBoard.lights.get(2).setBoolean(true);
                GameBoard.lights.get(6).setBoolean(true);
                GameBoard.lights.get(8).setBoolean(true);
                GameBoard.lights.get(9).setBoolean(true);
                GameBoard.lights.get(10).setBoolean(true);
                GameBoard.lights.get(11).setBoolean(true);
                GameBoard.lights.get(15).setBoolean(true);
                GameBoard.lights.get(16).setBoolean(true);
                GameBoard.lights.get(18).setBoolean(true);
                GameBoard.lights.get(23).setBoolean(true);
                break;

            case 65:
                GameBoard.lights.get(1).setBoolean(true);
                GameBoard.lights.get(2).setBoolean(true);
                GameBoard.lights.get(3).setBoolean(true);
                GameBoard.lights.get(4).setBoolean(true);
                GameBoard.lights.get(5).setBoolean(true);
                GameBoard.lights.get(6).setBoolean(true);
                GameBoard.lights.get(8).setBoolean(true);
                GameBoard.lights.get(10).setBoolean(true);
                GameBoard.lights.get(11).setBoolean(true);
                GameBoard.lights.get(12).setBoolean(true);
                GameBoard.lights.get(14).setBoolean(true);
                GameBoard.lights.get(17).setBoolean(true);
                GameBoard.lights.get(19).setBoolean(true);
                GameBoard.lights.get(20).setBoolean(true);
                GameBoard.lights.get(21).setBoolean(true);
                GameBoard.lights.get(22).setBoolean(true);
                GameBoard.lights.get(23).setBoolean(true);
                GameBoard.lights.get(24).setBoolean(true);
                break;

            case 66:
                GameBoard.lights.get(0).setBoolean(true);
                GameBoard.lights.get(3).setBoolean(true);
                GameBoard.lights.get(6).setBoolean(true);
                GameBoard.lights.get(8).setBoolean(true);
                GameBoard.lights.get(10).setBoolean(true);
                GameBoard.lights.get(11).setBoolean(true);
                GameBoard.lights.get(13).setBoolean(true);
                GameBoard.lights.get(14).setBoolean(true);
                GameBoard.lights.get(16).setBoolean(true);
                GameBoard.lights.get(21).setBoolean(true);
                GameBoard.lights.get(24).setBoolean(true);
                break;

            case 67:
                GameBoard.lights.get(0).setBoolean(true);
                GameBoard.lights.get(2).setBoolean(true);
                GameBoard.lights.get(5).setBoolean(true);
                GameBoard.lights.get(6).setBoolean(true);
                GameBoard.lights.get(8).setBoolean(true);
                GameBoard.lights.get(9).setBoolean(true);
                GameBoard.lights.get(11).setBoolean(true);
                GameBoard.lights.get(13).setBoolean(true);
                GameBoard.lights.get(14).setBoolean(true);
                GameBoard.lights.get(15).setBoolean(true);
                GameBoard.lights.get(16).setBoolean(true);
                GameBoard.lights.get(21).setBoolean(true);
                GameBoard.lights.get(24).setBoolean(true);
                break;

            case 68:
                GameBoard.lights.get(1).setBoolean(true);
                GameBoard.lights.get(2).setBoolean(true);
                GameBoard.lights.get(3).setBoolean(true);
                GameBoard.lights.get(4).setBoolean(true);
                GameBoard.lights.get(5).setBoolean(true);
                GameBoard.lights.get(6).setBoolean(true);
                GameBoard.lights.get(9).setBoolean(true);
                GameBoard.lights.get(10).setBoolean(true);
                GameBoard.lights.get(14).setBoolean(true);
                GameBoard.lights.get(15).setBoolean(true);
                GameBoard.lights.get(18).setBoolean(true);
                GameBoard.lights.get(19).setBoolean(true);
                GameBoard.lights.get(20).setBoolean(true);
                GameBoard.lights.get(21).setBoolean(true);
                GameBoard.lights.get(22).setBoolean(true);
                GameBoard.lights.get(23).setBoolean(true);
                break;

            case 69:
                GameBoard.lights.get(0).setBoolean(true);
                GameBoard.lights.get(2).setBoolean(true);
                GameBoard.lights.get(6).setBoolean(true);
                GameBoard.lights.get(10).setBoolean(true);
                GameBoard.lights.get(14).setBoolean(true);
                GameBoard.lights.get(18).setBoolean(true);
                GameBoard.lights.get(22).setBoolean(true);
                GameBoard.lights.get(24).setBoolean(true);
                break;

            case 70:
                GameBoard.lights.get(0).setBoolean(true);
                GameBoard.lights.get(7).setBoolean(true);
                GameBoard.lights.get(10).setBoolean(true);
                GameBoard.lights.get(14).setBoolean(true);
                GameBoard.lights.get(17).setBoolean(true);
                GameBoard.lights.get(24).setBoolean(true);
                break;

            case 71:
                GameBoard.lights.get(1).setBoolean(true);
                GameBoard.lights.get(2).setBoolean(true);
                GameBoard.lights.get(4).setBoolean(true);
                GameBoard.lights.get(8).setBoolean(true);
                GameBoard.lights.get(10).setBoolean(true);
                GameBoard.lights.get(12).setBoolean(true);
                GameBoard.lights.get(16).setBoolean(true);
                GameBoard.lights.get(18).setBoolean(true);
                GameBoard.lights.get(20).setBoolean(true);
                GameBoard.lights.get(22).setBoolean(true);
                break;

            case 72:
                GameBoard.lights.get(0).setBoolean(true);
                GameBoard.lights.get(2).setBoolean(true);
                GameBoard.lights.get(3).setBoolean(true);
                GameBoard.lights.get(4).setBoolean(true);
                GameBoard.lights.get(5).setBoolean(true);
                GameBoard.lights.get(6).setBoolean(true);
                GameBoard.lights.get(7).setBoolean(true);
                GameBoard.lights.get(9).setBoolean(true);
                GameBoard.lights.get(11).setBoolean(true);
                GameBoard.lights.get(13).setBoolean(true);
                GameBoard.lights.get(14).setBoolean(true);
                GameBoard.lights.get(15).setBoolean(true);
                GameBoard.lights.get(17).setBoolean(true);
                GameBoard.lights.get(18).setBoolean(true);
                GameBoard.lights.get(20).setBoolean(true);
                GameBoard.lights.get(22).setBoolean(true);
                GameBoard.lights.get(23).setBoolean(true);
                break;

            case 73:
                GameBoard.lights.get(0).setBoolean(true);
                GameBoard.lights.get(5).setBoolean(true);
                GameBoard.lights.get(7).setBoolean(true);
                GameBoard.lights.get(9).setBoolean(true);
                GameBoard.lights.get(15).setBoolean(true);
                GameBoard.lights.get(16).setBoolean(true);
                GameBoard.lights.get(17).setBoolean(true);
                GameBoard.lights.get(18).setBoolean(true);
                GameBoard.lights.get(19).setBoolean(true);
                GameBoard.lights.get(20).setBoolean(true);
                break;

            case 74:
                GameBoard.lights.get(0).setBoolean(true);
                GameBoard.lights.get(9).setBoolean(true);
                GameBoard.lights.get(11).setBoolean(true);
                GameBoard.lights.get(12).setBoolean(true);
                GameBoard.lights.get(15).setBoolean(true);
                GameBoard.lights.get(17).setBoolean(true);
                GameBoard.lights.get(18).setBoolean(true);
                GameBoard.lights.get(20).setBoolean(true);
                GameBoard.lights.get(21).setBoolean(true);
                GameBoard.lights.get(23).setBoolean(true);
                GameBoard.lights.get(24).setBoolean(true);
                break;

            case 75:
                GameBoard.lights.get(8).setBoolean(true);
                GameBoard.lights.get(9).setBoolean(true);
                GameBoard.lights.get(11).setBoolean(true);
                GameBoard.lights.get(12).setBoolean(true);
                GameBoard.lights.get(13).setBoolean(true);
                GameBoard.lights.get(16).setBoolean(true);
                GameBoard.lights.get(20).setBoolean(true);
                GameBoard.lights.get(21).setBoolean(true);
                break;

            case 76:
                GameBoard.lights.get(10).setBoolean(true);
                GameBoard.lights.get(15).setBoolean(true);
                GameBoard.lights.get(20).setBoolean(true);
                break;

            case 77:
                GameBoard.lights.get(2).setBoolean(true);
                GameBoard.lights.get(7).setBoolean(true);
                GameBoard.lights.get(8).setBoolean(true);
                GameBoard.lights.get(9).setBoolean(true);
                GameBoard.lights.get(11).setBoolean(true);
                GameBoard.lights.get(13).setBoolean(true);
                GameBoard.lights.get(17).setBoolean(true);
                GameBoard.lights.get(20).setBoolean(true);
                GameBoard.lights.get(22).setBoolean(true);
                GameBoard.lights.get(23).setBoolean(true);
                break;

            case 78:
                GameBoard.lights.get(0).setBoolean(true);
                GameBoard.lights.get(7).setBoolean(true);
                GameBoard.lights.get(10).setBoolean(true);
                GameBoard.lights.get(11).setBoolean(true);
                GameBoard.lights.get(12).setBoolean(true);
                GameBoard.lights.get(13).setBoolean(true);
                GameBoard.lights.get(14).setBoolean(true);
                GameBoard.lights.get(19).setBoolean(true);
                GameBoard.lights.get(20).setBoolean(true);
                break;

            case 79:
                GameBoard.lights.get(0).setBoolean(true);
                GameBoard.lights.get(2).setBoolean(true);
                GameBoard.lights.get(3).setBoolean(true);
                GameBoard.lights.get(5).setBoolean(true);
                GameBoard.lights.get(8).setBoolean(true);
                GameBoard.lights.get(9).setBoolean(true);
                GameBoard.lights.get(10).setBoolean(true);
                GameBoard.lights.get(12).setBoolean(true);
                GameBoard.lights.get(13).setBoolean(true);
                GameBoard.lights.get(15).setBoolean(true);
                GameBoard.lights.get(16).setBoolean(true);
                GameBoard.lights.get(18).setBoolean(true);
                GameBoard.lights.get(22).setBoolean(true);
                break;

            case 80:
                GameBoard.lights.get(0).setBoolean(true);
                GameBoard.lights.get(1).setBoolean(true);
                GameBoard.lights.get(2).setBoolean(true);
                GameBoard.lights.get(3).setBoolean(true);
                GameBoard.lights.get(5).setBoolean(true);
                GameBoard.lights.get(6).setBoolean(true);
                GameBoard.lights.get(7).setBoolean(true);
                GameBoard.lights.get(10).setBoolean(true);
                GameBoard.lights.get(11).setBoolean(true);
                GameBoard.lights.get(14).setBoolean(true);
                GameBoard.lights.get(15).setBoolean(true);
                GameBoard.lights.get(18).setBoolean(true);
                GameBoard.lights.get(19).setBoolean(true);
                GameBoard.lights.get(22).setBoolean(true);
                GameBoard.lights.get(23).setBoolean(true);
                GameBoard.lights.get(24).setBoolean(true);
                break;
            default:
                Log.d("Levels", "Too high a level called. there is a massive issue!!!");
        }
    }
}
