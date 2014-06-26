package com.ace5852.liquidDirt;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigHandler
{

    public static void init(File file)
    {
        Configuration config = new Configuration(file);

        config.load();

        LiquidDirt.causesSlowness = config.get("Effects", "Slowness", true).getBoolean(true);
        LiquidDirt.causesBlindness = config.get("Effects", "Blindness", false).getBoolean(false);
        LiquidDirt.enableCrafting = config.get("Crafting", "crafting", true).getBoolean(true);

        config.save();
    }
}
