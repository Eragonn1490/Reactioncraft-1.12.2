package com.reactioncraft.common.items;

import forestry.api.apiculture.IBee;
import forestry.api.apiculture.IBeeHousing;
import forestry.api.apiculture.IBeeModifier;
import forestry.api.apiculture.IHiveFrame;
import net.minecraft.item.ItemStack;

public class ItemFrame extends ItemBase implements IHiveFrame
{

	public ItemFrame(String name) 
	{
		super(name);
		
	}

	@Override
	public ItemStack frameUsed(IBeeHousing housing, ItemStack frame, IBee queen, int wear)
	{
		return null;
	}

	@Override
	public IBeeModifier getBeeModifier() 
	{
		return null;
	}
}