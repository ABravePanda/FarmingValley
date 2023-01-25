package com.tompkins_development.forge.farming_valley;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.logging.LogUtils;
import com.tompkins_development.forge.farming_valley.blocks.ModBlocks;
import com.tompkins_development.forge.farming_valley.capabilities.CapabilityEvents;
import com.tompkins_development.forge.farming_valley.capabilities.coin.CoinsCapabilityProvider;
import com.tompkins_development.forge.farming_valley.client.ModOverlays;
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
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.client.gui.GuiUtils;
import net.minecraftforge.client.gui.OverlayRegistry;
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
        eventBus.addListener(this::commonSetup);

        GeckoLib.initialize();
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        Keybinds.register();
        EntityRenderers.register(ModEntityTypes.JOHN.get(), JohnRenderer::new);
        EntityRenderers.register(ModEntityTypes.PIERRE.get(), PierreRenderer::new);

        ItemBlockRenderTypes.setRenderLayer(ModBlocks.SHIPPNG_CRATE.get(), RenderType.translucent());
        ModOverlays.register();
    }


//        OverlayRegistry.registerOverlayTop("CoinOverlay", (gui, mStack, partialTicks, screenWidth, screenHeight) -> {
//            Minecraft minecraft = Minecraft.getInstance();
//            ResourceLocation resourceLocation = new ResourceLocation(FarmingValleyMod.MOD_ID, "textures/gui/coin_gui.png");
//            if (minecraft.gameMode == null) return;
//            if (minecraft.level == null) return;
//            TextureManager tm = minecraft.getTextureManager();
//            gui.setupOverlayRenderState(true, false, resourceLocation);
//            GuiUtils.drawTexturedModalRect(new PoseStack(), 10, 0, 0, 0, 18, 18, 1);
//        });
//    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ModMessages.register();
        });
    }



}
