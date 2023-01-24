package com.tompkins_development.forge.farming_valley;

import com.mojang.logging.LogUtils;
import com.tompkins_development.forge.farming_valley.blocks.ModBlocks;
import com.tompkins_development.forge.farming_valley.capabilities.CapabilityEvents;
import com.tompkins_development.forge.farming_valley.capabilities.coin.CoinsCapabilityProvider;
import com.tompkins_development.forge.farming_valley.entity.ModEntityTypes;
import com.tompkins_development.forge.farming_valley.entity.client.JohnRenderer;
import com.tompkins_development.forge.farming_valley.entity.client.PierreRenderer;
import com.tompkins_development.forge.farming_valley.entity.custom.PierreEntity;
import com.tompkins_development.forge.farming_valley.events.BlockPlaceModEvent;
import com.tompkins_development.forge.farming_valley.items.ModItems;
import com.tompkins_development.forge.farming_valley.keybinds.Keybinds;
import com.tompkins_development.forge.farming_valley.networking.ModMessages;
import com.tompkins_development.forge.farming_valley.sounds.ModSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import software.bernie.geckolib3.GeckoLib;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(FarmingValleyMod.MOD_ID)
public class FarmingValleyMod
{
    public static final String MOD_ID = "farmingvalley";
    public static int days = 0;
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public FarmingValleyMod()
    {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModBlocks.register(eventBus);
        ModItems.register(eventBus);
        ModSounds.register(eventBus);
        ModEntityTypes.register(eventBus);
        //eventBus.addGenericListener(Entity.class, CapabilityEvents::attach);

        MinecraftForge.EVENT_BUS.register(this);

        eventBus.addListener(this::clientSetup);
        eventBus.addListener(this::commonSetuo);

        GeckoLib.initialize();
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        Keybinds.register();
        EntityRenderers.register(ModEntityTypes.JOHN.get(), JohnRenderer::new);
        EntityRenderers.register(ModEntityTypes.PIERRE.get(), PierreRenderer::new);

        ItemBlockRenderTypes.setRenderLayer(ModBlocks.SHIPPNG_CRATE.get(), RenderType.translucent());
    }

    private void commonSetuo(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ModMessages.register();
        });
    }



}
