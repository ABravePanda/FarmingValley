package com.tompkins_development.forge.farming_valley.events.custom;

import com.tompkins_development.forge.farming_valley.enums.Season;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

@Cancelable
public class DateChangeEvent extends Event {

    Level level;
    Season newSeason;
    Season oldSeason;
    int oldDay;
    int newDay;

    public DateChangeEvent(Level level, int oldDay, int newDay, Season newSeason, Season oldSeason) {
        this.level = level;
        this.oldDay = oldDay;
        this.newDay = newDay;
        this.newSeason = newSeason;
        this.oldSeason = oldSeason;
    }

    public Level getLevel() {
        return level;
    }

    public Season getNewSeason() {
        return newSeason;
    }

    public Season getOldSeason() {
        return oldSeason;
    }

    public boolean didSeasonChange() {
        return getNewSeason() != getOldSeason();
    }

    public int getNewDay() {
        return newDay;
    }

    public int getOldDay() {
        return oldDay;
    }
}
