package com.tompkins_development.forge.farming_valley.blocks.plants;

import com.tompkins_development.forge.farming_valley.items.ModItems;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class ParsnipPlantBlock extends CropBlock {

    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;

    public ParsnipPlantBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public int getMaxAge() {
        return 3;
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return ModItems.PARSNIP_SEEDS.get();
    }
    

}
