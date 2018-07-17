package com.reactioncraft.common.creativetabs;

import com.reactioncraft.common.instances.BlockIndex;

import net.minecraft.item.ItemStack;

public class RCTestTab extends RCItemTab
{	
    public RCTestTab(String s)
    {
        super(s);
    }

	@Override
	public ItemStack getTabIconItem()
	{
		return new ItemStack(BlockIndex.cherryTreeSapling);
	}
}