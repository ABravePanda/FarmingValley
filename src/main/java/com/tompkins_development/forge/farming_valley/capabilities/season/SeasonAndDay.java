package com.tompkins_development.forge.farming_valley.capabilities.season;

import com.tompkins_development.forge.farming_valley.enums.Season;
import net.minecraft.nbt.CompoundTag;

public class SeasonAndDay {

    private int day;
    private Season season;

    private static final String NBT_DAY = "day";
    private static final String NBT_SEASON = "season";


    public int getDay() {
        return this.day;
    }


    public Season getSeason() {
        return this.season;
    }


    public void setDay(int day) {
        this.day = day;
    }


    public void setSeason(Season season) {
        this.season = season;
    }

    public CompoundTag serializeNBT(CompoundTag nbt) {
        nbt.putInt(NBT_DAY, day);
        if(season == null)
            this.season = Season.SPRING;
        nbt.putString(NBT_SEASON, season.getKey());
        return nbt;
    }

    public void deserializeNBT(CompoundTag nbt) {
        this.day = nbt.getInt(NBT_DAY);
        if(Season.valueOf(nbt.getString(NBT_SEASON)) != null) {
            this.season = Season.valueOf(nbt.getString(NBT_SEASON));
        } else this.season = Season.SPRING;
    }
}