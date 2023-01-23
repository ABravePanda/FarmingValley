package com.tompkins_development.forge.farming_valley.events;

import com.mojang.blaze3d.vertex.PoseStack;
import com.tompkins_development.forge.farming_valley.FarmingValleyMod;
import com.tompkins_development.forge.farming_valley.blocks.ModBlocks;
import com.tompkins_development.forge.farming_valley.capabilities.coins.CoinsCapabilityProvider;
import com.tompkins_development.forge.farming_valley.capabilities.season.SeasonCapabilityProvider;
import com.tompkins_development.forge.farming_valley.capabilities.season.SeasonInstance;
import com.tompkins_development.forge.farming_valley.capabilities.sleeping.SleepingCapabilityProvider;
import com.tompkins_development.forge.farming_valley.enums.Season;
import com.tompkins_development.forge.farming_valley.events.custom.PlayersAwakeEvent;
import com.tompkins_development.forge.farming_valley.keybinds.Keybinds;
import com.tompkins_development.forge.farming_valley.sounds.ModSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.*;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

import java.awt.*;

public class BlockPlaceModEvent {

    @Mod.EventBusSubscriber(modid = FarmingValleyMod.MOD_ID)
    public static class ForgeEvents {

//        @SubscribeEvent
//        public static void onPlaceBlockEvent(BlockEvent.EntityPlaceEvent event) {
//            if (!(event.getEntity() instanceof Player)) return;
//
//            Player player = (Player) event.getEntity();
//            BlockState state = event.getState();
//            ItemStack itemStack = player.getMainHandItem();
//            Item item = itemStack.getItem();
//
//            if (!(state.getBlock() instanceof IPlantable)) return;
//
//            if (!itemStack.hasTag()) {
//                event.setCanceled(true);
//                return;
//            }
//
//            if (itemStack.getTag().getBoolean(FarmingValleyMod.MOD_ID + ".seed")) return;
//            event.setCanceled(true);
//        }

//        @SubscribeEvent
//        public static void onBlockRightClickEvent(PlayerInteractEvent.RightClickBlock event) {
//            if (event.getHand() != InteractionHand.MAIN_HAND) return;
//            if (event.getSide() != LogicalSide.SERVER) return;
//
//            Player player = event.getPlayer();
//            BlockPos pos = event.getHitVec().getBlockPos();
//            BlockState state = player.getLevel().getBlockState(pos);
//            ItemStack itemStack = player.getMainHandItem();
//            Item item = itemStack.getItem();
//
//            if (!(state.getBlock() instanceof IPlantable)) return;
//            if (item == Items.AIR) return;
//
//            CompoundTag tag = new CompoundTag();
//            tag.putBoolean(FarmingValleyMod.MOD_ID + ".seed", true);
//            itemStack.setTag(tag);
//            //net.minecraftforge.event.entity.player.
//
//        }

        @SubscribeEvent
        public static void onUseHoeEvent(UseHoeEvent event) {
            BlockPos pos = event.getContext().getClickedPos();
            if(event.getEntity().getLevel().isClientSide()) return;
            ServerLevel level = (ServerLevel) event.getEntity().getLevel();
            BlockState state = level.getBlockState(pos);
            if(state.getBlock() == Blocks.DIRT || state.getBlock() == Blocks.GRASS_BLOCK) {
                level.setBlock(pos, ModBlocks.FARM_BLOCK.get().defaultBlockState(), 0);
            }
        }

//        @SubscribeEvent
//        public static void onSave(WorldEvent.Save event) {
//            ServerLevel serverLevel = event.getWorld().getServer().overworld().getLevel();
//            SeasonAndDay seasonAndDay = new SeasonAndDay();
//            seasonAndDay.setDay(5);
//            seasonAndDay.setSeason(Season.FALL);
//            seasonAndDay.save(new CompoundTag());
//            seasonAndDay.compute(serverLevel.getDataStorage());
//        }

        @SubscribeEvent
        public static void onWorldTick(TickEvent.WorldTickEvent event) {
            if(event.side == LogicalSide.CLIENT) return;
            ServerLevel level = (ServerLevel) event.world;
            SeasonInstance.updateTime(level);
        }


//
        @SubscribeEvent
        public static void onKeyBind(InputEvent.KeyInputEvent event) {
            if(Keybinds.KEY_GUI.isDown()) {
                //Minecraft.getInstance().setScreen(new CustomScreen(new TextComponent("Shit")));
                //Minecraft.getInstance().player.startSleepInBed(Minecraft.getInstance().player.getSleepingPos().get());
                Minecraft.getInstance().player.getLevel().getCapability(CoinsCapabilityProvider.COINS).ifPresent(coinProvider -> {
                    coinProvider.setBalance(coinProvider.getBalance()+1);
                });
            }
        }

        @SubscribeEvent
        public static void onRender(RenderGameOverlayEvent event) {
            Minecraft.getInstance().font.draw(new PoseStack(), "Season: " + SeasonInstance.getSeason(), 2, 3, Color.white.getRGB());
            Minecraft.getInstance().font.draw(new PoseStack(), "Day: " + SeasonInstance.day, 2, 12, Color.white.getRGB());
            Minecraft.getInstance().font.draw(new PoseStack(), "Time: " + SeasonInstance.time, 2, 21, Color.white.getRGB());
            Minecraft.getInstance().player.getLevel().getCapability(CoinsCapabilityProvider.COINS).ifPresent(coinProvider -> {
                Minecraft.getInstance().font.draw(new PoseStack(), "Coins: " + coinProvider.getBalance(), 2, 30, Color.white.getRGB());
            });
        }

