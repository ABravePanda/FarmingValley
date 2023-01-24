package com.tompkins_development.forge.farming_valley.enums;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public enum ItemValue {
    STONE(Items.STONE, 5);

    private Item item;
    private int value;

    ItemValue(Item item, int value) {
        this.item = item;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Item getItem() {
        return item;
    }
}
