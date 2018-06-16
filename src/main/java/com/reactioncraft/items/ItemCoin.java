package com.reactioncraft.items;

import com.reactioncraft.Reactioncraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ItemCoin extends Item
{
	public ItemCoin()
	{
		super();
		this.setHasSubtypes(true);

	}



	private static final String[] names = new String[] { "0", "1","2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"};



	public String getUnlocalizedName(ItemStack par1ItemStack)
	{	
		int i =getMetadata(par1ItemStack); //MathHelper.clamp(par1ItemStack.getItemDamage(), 0, 15);
//
//		if(par1ItemStack.getItemDamage() == 15)
//		{
//			setContainerItem(IntegratedItems.Bag);
//		}
//
//		if(par1ItemStack.getItemDamage() == 14)
//		{
//			setContainerItem(IntegratedItems.Sack);
//		}
//
//		if(par1ItemStack.getItemDamage() == 10)
//		{
//			setContainerItem(IntegratedItems.Bag);
//		}
//
//		if(par1ItemStack.getItemDamage() == 9)
//		{
//			setContainerItem(IntegratedItems.Sack);
//		}
//
//		if(par1ItemStack.getItemDamage() == 5)
//		{
//			setContainerItem(IntegratedItems.Bag);
//		}
//
//		if(par1ItemStack.getItemDamage() == 4)
//		{
//			setContainerItem(IntegratedItems.Sack);
//		}
		
		return "item."+Reactioncraft.MODID + ".coin." + names[i];
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