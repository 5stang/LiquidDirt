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
        LiquidDirt.enableCraftingIce = config.get("Crafting", "iceblock", true).getBoolean(true);
        LiquidDirt.enableCraftingWater = config.get("Crafting", "waterbottle", true).getBoolean(true);
        LiquidDirt.enableCraftingBucket = config.get("Crafting", "waterbucket", true).getBoolean(true);

        config.save();
    }
}
