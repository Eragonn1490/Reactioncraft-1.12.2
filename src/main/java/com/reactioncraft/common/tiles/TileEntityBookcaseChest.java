package com.reactioncraft.common.tiles;

import com.reactioncraft.common.blocks.*;
import com.reactioncraft.common.blocks.enums.*;
import com.reactioncraft.common.registration.instances.BlockIndex;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ITickable;

public class TileEntityBookcaseChest extends TileEntityBase implements ITickable, IInventory
{
	 private EnumChestType chestType;
	
	protected TileEntityBookcaseChest(EnumChestType type)
    {
        super();

        this.chestType = type;
    }

	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasCustomName() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getSizeInventory() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getInventoryStackLimit() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
    public boolean isUsableByPlayer(EntityPlayer player)
    {
        if (this.world == null)
        {
            return true;
        }


        if (this.world.getTileEntity(this.pos) != this)
        {
            return false;
        }
        return player.getDistanceSq(this.pos.getX() + 0.5D, this.pos.getY() + 0.5D, this.pos.getZ() + 0.5D) <= 64D;
    }

	@Override
	public void openInventory(EntityPlayer player) 
	{
		// TODO Auto-generated method stub	
	}

	@Override
	public void closeInventory(EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getField(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setField(int id, int value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getFieldCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	public EnumChestType getType()
    {
		EnumChestType type = EnumChestType.Book;

        if (this.hasWorld())
        {
            IBlockState state = this.world.getBlockState(this.pos);

            if (state.getBlock() == BlockIndex.Bookcasechest)
            {
                type = state.getValue(BlockBookcaseChest.TYPE);
            }
        }
        return type;
    }
}