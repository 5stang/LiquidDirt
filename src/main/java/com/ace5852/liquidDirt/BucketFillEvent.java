package com.ace5852.liquidDirt;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.FillBucketEvent;

public class BucketFillEvent
{
    @SubscribeEvent
    public void onBucketFill(FillBucketEvent e)
    {
        if (e.world.getBlock(e.target.blockX,e.target.blockY, e.target.blockZ) instanceof BlockLiquidDirt && e.world.getBlockMetadata(e.target.blockX,e.target.blockY, e.target.blockZ) == 0)
        {
            e.world.removeTileEntity(e.target.blockX,e.target.blockY, e.target.blockZ);
            e.world.setBlockToAir(e.target.blockX,e.target.blockY, e.target.blockZ);
            e.result = new ItemStack(LiquidDirt.mudBucket);
            e.setResult(Event.Result.ALLOW);
        }
    }
}
