package com.tompkins_development.forge.farming_valley.items.tabs;

import com.tompkins_development.forge.farming_valley.items.ModItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class ModCreativeModeTabs {
    public static final CreativeModeTab SEED_TAB = new CreativeModeTab("seeds") {

        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.SEED_BAG.get());
        }

    };

    public static final CreativeModeTab FARM_BLOCK_TAB = new CreativeModeTab("farm_blocks") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Items.GOLDEN_HOE.asItem());
        }
    };
}
