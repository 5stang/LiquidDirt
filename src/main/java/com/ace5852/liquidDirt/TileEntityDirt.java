package com.ace5852.liquidDirt;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityDirt extends TileEntity
{
    private short timeToSolidify;

    public TileEntityDirt()
    {
        timeToSolidify = 200;
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
        }

    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        timeToSolidify = compound.getShort("TimeLeft");
    }

}
