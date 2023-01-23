package com.tompkins_development.forge.farming_valley.items;

import com.tompkins_development.forge.farming_valley.enums.Season;
import com.tompkins_development.forge.farming_valley.items.tabs.ModCreativeModeTabs;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SeedItem extends ItemNameBlockItem {

    private Season[] seasons;
    int growthDays;

    public SeedItem(Block pBlock, Properties pProperties) {
        super(pBlock, pProperties);
    }

    public SeedItem(Block pBlock, int growthDays, Season... seasons) {
        super(pBlock, new Item.Properties().tab(ModCreativeModeTabs.SEED_TAB));
        this.growthDays = growthDays;
        this.seasons = seasons;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        if(Screen.hasShiftDown()) {
            pTooltip.add(new TranslatableComponent("tooltip.farmingvalley.growthdays").append(new TextComponent(" " + growthDays)));
        }
        TranslatableComponent component = new TranslatableComponent("");
        int index = 1;
        for(Season season : seasons) {
            component.append(new TranslatableComponent("tooltip.farmingvalley.seasons." + season.getKey()));
            if(index < seasons.length)
                component.append(new TextComponent(", "));
            index++;
        }
        pTooltip.add(component);
    }
}
