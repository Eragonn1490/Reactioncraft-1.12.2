package com.reactioncraft.common.creativetabs;

import com.reactioncraft.common.registration.instances.ItemIndex;

import net.minecraft.item.ItemStack;

public class RCFoodTab extends RCItemTab
{	
    public RCFoodTab(String s)
    {
        super(s);
    }


	@Override
	public ItemStack getTabIconItem()
	{
		return new ItemStack(ItemIndex.bagofpopcorn);
	}
}