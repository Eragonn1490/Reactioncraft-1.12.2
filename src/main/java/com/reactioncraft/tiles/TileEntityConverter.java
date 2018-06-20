package com.reactioncraft.tiles;

import javax.annotation.Nonnull;

//import buildcraft.api.mj.IMjConnector;
//import buildcraft.api.mj.IMjReceiver;
//import buildcraft.api.mj.MjAPI;
//import buildcraft.api.mj.MjCapabilityHelper;
//import cofh.redstoneflux.api.IEnergyConnection;
//import cofh.redstoneflux.api.IEnergyHandler;
//import cofh.redstoneflux.api.IEnergyProvider;
//import cofh.redstoneflux.api.IEnergyReceiver;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

public class TileEntityConverter extends TileEntity //implements IEnergyConnection, IEnergyHandler, IEnergyReceiver, IEnergyProvider,IMjConnector, IMjReceiver
{	
	/**
	 * 
	private final MjCapabilityHelper mjCaps = new MjCapabilityHelper(this);
	private long lastReceived;
	private long totalReceived;


	@Override
	public boolean canConnectEnergy(EnumFacing from) 
	{
		return true;
	}

	@Override
	public boolean canConnect(IMjConnector other) 
	{
		return true;
	}
	
	@Override
	public int getEnergyStored(EnumFacing from) 
	{
		return (int) totalReceived;
	}

	@Override
	public int getMaxEnergyStored(EnumFacing from) 
	{
		return 100000;
	}

	@Override
	public int extractEnergy(EnumFacing from, int maxExtract, boolean simulate) {
		return 10;
	}

	@Override
	public int receiveEnergy(EnumFacing from, int maxReceive, boolean simulate) {
		return 10;
	}



	@Override
	public long getPowerRequested() {
		return 100000 * MjAPI.MJ;
	}

	
	@Override
	public long receivePower(long microJoules, boolean simulate) 
	{
		return 10;
	}
		 */
}