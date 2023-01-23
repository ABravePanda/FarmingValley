package com.tompkins_development.forge.farming_valley.items.tabs;

import com.tompkins_development.forge.farming_valley.items.ModItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTabs {
    public static final CreativeModeTab SEED_TAB = new CreativeModeTab("seeds") {

        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.SEED_BAG.get());
        }

    };
}
