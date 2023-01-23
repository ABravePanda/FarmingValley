package com.tompkins_development.forge.farming_valley;

import com.mojang.logging.LogUtils;
import com.tompkins_development.forge.farming_valley.blocks.ModBlocks;
import com.tompkins_development.forge.farming_valley.items.ModItems;
import com.tompkins_development.forge.farming_valley.keybinds.Keybinds;
import com.tompkins_development.forge.farming_valley.sounds.ModSounds;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

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

        MinecraftForge.EVENT_BUS.register(this);

        eventBus.addListener(this::clientSetup);
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        Keybinds.register();
    }


}
