package com.tompkins_development.forge.farming_valley.events.custom;

import com.tompkins_development.forge.farming_valley.capabilities.crates.ShippingCrateCapabilityProvider;
import com.tompkins_development.forge.farming_valley.enums.ItemValue;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

@Cancelable
public class ShippingCrateSellEvent extends Event {

    ItemStack item;
    Player player;

    public ShippingCrateSellEvent(ItemStack item, Player player) {
        this.item = item;
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public ItemStack getItem() {
        return item;
    }

    public int getValue() {
        return calculateValue();
    }

    public void fire() {
        addToBalance();
        removeItem();
    }

    public int calculateValue() {
        for(ItemValue value : ItemValue.values()) {
            if(value.getItem() == item.getItem()) {
                return value.getValue();
            }
        }
        return -1;
    }

    public void addToBalance() {
        int value = calculateValue();
        if(value == -1) {
            this.setCanceled(true);
            return;
        }
        player.getCapability(ShippingCrateCapabilityProvider.SHIPPING_CRATE).ifPresent(crateProvider -> {
            crateProvider.addValue(value);
        });
    }

    public void removeItem() {
        item.shrink(1);
    }
}
