package com.ace5852.liquidDirt;

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import javax.xml.transform.Result;

@Mod(modid = LiquidDirt.MODID, version = LiquidDirt.VERSION)
public class LiquidDirt
{
    public static final String MODID = "liquidDirt";
    public static final String VERSION = "1.0";

    public static Fluid liquidDirt;
    public static Block mud;
    public static Item mudBucket;

    //Some Config Variables
    public static boolean causesSlowness;
    public static boolean causesBlindness;
    public static boolean enableCraftingIce;
    public static boolean enableCraftingWater;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        ConfigHandler.init(event.getSuggestedConfigurationFile());
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code
        //System.out.println("DIRT BLOCK >> "+Blocks.dirt.getUnlocalizedName());

        liquidDirt = new Fluid("mud");
        FluidRegistry.registerFluid(liquidDirt);
        mud = new BlockLiquidDirt(liquidDirt, Material.water).setBlockName("Mud");


        GameRegistry.registerBlock(mud, MODID + "_" + mud.getUnlocalizedName());
        liquidDirt.setUnlocalizedName(mud.getUnlocalizedName());

        mudBucket = new ItemDirtBucket(mud).setUnlocalizedName("mudBucket");

        GameRegistry.registerItem(mudBucket, "mudBucket");

        System.out.println(mud.getUnlocalizedName());

        FluidContainerRegistry.registerFluidContainer(new FluidStack(liquidDirt, FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(mudBucket), new ItemStack(Items.bucket));
        MinecraftForge.EVENT_BUS.register(new BucketFillEvent());

        System.out.println(mudBucket.getUnlocalizedName());

        MinecraftForge.EVENT_BUS.register(new TextureEvent());

        //Crafting
        if (enableCraftingWater)
        {
            GameRegistry.addShapelessRecipe(new ItemStack(mudBucket, 1), new Object[]{new ItemStack(Items.potionitem, 1, 0), new ItemStack(Blocks.dirt), new ItemStack(Items.bucket)});
            cpw.mods.fml.common.FMLCommonHandler.instance().bus().register(new CraftingEventHandler());
        }
        if (enableCraftingIce)
        {
            GameRegistry.addShapelessRecipe(new ItemStack(mudBucket, 1), new Object[]{new ItemStack(Blocks.ice), new ItemStack(Blocks.dirt), new ItemStack(Items.bucket)});
        }

        GameRegistry.registerTileEntity(TileEntityDirt.class, MODID+"dirt");
    }
}