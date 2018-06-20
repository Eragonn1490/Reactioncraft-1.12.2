package com.reactioncraft.craftinghandlers;

import com.reactioncraft.registration.instances.ItemIndex;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;

/**
 * @author Ordinastie && Eragonn1490
 *
 */
public class HammerCraftingHandler
{
	@SuppressWarnings("unused")
	@SubscribeEvent
	public void onCrafting(ItemCraftedEvent event)
	{
		if (event.crafting.getItem() != Items.IRON_AXE)
			return;
		
		for (int i = 0; i < event.player.inventory.getSizeInventory(); i++)
		{
			ItemStack slotStack = event.player.inventory.getStackInSlot(i);

			if (slotStack.getItem() == ItemIndex.hammer || slotStack.getItem() == ItemIndex.bloodstoneHammer)
			{
				slotStack.damageItem(1, event.player);
				
				if (slotStack.getItemDamage() == slotStack.getMaxDamage()) 
				{
					event.player.inventory.setInventorySlotContents(i, ItemStack.EMPTY);
				}
			}
		}
	}
}