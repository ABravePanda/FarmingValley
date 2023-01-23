package com.tompkins_development.forge.farming_valley.capabilities.season;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.checkerframework.checker.units.qual.C;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SeasonCapabilityProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<SeasonAndDay> SEASON_AND_DAY = CapabilityManager.get(new CapabilityToken<SeasonAndDay>() {});

    private SeasonAndDay seasonAndDay = null;
    private LazyOptional<SeasonAndDay> optional = LazyOptional.of(this::createSeasonAndDay);

    private SeasonAndDay createSeasonAndDay() {
        if(this.seasonAndDay == null) {
            this.seasonAndDay = new SeasonAndDay();
        }

        return this.seasonAndDay;
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == SEASON_AND_DAY) return optional.cast();
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createSeasonAndDay().serializeNBT(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createSeasonAndDay().deserializeNBT(nbt);
    }
}
