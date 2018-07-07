package com.reactioncraft.common.creativetabs;

import com.reactioncraft.common.registration.instances.BlockIndex;

import net.minecraft.item.ItemStack;

public class RCBlockTab extends RCItemTab
{	
    public RCBlockTab(String s)
    {
        super(s);
    }

	@Override
	public ItemStack getTabIconItem()
	{
		return new ItemStack(BlockIndex.bookcases);
	}
}