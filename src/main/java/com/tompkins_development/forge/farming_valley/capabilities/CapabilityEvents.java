package com.tompkins_development.forge.farming_valley.capabilities;

import com.tompkins_development.forge.farming_valley.FarmingValleyMod;
import com.tompkins_development.forge.farming_valley.capabilities.coin.Coins;
import com.tompkins_development.forge.farming_valley.capabilities.coin.CoinsCapabilityProvider;
import com.tompkins_development.forge.farming_valley.capabilities.crates.ShippingCrate;
import com.tompkins_development.forge.farming_valley.capabilities.crates.ShippingCrateCapabilityProvider;
import com.tompkins_development.forge.farming_valley.capabilities.season.SeasonCapabilityProvider;
import com.tompkins_development.forge.farming_valley.capabilities.sleeping.SleepingCapabilityProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = FarmingValleyMod.MOD_ID)
public class CapabilityEvents {

    @SubscribeEvent
    public static void attachLevel(final AttachCapabilitiesEvent<Level> event) {
        if(!event.getObject().getCapability(SeasonCapabilityProvider.SEASON_AND_DAY).isPresent()) {
            event.addCapability(new ResourceLocation(FarmingValleyMod.MOD_ID, "season"), new SeasonCapabilityProvider());
        }
        if(!event.getObject().getCapability(SleepingCapabilityProvider.SLEEPING).isPresent()) {
            event.addCapability(new ResourceLocation(FarmingValleyMod.MOD_ID, "sleeping"), new SleepingCapabilityProvider());
        }
    }

    @SubscribeEvent
    public static void attach(final AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player player) {
            if (!player.getCapability(CoinsCapabilityProvider.COINS).isPresent()) {
                event.addCapability(new ResourceLocation(FarmingValleyMod.MOD_ID, "balance"), new CoinsCapabilityProvider());
            }
            if (!player.getCapability(ShippingCrateCapabilityProvider.SHIPPING_CRATE).isPresent()) {
                event.addCapability(new ResourceLocation(FarmingValleyMod.MOD_ID, "shippingcrate"), new ShippingCrateCapabilityProvider());
            }
        }
    }

    @SubscribeEvent
    public static void register(RegisterCapabilitiesEvent event) {
        event.register(SeasonCapabilityProvider.class);
        event.register(SleepingCapabilityProvider.class);
        event.register(CoinsCapabilityProvider.class);
        event.register(ShippingCrateCapabilityProvider.class);
    }

}
