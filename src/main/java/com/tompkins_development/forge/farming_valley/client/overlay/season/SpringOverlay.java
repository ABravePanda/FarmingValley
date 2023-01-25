package com.tompkins_development.forge.farming_valley.client.overlay.season;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.tompkins_development.forge.farming_valley.FarmingValleyMod;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.client.gui.GuiUtils;
import net.minecraftforge.client.gui.IIngameOverlay;

public class SpringOverlay implements IIngameOverlay {

    protected static final ResourceLocation FLOWER_LOCATION = new ResourceLocation(FarmingValleyMod.MOD_ID, "textures/gui/spring_overlay.png");
    protected static final Float opacity = 0.7F;

    @Override
    public void render(ForgeIngameGui gui, PoseStack poseStack, float partialTick, int width, int height) {
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, opacity);
        RenderSystem.setShaderTexture(0, FLOWER_LOCATION);
        GuiUtils.drawTexturedModalRect(poseStack, 1, 1, 0, 0, 256, 256, 0);
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    }

//    private static final IIngameOverlay HUD_OVERLAY = new IIngameOverlay() {
//        @Override
//        public void render(ForgeIngameGui gui, PoseStack poseStack, float partialTick, int width, int height) {
//            int x = width/2;
//            int y = height;
//
//            RenderSystem.setShader(GameRenderer::getPositionTexShader);
//            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
//            RenderSystem.setShaderTexture(0, FLOWER_LOCATION);
//            GuiComponent.blit(poseStack, x, y, 0, 0, 256, 256);
//        }
//    };
//
////            OverlayRegistry.registerOverlayTop("FlowerOverlay", (gui, mStack, partialTicks, screenWidth, screenHeight) -> {
////                Minecraft minecraft = Minecraft.getInstance();
////                ResourceLocation resourceLocation = new ResourceLocation(FarmingValleyMod.MOD_ID, "textures/gui/flower_gui.png");
////                if (minecraft.gameMode == null) return;
////                if (minecraft.level == null) return;
////                TextureManager tm = minecraft.getTextureManager();
////                gui.setupOverlayRenderState(true, false, resourceLocation);
////                GuiUtils.drawTexturedModalRect(new PoseStack(), 0, 0, 0, 0, 256, 256, 0);
////            });

}
