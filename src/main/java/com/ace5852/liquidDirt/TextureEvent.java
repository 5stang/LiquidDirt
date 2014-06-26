package com.ace5852.liquidDirt;


import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.client.event.TextureStitchEvent;

public class TextureEvent
{
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void textureFix(TextureStitchEvent.Post e)
    {
        if (e.map.getTextureType() == 0)
        {
            LiquidDirt.liquidDirt.setIcons(LiquidDirt.mud.getBlockTextureFromSide(0));
        }
    }
}
