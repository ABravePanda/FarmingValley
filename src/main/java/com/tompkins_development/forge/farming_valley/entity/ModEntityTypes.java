package com.tompkins_development.forge.farming_valley.entity;

import com.tompkins_development.forge.farming_valley.FarmingValleyMod;
import com.tompkins_development.forge.farming_valley.entity.custom.JohnEntity;
import com.tompkins_development.forge.farming_valley.entity.custom.PierreEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityTypes {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, FarmingValleyMod.MOD_ID);

        public static final RegistryObject<EntityType<JohnEntity>> JOHN = ENTITY_TYPES.register("john",
                () -> EntityType.Builder.of(JohnEntity::new, MobCategory.CREATURE)
                        .sized(1f, 1f)
                        .build(new ResourceLocation(FarmingValleyMod.MOD_ID, "john").toString()));

    public static final RegistryObject<EntityType<PierreEntity>> PIERRE = ENTITY_TYPES.register("pierre",
            () -> EntityType.Builder.of(PierreEntity::new, MobCategory.CREATURE)
                    .sized(1f, 1f)
                    .build(new ResourceLocation(FarmingValleyMod.MOD_ID, "pierre").toString()));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
