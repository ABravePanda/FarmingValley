package com.tompkins_development.forge.farming_valley.enums;

import com.tompkins_development.forge.farming_valley.client.ModOverlays;
import net.minecraftforge.client.gui.OverlayRegistry;

public enum Season {

    SPRING("SPRING", 28),
    SUMMER("SUMMER", 28),
    FALL("FALL", 28),
    WINTER("WINTER", 28);

    private String key;
    private int days;

    Season(String key, int days) {
        this.key = key;
        this.days = days;
    }

    public String getKey() {
        return key;
    }

    public int getDays() {
        return days;
    }

    public static Season getNextSeason(Season season) {
        switch (season) {
            case SPRING:
                return SUMMER;
            case SUMMER:
                return FALL;
            case FALL:
                return WINTER;
            case WINTER:
                return SPRING;
        }
        return season;
    }

    public static void switchOverlay(Season season) {
        switch (season) {
            case SPRING:
                OverlayRegistry.enableOverlay(ModOverlays.SPRING_OVERLAY, true);
                OverlayRegistry.enableOverlay(ModOverlays.SUMMER_OVERLAY, false);
                break;
            case SUMMER:
                OverlayRegistry.enableOverlay(ModOverlays.SUMMER_OVERLAY, true);
                OverlayRegistry.enableOverlay(ModOverlays.SPRING_OVERLAY, false);
                break;
            case FALL:
                OverlayRegistry.enableOverlay(ModOverlays.FALL_OVERLAY, true);
                OverlayRegistry.enableOverlay(ModOverlays.SUMMER_OVERLAY, false);
                break;
            case WINTER:
                OverlayRegistry.enableOverlay(ModOverlays.WINTER_OVERLAY, true);
                OverlayRegistry.enableOverlay(ModOverlays.FALL_OVERLAY, false);
                break;
        }
    }
}
