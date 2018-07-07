package com.reactioncraft.common.items;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.common.utils.constants;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ItemPaints extends Item
{
	public ItemPaints()
	{
		super();
		this.setHasSubtypes(true);
	}


	private static final String[] names = new String[] { "0", "1","2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"};


	public String getUnlocalizedName(ItemStack par1ItemStack)
	{	
		int i =getMetadata(par1ItemStack);
		
		return "item."+constants.MODID + ".paints." + names[i];
	}


	@Override
	public int getMetadata(int damage) {
		return damage;
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if(tab==Reactioncraft.ReactioncraftItems) {
			for (int i = 0; i < 16; i++) {
				items.add(new ItemStack(this, 1, i));
			}
		}
	}
}