        @SubscribeEvent
        public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
            if(event.getPlayer().getLevel().isClientSide()) return;
            event.getPlayer().getLevel().getCapability(SeasonCapabilityProvider.SEASON_AND_DAY).ifPresent(seasonProvider -> {
                SeasonInstance.season = seasonProvider.getSeason();
                SeasonInstance.day = seasonProvider.getDay();
            });
        }


        //
        @SubscribeEvent
        public static void onSleep(SleepingTimeCheckEvent event) {
            event.setResult(Event.Result.ALLOW);
            //SeasonCapabilityImplementation
        }

        @SubscribeEvent
        public static void onSleepInBed(PlayerSleepInBedEvent event) {
            if(event.getPlayer().getLevel().isClientSide()) return;
            event.getPlayer().getLevel().getCapability(SleepingCapabilityProvider.SLEEPING).ifPresent(sleepingProvider -> {
                sleepingProvider.setSleeping(sleepingProvider.getSleeping() + 1);
            });
        }
//
        @SubscribeEvent
        public static void onPlayerSleepAwake(PlayerWakeUpEvent event) {
//            Player player = event.getPlayer();
//            Level level = player.getLevel();
//
//            if(level.isClientSide()) {
//                player.sendMessage(new TextComponent("Debug Text"), null);
//            } else {
//                ServerLevel serverLevel = (ServerLevel) level;
//                serverLevel.setDayTime(23500);
//                FarmingValleyMod.days++;
//            }
            if(event.getPlayer().level.isClientSide()) return;
            event.getPlayer().getLevel().getCapability(SleepingCapabilityProvider.SLEEPING).ifPresent(sleepingProvider -> {
                if(sleepingProvider.getSleeping() >= event.getPlayer().getServer().getPlayerCount()) {
                    MinecraftForge.EVENT_BUS.post(new PlayersAwakeEvent(event.getPlayer().getLevel()));
                    if(sleepingProvider.getSleeping() != 0)
                        sleepingProvider.setSleeping(sleepingProvider.getSleeping()-1);
                } else {
                    if(sleepingProvider.getSleeping() != 0)
                        sleepingProvider.setSleeping(sleepingProvider.getSleeping()-1);
                }
            });
        }

        @SubscribeEvent
        public static void onPlayerAwake(PlayersAwakeEvent event) {
            if(event.getLevel().isClientSide()) return;
            event.getLevel().getCapability(SeasonCapabilityProvider.SEASON_AND_DAY).ifPresent(seasonProvider -> {
                if(seasonProvider.getSeason() == null)
                    seasonProvider.setSeason(Season.SPRING);
                if(seasonProvider.getDay() >= seasonProvider.getSeason().getDays()) {
                    seasonProvider.setDay(1);
                    seasonProvider.setSeason(Season.getNextSeason(seasonProvider.getSeason()));
                    SeasonInstance.season = seasonProvider.getSeason();
                    SeasonInstance.day = seasonProvider.getDay();
                } else {
                    seasonProvider.setDay(seasonProvider.getDay()+1);
                    SeasonInstance.day = seasonProvider.getDay();
                }

                Player p = Minecraft.getInstance().player;
                Minecraft.getInstance().level.playSound(p, p.getOnPos(), ModSounds.ROOSTER.get(), SoundSource.AMBIENT, 1f, 1f);
                p.setHealth(20f);
                p.getFoodData().setFoodLevel(20);

                if(!event.getLevel().isClientSide()) {
                    ServerLevel serverLevel = (ServerLevel) event.getLevel();
                    serverLevel.setDayTime(23500);
                    FarmingValleyMod.days++;
                }
                //event.getPlayer().sendMessage(new TextComponent("Season: " + seasonProvider.getSeason() + " Day:" + seasonProvider.getDay()), event.getPlayer().getUUID());
            });
        }
//
        @SubscribeEvent
        public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
            if(event.side != LogicalSide.SERVER) return;
            ServerPlayer player = (ServerPlayer) event.player;
            ServerLevel level = (ServerLevel) event.player.getLevel();

            if(level.dayTime() >= 18000 && level.dayTime() < 23500) {
                BlockPos pos = player.getRespawnPosition();
                player.connection.teleport(pos.getX(), pos.getY(), pos.getZ(),0, 0);
                player.startSleepInBed(pos);
                //player.startSleeping(pos);
            }
        }




//        @SubscribeEvent
//        public static void onRightClickEvent(PlayerInteractEvent.RightClickBlock event) {
//            Player player = event.getPlayer();
//            BlockPos pos = event.getPos();
//            Level level = event.getEntity().getLevel();
//            BlockState state = level.getBlockState(pos);
//
//            if(event.getHand() != InteractionHand.MAIN_HAND) return;
//            if(event.getSide() != LogicalSide.SERVER) return;
//
//            if(state.getBlock() == ModBlocks.FARM_BLOCK.get()) {
//                if(player.getMainHandItem().getItem() instanceof ItemNameBlockItem) {
//                    player.sendMessage(new TextComponent("Test"), null);
//                    level.setBlock(pos.above(), CropBlock.byItem(player.getMainHandItem().getItem()).defaultBlockState(), 0);
//                }
//            } else
//                player.sendMessage(new TextComponent(state.getBlock() + ""), null);
//        }
    }
}
