package com.reactioncraft.tiles;

import com.reactioncraft.utils.EnergyBase;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public class TileEntityConverter extends TileEntityBase implements ITickable 
{
	public final EnergyBase container;

	public TileEntityConverter() 
	{
		this.container = new EnergyBase();
	}

	@Override
	public void update() 
	{
		if(this.container.getEnergyStored() <= this.container.getMaxEnergyStored())
		{
			this.container.receiveEnergy(10, false);

			//System.out.println(this.container.getEnergyStored());
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) 
	{
		super.readFromNBT(compound);
		this.container.deserializeNBT(compound.getCompoundTag("StoredJAE"));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) 
	{
		compound.setTag("StoredJAE", this.container.serializeNBT());
		return super.writeToNBT(compound);
	}

	@Override
	public SPacketUpdateTileEntity getUpdatePacket() 
	{
		return super.getUpdatePacket();
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) 
	{
		super.onDataPacket(net, pkt);
		this.readFromNBT(pkt.getNbtCompound());
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) 
	{
		if(facing == EnumFacing.DOWN && capability == CapabilityEnergy.ENERGY)
			return (T) this.container;

		return super.getCapability(capability, facing);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) 
	{
		if(facing == EnumFacing.DOWN && capability == CapabilityEnergy.ENERGY)
			return true;

		return super.hasCapability(capability, facing);
	}
}