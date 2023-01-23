package com.tompkins_development.forge.farming_valley.items;

import com.tompkins_development.forge.farming_valley.FarmingValleyMod;
import com.tompkins_development.forge.farming_valley.blocks.ModBlocks;
import com.tompkins_development.forge.farming_valley.enums.Season;
import com.tompkins_development.forge.farming_valley.items.tabs.ModCreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, FarmingValleyMod.MOD_ID);


    //public static final RegistryObject<Item> TOMATO_SEEDS = ITEMS.register("tomato_seeds", () -> new ItemNameBlockItem(ModBlocks.TOMATO_PLANT.get(), new Item.Properties().tab(ModCreativeModTab.FOOD_TAB)));

    public static final RegistryObject<Item> SEED_BAG = ITEMS.register("seed_bag", () -> new Item(new Item.Properties().tab(ModCreativeModeTabs.SEED_TAB)));
    public static final RegistryObject<Item> CAULIFLOWER_SEEDS = ITEMS.register("cauliflower_seeds", () -> new SeedItem(ModBlocks.CAULIFLOWER_PLANT.get(), 12, Season.SPRING));
    public static final RegistryObject<Item> GARLIC_SEEDS = ITEMS.register("garlic_seeds", () -> new SeedItem(ModBlocks.GARLIC_PLANT.get(), 4, Season.SPRING));
    public static final RegistryObject<Item> PARSNIP_SEEDS = ITEMS.register("parsnip_seeds", () -> new SeedItem(ModBlocks.PARSNIP_PLANT.get(), 4, Season.SPRING));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
