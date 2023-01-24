package com.tompkins_development.forge.farming_valley.capabilities.crates;

import net.minecraft.nbt.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ShippingCrate {

    private int value;

    private static final String NBT_VALUE = "value";

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void addValue(int value) {
        this.value+= value;
    }

    public CompoundTag serializeNBT(CompoundTag nbt) {
        nbt.putInt(NBT_VALUE, value);
        return nbt;
    }

    public void deserializeNBT(CompoundTag nbt) {
        this.value = nbt.getInt(NBT_VALUE);
    }

}
