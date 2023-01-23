package com.tompkins_development.forge.farming_valley.capabilities.sleeping;

import com.tompkins_development.forge.farming_valley.enums.Season;
import net.minecraft.nbt.CompoundTag;

public class Sleeping {

    private int sleeping;

    private static final String NBT_SLEEPING = "sleeping";

    public int getSleeping() {
        return sleeping;
    }

    public void setSleeping(int sleeping) {
        this.sleeping = sleeping;
    }

    public CompoundTag serializeNBT(CompoundTag nbt) {
        nbt.putInt(NBT_SLEEPING, sleeping);
        return nbt;
    }

    public void deserializeNBT(CompoundTag nbt) {
        this.sleeping = nbt.getInt(NBT_SLEEPING);
    }
}