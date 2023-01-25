package com.tompkins_development.forge.farming_valley.items;

import com.tompkins_development.forge.farming_valley.enums.Season;
import com.tompkins_development.forge.farming_valley.items.tabs.ModCreativeModeTabs;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.*;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.*;
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

    @Override
    public void fillItemCategory(CreativeModeTab pGroup, NonNullList<ItemStack> pItems) {
        if(this.getItemCategory() != pGroup) return;
        ItemStack itemStack = new ItemStack(this);
        CompoundTag tag = itemStack.getOrCreateTag();
        tag.put("seasons", new ListTag());
        ListTag listTag = tag.getList("seasons", 8);
        CompoundTag stringTag = new CompoundTag();
        for(Season s : seasons) {
            stringTag.putString(s.getKey(), s.getKey());
            listTag.add(stringTag);
        }
        tag.put("seasons", listTag);
        itemStack.setTag(tag);
        pItems.add(itemStack);
    }

    @Override
    public ItemStack getDefaultInstance() {
        ItemStack itemStack = new ItemStack(this);
        CompoundTag tag = itemStack.getOrCreateTag();
        tag.put("seasons", new ListTag());
        ListTag listTag = tag.getList("seasons", 8);
        CompoundTag stringTag = new CompoundTag();
        for(Season s : seasons) {
            stringTag.putString(s.getKey(), s.getKey());
            listTag.add(stringTag);
        }
        tag.put("seasons", listTag);
        itemStack.setTag(tag);
        return itemStack;
    }

}
