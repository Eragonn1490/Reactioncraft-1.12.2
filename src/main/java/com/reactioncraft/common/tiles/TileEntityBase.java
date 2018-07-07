package com.reactioncraft.common.tiles;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created on 12/31/17.
 */
public class TileEntityBase extends TileEntity 
{
	@Override
	public void readFromNBT (NBTTagCompound dataTag) {
		this.readNBT(dataTag);
		super.readFromNBT(dataTag);
	}


	@Override
	public NBTTagCompound writeToNBT (NBTTagCompound dataTag) 
	{
		this.writeNBT(dataTag);
		return super.writeToNBT(dataTag);
	}


	@Override
	public SPacketUpdateTileEntity getUpdatePacket () 
	{
		return new SPacketUpdateTileEntity(this.pos, 0, this.getUpdateTag());
	}


	@Override
	public void onDataPacket (NetworkManager net, SPacketUpdateTileEntity packet) 
	{
		super.onDataPacket(net, packet);
		this.readNBT(packet.getNbtCompound());
	}



	@Override

	public NBTTagCompound getUpdateTag () 
	{
		return this.writeToNBT(new NBTTagCompound());
	}


	@Override
	public boolean shouldRefresh (World world, BlockPos pos, IBlockState oldState, IBlockState newState) 
	{
		return oldState.getBlock() != newState.getBlock();
	}


	/**
	 * Marks the tile entity for a block update. This will mark the block as dirty, sync the
	 * NBT from server to client, and cause a block update. Keep in mind that this should be
	 * used only when necessary to prevent lag!
	 */
	public void sync () 
	{
		this.markDirty();
		if (this.isLoaded()) 
		{
			final IBlockState state = this.getState();
			this.getWorld().notifyBlockUpdate(this.pos, state, state, 3);
		}
	}


	/**
	 * Checks if the tile entity has a valid position.
	 *
	 * @return Whether or not the tile entity has a valid position.
	 */
	public boolean hasPosition ()
	{
		return this.pos != null && this.pos != BlockPos.ORIGIN;
	}


	/**
	 * Checks if the tile entity has initialized, and is in a loaded chunk.
	 *
	 * @return Whether or not the tile entity is initialized and in a loaded chunk.
	 */
	public boolean isLoaded () 
	{
		return this.hasWorld() && this.hasPosition() ? this.getWorld().isBlockLoaded(this.getPos()) : false;
	}


	/**
	 * Gets the block state of the tile entity. Can be null if the tile is not loaded.
	 *
	 * @return The block state of the tile.
	 */
	public IBlockState getState () 
	{
		return this.isLoaded() ? this.getWorld().getBlockState(this.pos) : null;
	}


	/**
	 * Handles the ability to write custom NBT values to a TileEntity.
	 *
	 * @param dataTag: The NBTTagCompound for the TileEntity.
	 */
	public void writeNBT (NBTTagCompound dataTag) {   }


	/**
	 * Handles the ability to read custom NBT values from the TileEntity's NBTTagCompound.
	 *
	 * @param dataTag: The NBTTagCompound for the TileEntity.
	 */
	public void readNBT (NBTTagCompound dataTag) {   }


	/**
	 * This is called when the tile block is removed. Other methods are seemingly unreliable,
	 * so this should be used over them.
	 * @param world The world instance.
	 * @param pos The position of the broken tile.
	 * @param state The state of the broken tile.
	 */
	public void onTileRemoved (World world, BlockPos pos, IBlockState state) {}
}