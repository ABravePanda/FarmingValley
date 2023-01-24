package com.tompkins_development.forge.farming_valley.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.tompkins_development.forge.farming_valley.FarmingValleyMod;
import com.tompkins_development.forge.farming_valley.entity.custom.JohnEntity;
import com.tompkins_development.forge.farming_valley.entity.custom.PierreEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class PierreRenderer extends GeoEntityRenderer<PierreEntity> {

    public PierreRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new PierreModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public ResourceLocation getTextureLocation(PierreEntity object) {
        return new ResourceLocation(FarmingValleyMod.MOD_ID, "textures/entity/pierre/pierre.png");
    }

    @Override
    public RenderType getRenderType(PierreEntity animatable, float partialTick, PoseStack poseStack, @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, int packedLight, ResourceLocation texture) {
        poseStack.scale(1f, 1f, 1f);
        return super.getRenderType(animatable, partialTick, poseStack, bufferSource, buffer, packedLight, texture);
    }
}
