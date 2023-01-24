package com.tompkins_development.forge.farming_valley.entity.client;

import com.tompkins_development.forge.farming_valley.FarmingValleyMod;
import com.tompkins_development.forge.farming_valley.entity.custom.JohnEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class JohnModel extends AnimatedGeoModel<JohnEntity> {
    @Override
    public ResourceLocation getModelLocation(JohnEntity object) {
        return new ResourceLocation(FarmingValleyMod.MOD_ID, "geo/john.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(JohnEntity object) {
        return new ResourceLocation(FarmingValleyMod.MOD_ID, "textures/entity/john/john.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(JohnEntity animatable) {
        return new ResourceLocation(FarmingValleyMod.MOD_ID, "animations/john.animation.json");
    }
}
