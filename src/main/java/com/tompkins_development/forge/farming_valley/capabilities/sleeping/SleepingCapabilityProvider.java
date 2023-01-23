package com.tompkins_development.forge.farming_valley.capabilities.sleeping;

import com.tompkins_development.forge.farming_valley.capabilities.season.SeasonAndDay;
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

public class SleepingCapabilityProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<Sleeping> SLEEPING = CapabilityManager.get(new CapabilityToken<Sleeping>() {});

    private Sleeping sleeping = null;
    private LazyOptional<Sleeping> optional = LazyOptional.of(this::createSleeping);

    private Sleeping createSleeping() {
        if(this.sleeping == null) {
            this.sleeping = new Sleeping();
        }

        return this.sleeping;
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == SLEEPING) return optional.cast();
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createSleeping().serializeNBT(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createSleeping().deserializeNBT(nbt);
    }
}
