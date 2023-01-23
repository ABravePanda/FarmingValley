package com.tompkins_development.forge.farming_valley.keybinds;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.ClientRegistry;

import javax.swing.text.JTextComponent;

public class Keybinds {

    public static final KeyMapping KEY_GUI = new KeyMapping("key.farmingvalley.gui", 5, "category.farmingvalley.gui");
    public static void register()
    {
        ClientRegistry.registerKeyBinding(KEY_GUI);
    }
}
