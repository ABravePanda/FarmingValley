package com.tompkins_development.forge.farming_valley.client;

import com.tompkins_development.forge.farming_valley.client.overlay.season.FallOverlay;
import com.tompkins_development.forge.farming_valley.client.overlay.season.SpringOverlay;
import com.tompkins_development.forge.farming_valley.client.overlay.season.SummerOverlay;
import com.tompkins_development.forge.farming_valley.client.overlay.season.WinterOverlay;
import net.minecraftforge.client.gui.IIngameOverlay;
import net.minecraftforge.client.gui.OverlayRegistry;

public class ModOverlays {

    public static final IIngameOverlay SPRING_OVERLAY = OverlayRegistry.registerOverlayTop("spring_overlay", new SpringOverlay());
    public static final IIngameOverlay SUMMER_OVERLAY = OverlayRegistry.registerOverlayTop("summer_overlay", new SummerOverlay());
    public static final IIngameOverlay FALL_OVERLAY = OverlayRegistry.registerOverlayTop("fall_overlay", new FallOverlay());
    public static final IIngameOverlay WINTER_OVERLAY = OverlayRegistry.registerOverlayTop("winter_overlay", new WinterOverlay());

    public static final IIngameOverlay COIN_OVERLAY = OverlayRegistry.registerOverlayTop("coin_overlay", new CoinOverlay());

    public static void register() {
    }

    public static void disableSeasonOverlays() {
        OverlayRegistry.enableOverlay(SPRING_OVERLAY, false);
        OverlayRegistry.enableOverlay(SUMMER_OVERLAY, false);
        OverlayRegistry.enableOverlay(FALL_OVERLAY, false);
        OverlayRegistry.enableOverlay(WINTER_OVERLAY, false);
    }
}
