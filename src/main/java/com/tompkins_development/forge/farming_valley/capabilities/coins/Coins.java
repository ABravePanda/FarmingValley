package com.tompkins_development.forge.farming_valley.capabilities.coins;

import com.tompkins_development.forge.farming_valley.enums.Season;
import net.minecraft.nbt.CompoundTag;

public class Coins {

    private int balance;
    private static final String NBT_BALANCE = "balance";

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public CompoundTag serializeNBT(CompoundTag nbt) {
        nbt.putInt(NBT_BALANCE, balance);
        return nbt;
    }

    public void deserializeNBT(CompoundTag nbt) {
        this.balance = nbt.getInt(NBT_BALANCE);
    }
}