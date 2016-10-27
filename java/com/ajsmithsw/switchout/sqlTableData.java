package com.ajsmithsw.switchout;

import android.provider.BaseColumns;

/**
 * Created by Alexander Smith on 27/05/15.
 */
public class sqlTableData {

    public sqlTableData() {

    }

    public static abstract class TableInfo implements BaseColumns {
        public static final String DATABASE_NAME = "switchout_database";
        public static final String TABLE_NAME = "player_progress";
        public static final String LEVEL = "level";
        public static final String PAR = "par";
        public static final String BEST = "best";
        public static final String STARS = "stars";

        public static final String HINT_TABLE_NAME = "hints_remaining";
        public static final String PLAYER = "player";
        public static final String HINTS_REMAINING = "hints_remaining";

        public static final String BONUS_TABLE_NAME = "social_bonuses";
        public static final String PLAYER_BONUS = "player_bonus";
        public static final String FACEBOOK_BONUS = "facebook_bonus";
        public static final String TWITTER_BONUS = "twitter_bonus";

    }

}
