package com.tompkins_development.forge.farming_valley.capabilities.crates;

import com.tompkins_development.forge.farming_valley.capabilities.coin.Coins;
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

public class ShippingCrateCapabilityProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<ShippingCrate> SHIPPING_CRATE = CapabilityManager.get(new CapabilityToken<ShippingCrate>() {});

    private ShippingCrate shippingCrate = null;
    private LazyOptional<ShippingCrate> optional = LazyOptional.of(this::createShippingCrate);

    private ShippingCrate createShippingCrate() {
        if(this.shippingCrate == null) {
            this.shippingCrate = new ShippingCrate();
        }

        return this.shippingCrate;
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == SHIPPING_CRATE) return optional.cast();
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createShippingCrate().serializeNBT(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createShippingCrate().deserializeNBT(nbt);
    }
}
