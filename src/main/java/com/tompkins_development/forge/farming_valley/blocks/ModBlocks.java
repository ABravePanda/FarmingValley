package com.tompkins_development.forge.farming_valley.blocks;

import com.tompkins_development.forge.farming_valley.FarmingValleyMod;
import com.tompkins_development.forge.farming_valley.blocks.custom.ShippingCrateBlock;
import com.tompkins_development.forge.farming_valley.blocks.plants.CauliflowerPlantBlock;
import com.tompkins_development.forge.farming_valley.blocks.plants.GarlicPlantBlock;
import com.tompkins_development.forge.farming_valley.blocks.plants.ParsnipPlantBlock;
import com.tompkins_development.forge.farming_valley.items.ModItems;
import com.tompkins_development.forge.farming_valley.items.tabs.ModCreativeModeTabs;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, FarmingValleyMod.MOD_ID);

    //Blocks
    public static final RegistryObject<Block> FARM_BLOCK = registerBlock("farm_block",
            () -> new ModFarmBlock(BlockBehaviour.Properties.of(Material.DIRT).strength(2f).randomTicks()), null);


    //Plants
    public static final RegistryObject<Block> PARSNIP_PLANT = registerBlockWithoutItem("parsnip_plant",
            () -> new ParsnipPlantBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).strength(0.1f).noOcclusion()));
    public static final RegistryObject<Block> GARLIC_PLANT = registerBlockWithoutItem("garlic_plant",
            () -> new GarlicPlantBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).strength(0.1f).noOcclusion()));
    public static final RegistryObject<Block> CAULIFLOWER_PLANT = registerBlockWithoutItem("cauliflower_plant",
            () -> new CauliflowerPlantBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).strength(0.1f).noOcclusion()));
    public static final RegistryObject<Block> SHIPPNG_CRATE = registerBlock("shipping_crate",
            () -> new ShippingCrateBlock(BlockBehaviour.Properties.copy(Blocks.CHEST).strength(0.1f).noOcclusion()), ModCreativeModeTabs.FARM_BLOCK_TAB);



    private static <T extends Block>RegistryObject<T> registerBlockWithoutItem(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        return toReturn;
    }

    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

}
