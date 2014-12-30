package com.ace5852.liquidDirt;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityDirt extends TileEntity
{
    private short timeToSolidify;
    private boolean canSpread;


    public TileEntityDirt()
    {
        timeToSolidify = 200;
        canSpread = true;
    }

    public boolean getCanSpread()
    {
        return canSpread;
    }

    public void setCanSpread(boolean newCanSpread)
    {
        canSpread = newCanSpread;
    }

    @Override
    public void writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setShort("TimeLeft", timeToSolidify);
    }

    @Override
    public void updateEntity()
    {
        super.updateEntity();
        //System.out.println("Life Left:" + timeToSolidify);
        if (timeToSolidify > 0)
        {
            timeToSolidify--;
        }
        else if (timeToSolidify == 0)
        {
            BlockLiquidDirt.convertBlock(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            if (worldObj.getBlock(xCoord+1,yCoord,zCoord) instanceof BlockLiquidDirt)
            {
                BlockLiquidDirt.setMeta(worldObj,xCoord+1,yCoord,zCoord);
                TileEntityDirt ted = (TileEntityDirt) worldObj.getTileEntity(xCoord+1,yCoord,zCoord);
                if (ted != null)
                {
                    ted.setCanSpread(false);
                }
            }
            if (worldObj.getBlock(xCoord-1,yCoord,zCoord) instanceof BlockLiquidDirt)
            {
                BlockLiquidDirt.setMeta(worldObj,xCoord-1,yCoord,zCoord);
                TileEntityDirt ted = (TileEntityDirt) worldObj.getTileEntity(xCoord-1,yCoord,zCoord);
                if (ted != null)
                {
                    ted.setCanSpread(false);
                }

            }
            if (worldObj.getBlock(xCoord,yCoord,zCoord+1) instanceof BlockLiquidDirt)
            {
                BlockLiquidDirt.setMeta(worldObj,xCoord,yCoord,zCoord+1);
                TileEntityDirt ted = (TileEntityDirt) worldObj.getTileEntity(xCoord,yCoord,zCoord+1);
                if (ted != null)
                {
                    ted.setCanSpread(false);
                }
            }
            if (worldObj.getBlock(xCoord-1,yCoord,zCoord-1) instanceof BlockLiquidDirt)
            {
                BlockLiquidDirt.setMeta(worldObj,xCoord,yCoord,zCoord-1);
                TileEntityDirt ted = (TileEntityDirt) worldObj.getTileEntity(xCoord,yCoord,zCoord-1);
                if (ted != null)
                {
                    ted.setCanSpread(false);
                }
            }


        }

    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        timeToSolidify = compound.getShort("TimeLeft");
    }

}
