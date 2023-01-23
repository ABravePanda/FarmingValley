package com.tompkins_development.forge.farming_valley.capabilities.season;

import com.tompkins_development.forge.farming_valley.enums.Season;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;

public class SeasonInstance {

    public static Season season = Season.WINTER;
    public static int day = 1;
    public static String time = "0:00";

    public static void updateTime(Level level) {
        if(level.isClientSide()) return;
        ServerLevel serverLevel = (ServerLevel) level;
        long gameTime = serverLevel.getDayTime();
        long hours = gameTime / 1000 + 6;
        long minutes = (gameTime % 1000) * 60 / 1000;
        String ampm = "AM";
        if (hours >= 12) {
            hours -= 12;
            ampm = "PM";
        }
        if (hours >= 12) {
            hours -= 12;
            ampm = "AM";
        }
        if (hours == 0) hours = 12;
        String mm = "0" + minutes;
        mm = mm.substring(mm.length() - 2, mm.length());
        time = hours + ":" + mm + " " + ampm;
    }

    public static String getSeason() {
        return season.getKey().toLowerCase().substring(0,1).toUpperCase() + season.getKey().toLowerCase().substring(1);
    }
}
