package com.reactioncraft.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TileEntityReprogrammer extends TileEntityBase implements ITickable
{
	public static int channel;
	
	@Override
	public void update() 
	{
		if(!this.world.isRemote) 
		{
			
		}
	}
	
	/**
	 * SAVE AND LOAD
	 */
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) 
	{	
		return super.writeToNBT(compound);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) 
	{
		super.readFromNBT(compound);
	}
}