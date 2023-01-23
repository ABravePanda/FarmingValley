package com.tompkins_development.forge.farming_valley.events.custom;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.Event;


public class PlayersAwakeEvent extends Event {

    Level level;

    public PlayersAwakeEvent(Level level) {
        this.level = level;
    }

    public Level getLevel() {
        return level;
    }
}
