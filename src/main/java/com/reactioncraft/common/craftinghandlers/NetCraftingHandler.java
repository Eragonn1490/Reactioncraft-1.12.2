package com.reactioncraft.common.craftinghandlers;

import com.reactioncraft.common.instances.ItemIndex;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;

public class NetCraftingHandler 
{
	@SubscribeEvent
	public void onCrafting(ItemCraftedEvent event)
	{
		if (event.crafting.getItem() == (ItemIndex.complete_net))
		{
			event.crafting.setTagCompound(new NBTTagCompound());
			ItemStack hilt = null;
			ItemStack net = null;

			for (int i = 0; i < event.craftMatrix.getSizeInventory(); ++i)
			{
				ItemStack is = event.craftMatrix.getStackInSlot(i);


					if (is.getItem() == ItemIndex.hilt)
					{
						hilt = is;
						event.crafting.getTagCompound().setInteger("hilt", hilt.getTagCompound().getInteger("str1"));
					}
					if(is.getItem() == ItemIndex.net)
					{
						net = is;
						event.crafting.getTagCompound().setInteger("net",  net.getTagCompound().getInteger("str2"));
					}
					else
					{
						net = is;
					}

			}
		}
	}
}