package com.tompkins_development.forge.farming_valley.events;

import com.tompkins_development.forge.farming_valley.FarmingValleyMod;
import com.tompkins_development.forge.farming_valley.capabilities.coins.CoinsCapabilityProvider;
import com.tompkins_development.forge.farming_valley.capabilities.season.SeasonCapabilityProvider;
import com.tompkins_development.forge.farming_valley.capabilities.sleeping.SleepingCapabilityProvider;
import net.minecraft.resources.ResourceLocation;
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
    public static void attachEntity(final AttachCapabilitiesEvent<LivingEntity> event) {
        if(!(event.getObject() instanceof Player)) return;
        if(!event.getObject().getCapability(CoinsCapabilityProvider.COINS).isPresent()) {
            event.addCapability(new ResourceLocation(FarmingValleyMod.MOD_ID, "coins"), new CoinsCapabilityProvider());
        }
    }

    @SubscribeEvent
    public static void register(RegisterCapabilitiesEvent event) {
        event.register(SeasonCapabilityProvider.class);
        event.register(SleepingCapabilityProvider.class);
        event.register(CoinsCapabilityProvider.class);
    }

}
