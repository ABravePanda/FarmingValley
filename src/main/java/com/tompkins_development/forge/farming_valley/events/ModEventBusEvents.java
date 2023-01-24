package com.tompkins_development.forge.farming_valley.events;

import com.tompkins_development.forge.farming_valley.FarmingValleyMod;
import com.tompkins_development.forge.farming_valley.entity.ModEntityTypes;
import com.tompkins_development.forge.farming_valley.entity.custom.JohnEntity;
import com.tompkins_development.forge.farming_valley.entity.custom.PierreEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = FarmingValleyMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(ModEntityTypes.JOHN.get(), JohnEntity.setAttributes());
        event.put(ModEntityTypes.PIERRE.get(), PierreEntity.setAttributes());
    }

}
