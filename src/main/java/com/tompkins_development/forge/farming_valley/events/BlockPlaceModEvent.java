package com.tompkins_development.forge.farming_valley.events;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.tompkins_development.forge.farming_valley.FarmingValleyMod;
import com.tompkins_development.forge.farming_valley.blocks.ModBlocks;
import com.tompkins_development.forge.farming_valley.capabilities.coin.ClientBalanceData;
import com.tompkins_development.forge.farming_valley.capabilities.coin.Coins;
import com.tompkins_development.forge.farming_valley.capabilities.coin.CoinsCapabilityProvider;
import com.tompkins_development.forge.farming_valley.capabilities.crates.ShippingCrateCapabilityProvider;
import com.tompkins_development.forge.farming_valley.capabilities.season.SeasonCapabilityProvider;
import com.tompkins_development.forge.farming_valley.capabilities.season.SeasonInstance;
import com.tompkins_development.forge.farming_valley.capabilities.sleeping.SleepingCapabilityProvider;
import com.tompkins_development.forge.farming_valley.client.ModOverlays;
import com.tompkins_development.forge.farming_valley.enums.Season;
import com.tompkins_development.forge.farming_valley.events.custom.DateChangeEvent;
import com.tompkins_development.forge.farming_valley.events.custom.PlayersAwakeEvent;
import com.tompkins_development.forge.farming_valley.events.custom.ShippingCrateSellEvent;
import com.tompkins_development.forge.farming_valley.items.SeedItem;
import com.tompkins_development.forge.farming_valley.keybinds.Keybinds;
import com.tompkins_development.forge.farming_valley.networking.ModMessages;
import com.tompkins_development.forge.farming_valley.networking.packet.CoinsS2CPacket;
import com.tompkins_development.forge.farming_valley.sounds.ModSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.gui.GuiUtils;
import net.minecraftforge.client.gui.OverlayRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.*;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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

        @SubscribeEvent
        public static void onBlockRightClickEvent(PlayerInteractEvent.RightClickBlock event) {
            if (event.getHand() != InteractionHand.MAIN_HAND) return;
            if (event.getSide() != LogicalSide.SERVER) return;

            Player player = event.getPlayer();
            BlockPos pos = event.getHitVec().getBlockPos();
            BlockState state = player.getLevel().getBlockState(pos);
            ItemStack itemStack = player.getMainHandItem();
            Item item = itemStack.getItem();

            if (state.getBlock() == ModBlocks.SHIPPNG_CRATE.get()) {
                ShippingCrateSellEvent shippingCrateSellEvent = new ShippingCrateSellEvent(itemStack, player);
                MinecraftForge.EVENT_BUS.post(new ShippingCrateSellEvent(itemStack, player));
                event.setCanceled(!shippingCrateSellEvent.isCanceled());
            }

//            if(state.getBlock() instanceof )
//
//            if (!(state.getBlock() instanceof IPlantable)) return;
//            if (item == Items.AIR) return;
//
//            CompoundTag tag = new CompoundTag();
//            tag.putBoolean(FarmingValleyMod.MOD_ID + ".seed", true);
//            itemStack.setTag(tag);
            //net.minecraftforge.event.entity.player.

        }

        @SubscribeEvent
        public static void onShippingCrateUsed(ShippingCrateSellEvent event) {
            event.fire();
        }

        @SubscribeEvent
        public static void onUseHoeEvent(UseHoeEvent event) {
            BlockPos pos = event.getContext().getClickedPos();
            if (event.getEntity().getLevel().isClientSide()) return;
            ServerLevel level = (ServerLevel) event.getEntity().getLevel();
            BlockState state = level.getBlockState(pos);
            if (state.getBlock() == Blocks.DIRT || state.getBlock() == Blocks.GRASS_BLOCK) {
                level.setBlock(pos, ModBlocks.FARM_BLOCK.get().defaultBlockState(), 0);
            }
            event.getPlayer().getCapability(CoinsCapabilityProvider.COINS).ifPresent(coinProvider -> {
                coinProvider.setBalance(coinProvider.getBalance() + 1);
            });

            event.getPlayer().getCapability(ShippingCrateCapabilityProvider.SHIPPING_CRATE).ifPresent(crateProvider -> {
                crateProvider.addValue(3);
                crateProvider.addValue(2);
                crateProvider.addValue(1);
            });
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
            if (event.side == LogicalSide.CLIENT) return;
            ServerLevel level = (ServerLevel) event.world;
            SeasonInstance.updateTime(level);

            level.players().forEach(player -> {
                int balance = player.getCapability(CoinsCapabilityProvider.COINS)
                        .map(Coins::getBalance)
                        .orElse(-1);
                ModMessages.sendToPlayer(new CoinsS2CPacket(balance), player);
            });

        }

        @SubscribeEvent
        public static void onPlayerCloned(PlayerEvent.Clone event) {
            if (!event.isWasDeath()) return;
            event.getOriginal().getCapability(CoinsCapabilityProvider.COINS).ifPresent(oldStore -> {
                event.getOriginal().getCapability(CoinsCapabilityProvider.COINS).ifPresent(newStore -> {
                    newStore.setBalance(oldStore.getBalance());
                });
            });
        }


        //
        @SubscribeEvent
        public static void onKeyBind(InputEvent.KeyInputEvent event) {
            if (Keybinds.KEY_GUI.isDown()) {
                //Minecraft.getInstance().setScreen(new CustomScreen(new TextComponent("Shit")));
                //Minecraft.getInstance().player.startSleepInBed(Minecraft.getInstance().player.getSleepingPos().get());
            }
        }

        @SubscribeEvent
        public static void onRender(RenderGameOverlayEvent.Post event) {
            Minecraft minecraft = Minecraft.getInstance();
            //event.getWindow().scal
            int width = event.getWindow().getGuiScaledWidth();
            int height = event.getWindow().getGuiScaledHeight();
            //System.out.println("Width: " + width + " Height: " + height);

            //Season & Day
            PoseStack poseStack = new PoseStack();
            poseStack.scale(2, 2, 2);

            minecraft.font.draw(poseStack, SeasonInstance.getSeason(), 30, 5, Color.HSBtoRGB(125, 100, 100));

            PoseStack poseStack2 = new PoseStack();
            poseStack2.scale(1.2f, 1.2f, 1.2f);

            minecraft.font.draw(poseStack2, "Day " + SeasonInstance.day, 50, 25, Color.white.getRGB());
            minecraft.font.draw(poseStack2, SeasonInstance.time, 50, 35, Color.white.getRGB());

            //Balance
            minecraft.font.draw(new PoseStack(), "" + ClientBalanceData.getBalance(), width - 65, 16, Color.YELLOW.getRGB());
        }


        @SubscribeEvent
        public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
            if (event.getPlayer().getLevel().isClientSide()) return;
            event.getPlayer().getLevel().getCapability(SeasonCapabilityProvider.SEASON_AND_DAY).ifPresent(seasonProvider -> {
                SeasonInstance.season = seasonProvider.getSeason();
                SeasonInstance.day = seasonProvider.getDay();
                ModOverlays.disableSeasonOverlays();
                Season.switchOverlay(seasonProvider.getSeason());
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
            if (event.getPlayer().getLevel().isClientSide()) return;
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
            if (event.getPlayer().level.isClientSide()) return;
            event.getPlayer().getLevel().getCapability(SleepingCapabilityProvider.SLEEPING).ifPresent(sleepingProvider -> {
                if (sleepingProvider.getSleeping() >= event.getPlayer().getServer().getPlayerCount()) {
                    MinecraftForge.EVENT_BUS.post(new PlayersAwakeEvent(event.getPlayer().getLevel()));
                    if (sleepingProvider.getSleeping() != 0)
                        sleepingProvider.setSleeping(sleepingProvider.getSleeping() - 1);
                } else {
                    if (sleepingProvider.getSleeping() != 0)
                        sleepingProvider.setSleeping(sleepingProvider.getSleeping() - 1);
                }
            });
        }

        @SubscribeEvent
        public static void onPlayerAwake(PlayersAwakeEvent event) {
            if (event.getLevel().isClientSide()) return;
            event.getLevel().getCapability(SeasonCapabilityProvider.SEASON_AND_DAY).ifPresent(seasonProvider -> {
                if (seasonProvider.getSeason() == null) {
                    seasonProvider.setSeason(Season.SPRING);
                }
                if (seasonProvider.getDay() >= seasonProvider.getSeason().getDays()) {
                    seasonProvider.setDay(1);
                    Season currentSeason = seasonProvider.getSeason();
                    seasonProvider.setSeason(Season.getNextSeason(seasonProvider.getSeason()));
                    SeasonInstance.season = seasonProvider.getSeason();
                    SeasonInstance.day = seasonProvider.getDay();
                    MinecraftForge.EVENT_BUS.post(new DateChangeEvent(event.getLevel(), 1, 1, seasonProvider.getSeason(), currentSeason));
                } else {
                    seasonProvider.setDay(seasonProvider.getDay() + 1);
                    SeasonInstance.day = seasonProvider.getDay();
                    MinecraftForge.EVENT_BUS.post(new DateChangeEvent(event.getLevel(), seasonProvider.getDay() - 1, seasonProvider.getDay(), seasonProvider.getSeason(), seasonProvider.getSeason()));
                }
                //event.getPlayer().sendMessage(new TextComponent("Season: " + seasonProvider.getSeason() + " Day:" + seasonProvider.getDay()), event.getPlayer().getUUID());
            });


            Player p = Minecraft.getInstance().player;
            Minecraft.getInstance().level.playSound(p, p.getOnPos(), ModSounds.ROOSTER.get(), SoundSource.AMBIENT, 1f, 1f);
            p.setHealth(20f);
            p.getFoodData().setFoodLevel(20);

            if (!event.getLevel().isClientSide()) {
                ServerLevel serverLevel = (ServerLevel) event.getLevel();
                serverLevel.setDayTime(23500);
                FarmingValleyMod.days++;
            }

            for (Player player : event.getLevel().players()) {
                player.getCapability(ShippingCrateCapabilityProvider.SHIPPING_CRATE).ifPresent(crateProvider -> {
                    int value = crateProvider.getValue();
                    crateProvider.setValue(0);
                    player.getCapability(CoinsCapabilityProvider.COINS).ifPresent(coinProvider -> {
                        coinProvider.setBalance(coinProvider.getBalance() + value);
                        player.sendMessage(new TextComponent("Sold For Value of " + value), null);
                    });
                });
            }
        }

        //
        @SubscribeEvent
        public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
            if (event.side != LogicalSide.SERVER) return;
            ServerPlayer player = (ServerPlayer) event.player;
            ServerLevel level = (ServerLevel) event.player.getLevel();

            if (level.dayTime() >= 18000 && level.dayTime() < 23500) {
                BlockPos pos = player.getRespawnPosition();
                player.connection.teleport(pos.getX(), pos.getY(), pos.getZ(), 0, 0);
                player.startSleepInBed(pos);
                //player.startSleeping(pos);
            }
        }

        @SubscribeEvent
        public static void dateChangeEvent(DateChangeEvent event) {
            if (event.getNewDay() % 3 == 0) {
                System.out.println("Weather");
                event.getLevel().getServer().overworld().setWeatherParameters(0, 5500, true, false);
            } else {
                event.getLevel().getServer().overworld().setWeatherParameters(5500, 0, false, false);
            }

            if (event.didSeasonChange()) {
                Season.switchOverlay(event.getNewSeason());
            }
        }


        @SubscribeEvent
        public static void onRightClickEvent(PlayerInteractEvent.RightClickBlock event) {
            Player player = event.getPlayer();
            BlockPos pos = event.getPos();
            Level level = event.getEntity().getLevel();
            BlockState state = level.getBlockState(pos);
            List<Season> seasonList = new ArrayList();

            if (event.getHand() != InteractionHand.MAIN_HAND) return;
            if (event.getSide() != LogicalSide.SERVER) return;

            if (state.getBlock() == ModBlocks.FARM_BLOCK.get()) {
                if (player.getMainHandItem().getItem() instanceof SeedItem) {
                    ItemStack item = player.getMainHandItem().getItem().getDefaultInstance();
                    CompoundTag tag = item.getTag();
                    ListTag listTag = tag.getList("seasons", 10);
                    for (int i = 0; i < listTag.size(); i++) {
                        CompoundTag compoundTag = (CompoundTag) listTag.get(i);
                        for (String s : compoundTag.getAllKeys())
                            for (Season season : Season.values())
                                if (season.getKey().equalsIgnoreCase(s)) seasonList.add(season);
                    }
//                    player.sendMessage(new TextComponent("Test"), null);
//                    level.setBlock(pos.above(), CropBlock.byItem(player.getMainHandItem().getItem()).defaultBlockState(), 0);
                    if(!seasonList.contains(SeasonInstance.season)) {
                        event.setCanceled(true);
                        System.out.println("Not in season");
                    }
                }
            }
        }
    }
}
