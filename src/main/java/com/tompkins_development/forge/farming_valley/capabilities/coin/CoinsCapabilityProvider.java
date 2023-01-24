package com.tompkins_development.forge.farming_valley.capabilities.coin;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CoinsCapabilityProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<Coins> COINS = CapabilityManager.get(new CapabilityToken<Coins>() {});

    private Coins coins = null;
    private LazyOptional<Coins> optional = LazyOptional.of(this::createCoins);

    private Coins createCoins() {
        if(this.coins == null) {
            this.coins = new Coins();
        }

        return this.coins;
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == COINS) return optional.cast();
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createCoins().serializeNBT(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createCoins().deserializeNBT(nbt);
    }
}
