package com.reactioncraft.creativetabs;

import com.reactioncraft.registration.instances.ItemIndex;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class RCItemTab extends CreativeTabs
{	
    public RCItemTab(String texture)
    {
        super(texture);
        setNoTitle();
    }
    
    public String getBackgroundImageName()
    {
        return "item_search.png";
    }
    
    @Override
    public boolean hasSearchBar()
    {
        return true;
    }

	@Override
	public ItemStack getTabIconItem()
	{
		return new ItemStack(ItemIndex.bloodstoneHammer);
	}
}