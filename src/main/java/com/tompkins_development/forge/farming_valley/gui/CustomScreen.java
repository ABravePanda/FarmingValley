package com.tompkins_development.forge.farming_valley.gui;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.tompkins_development.forge.farming_valley.FarmingValleyMod;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.ConfirmScreen;
import net.minecraft.client.gui.screens.GenericDirtMessageScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.gui.GuiUtils;

import javax.annotation.Nullable;
import java.util.List;

public class CustomScreen extends Screen {

    static Minecraft mc = Minecraft.getInstance();
    //private static final ResourceLocation IMG_LOCATION = new ResourceLocation(FarmingValleyMod.MOD_ID,"textures/gui/diag.png");
    protected static int screenWidth;
    protected static int screenHeight;
    protected static final int imageWidth = 176;
    protected static final int imageHeight = 79;

    public CustomScreen(Component pTitle) {
        super(pTitle);
    }

    protected void init() {

    }

    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        Player player = mc.player;
        //Options settings = Minecraft.getInstance().options;
        screenWidth = mc.getWindow().getGuiScaledWidth();
        screenHeight = mc.getWindow().getGuiScaledHeight();

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        //RenderSystem.setShaderTexture(0, IMG_LOCATION);
        int x = (screenWidth - imageWidth) / 2;
        int y = (screenHeight - imageHeight) / 2;
        this.blit(pPoseStack, x, y, 0, 0, 256, 256);
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
