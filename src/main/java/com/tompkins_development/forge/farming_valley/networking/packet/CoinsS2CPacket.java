package com.tompkins_development.forge.farming_valley.networking.packet;

import com.mojang.blaze3d.vertex.PoseStack;
import com.tompkins_development.forge.farming_valley.capabilities.coin.ClientBalanceData;
import com.tompkins_development.forge.farming_valley.capabilities.coin.CoinsCapabilityProvider;
import com.tompkins_development.forge.farming_valley.capabilities.season.SeasonCapabilityProvider;
import com.tompkins_development.forge.farming_valley.capabilities.season.SeasonInstance;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.awt.*;
import java.util.function.Supplier;

public class CoinsS2CPacket {

    private int balance;

    public CoinsS2CPacket(int balance) {
        this.balance = balance;
    }

    public CoinsS2CPacket(FriendlyByteBuf buf) {
        this.balance = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(this.balance);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context =  supplier.get();
        context.enqueueWork(() -> {
            ClientBalanceData.set(balance);
        });
        return true;
    }
}
