package com.tompkins_development.forge.farming_valley.entity.client;

import com.tompkins_development.forge.farming_valley.FarmingValleyMod;
import com.tompkins_development.forge.farming_valley.entity.custom.JohnEntity;
import com.tompkins_development.forge.farming_valley.entity.custom.PierreEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class PierreModel extends AnimatedGeoModel<PierreEntity> {
    @Override
    public ResourceLocation getModelLocation(PierreEntity object) {
        return new ResourceLocation(FarmingValleyMod.MOD_ID, "geo/pierre.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(PierreEntity object) {
        return new ResourceLocation(FarmingValleyMod.MOD_ID, "textures/entity/pierre/pierre.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(PierreEntity animatable) {
        return new ResourceLocation(FarmingValleyMod.MOD_ID, "animations/pierre.animation.json");
    }
}
