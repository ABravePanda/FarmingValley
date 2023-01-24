package com.tompkins_development.forge.farming_valley.blocks.custom;

import com.tompkins_development.forge.farming_valley.capabilities.crates.ShippingCrateCapabilityProvider;
import com.tompkins_development.forge.farming_valley.enums.ItemValue;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.xml.validation.Validator;
import java.util.Random;

public class ShippingCrateBlock extends Block {

    private static final VoxelShape SHAPE = Block.box(0,0,0,12,12,12);

    public ShippingCrateBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }
}
