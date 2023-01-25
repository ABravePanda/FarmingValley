package com.tompkins_development.forge.farming_valley.events;

import com.mojang.blaze3d.vertex.PoseStack;
import com.tompkins_development.forge.farming_valley.FarmingValleyMod;
import com.tompkins_development.forge.farming_valley.capabilities.coin.ClientBalanceData;
import com.tompkins_development.forge.farming_valley.capabilities.season.SeasonInstance;
import com.tompkins_development.forge.farming_valley.entity.ModEntityTypes;
import com.tompkins_development.forge.farming_valley.entity.custom.JohnEntity;
import com.tompkins_development.forge.farming_valley.entity.custom.PierreEntity;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.awt.*;

@Mod.EventBusSubscriber(modid = FarmingValleyMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(ModEntityTypes.JOHN.get(), JohnEntity.setAttributes());
        event.put(ModEntityTypes.PIERRE.get(), PierreEntity.setAttributes());
    }


}
