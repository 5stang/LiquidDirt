package com.ace5852.liquidDirt;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemDirtBucket extends ItemBucket
{

    public ItemDirtBucket(Block dirtBlock)
    {
        super(dirtBlock);
        setCreativeTab(CreativeTabs.tabMisc);
        setMaxStackSize(1);
        setContainerItem(Items.bucket);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister i)
    {
        itemIcon = i.registerIcon("liquiddirt:bucket");
    }

}
