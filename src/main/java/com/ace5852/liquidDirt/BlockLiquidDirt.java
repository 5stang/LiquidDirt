package com.ace5852.liquidDirt;

import com.ace5852.liquidDirt.LiquidDirt;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;


public class BlockLiquidDirt extends BlockFluidClassic implements ITileEntityProvider
{
    @SideOnly(Side.CLIENT)
    protected IIcon mudStill;
    @SideOnly(Side.CLIENT)
    protected IIcon mudFlowing;


    public BlockLiquidDirt(Fluid fluid, Material material)
    {
        super(fluid, material);
        setCreativeTab(CreativeTabs.tabMisc);
//      setTickRandomly(true);
    }

    @Override
    public int getQuantaValue(IBlockAccess world, int x, int y, int z)
    {
        if (world.getTileEntity(x,y,z) != null)
        {
            TileEntityDirt ted = (TileEntityDirt) world.getTileEntity(x, y, z);
            if (!ted.getCanSpread())
            {
                return -1;
            }
        }

        return super.getQuantaValue(world,x,y,z);
    }

//    @Override
//    public void updateTick(World world, int x, int y, int z, Random rand)
//    {
//       super.updateTick(world, x, y, z, rand);
//       int meta = world.getBlockMetadata(x,y,z);
//       if (rand.nextInt(5) == 1)
//       {
//           if (meta == 10)
//           {
//               world.setBlock(x,y,z, Blocks.dirt);
//               meta = 0;
//           }
//           else
//           {
//               meta ++;
//               System.out.println(x + " : " + y + " : " + z + " : Meta " + meta);
//           }
//       }
//        world.setBlockMetadataWithNotify(x,y,z, meta, 0);
//    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        if (side == 0 || side == 1)
        {
            return mudStill;
        }
        else
        {
            return mudFlowing;
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister register)
    {
        mudStill = register.registerIcon("liquiddirt:mudStill");
        mudFlowing = register.registerIcon("liquiddirt:mudFlowing");
        LiquidDirt.liquidDirt.setIcons(mudStill, mudFlowing);
    }

    @Override
    public boolean canDisplace(IBlockAccess world, int x, int y, int z)
    {
        return super.canDisplace(world, x, y, z);
    }

    @Override
    public boolean displaceIfPossible(World world, int x, int y, int z)
    {
       return super.displaceIfPossible(world,x,y,z);
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block blk, int unknown)
    {
        super.breakBlock(world, x, y, z, blk, unknown);
        world.removeTileEntity(x, y, z);
    }

    public static void convertBlock(World world, int x, int y, int z)
    {
        world.removeTileEntity(x,y,z);
        world.setBlock(x,y,z, Blocks.dirt);
    }

    public static void setMeta(World world, int x, int y, int z)
    {
        world.setBlockMetadataWithNotify(x,y,z,0,2);
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
    {
        super.onEntityCollidedWithBlock(world, x, y, z, entity);
        if (entity instanceof EntityLivingBase)
        {
            EntityLivingBase ent = (EntityLivingBase) entity;
            if (LiquidDirt.causesSlowness)
            {
                ent.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 200, 1));
            }
            if (LiquidDirt.causesBlindness)
            {
                ent.addPotionEffect(new PotionEffect(Potion.blindness.id, 100, 0));
            }
        }
    }

    @Override
    public TileEntity createNewTileEntity(World world, int unknown)
    {
        return new TileEntityDirt();
    }
}
