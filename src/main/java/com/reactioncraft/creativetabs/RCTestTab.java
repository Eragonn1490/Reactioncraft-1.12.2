package com.reactioncraft.creativetabs;

import com.reactioncraft.registration.instances.BlockIndex;
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