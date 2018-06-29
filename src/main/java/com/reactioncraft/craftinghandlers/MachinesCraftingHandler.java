package com.reactioncraft.craftinghandlers;

import com.reactioncraft.registration.instances.BlockIndex;
import com.reactioncraft.registration.instances.ItemIndex;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;

public class MachinesCraftingHandler 
{
	@SubscribeEvent
	public void onCrafting(ItemCraftedEvent event)
	{
		//Tools
		if(event.crafting.getItem() == Items.IRON_AXE)
		{
			ItemStack irondust = new ItemStack(ItemIndex.ironShavings);
			for (int i = 0; i < event.player.inventory.getSizeInventory(); i++)
			{
				ItemStack slotStack = event.player.inventory.getStackInSlot(i);
				if (!slotStack .isEmpty() && (slotStack.getItem() == ItemIndex.hammer || slotStack.getItem() == ItemIndex.bloodstoneHammer))
				{
					event.player.dropItem(irondust, true);
				}
			}
		}
		if(event.crafting.getItem() == Items.IRON_PICKAXE)
		{
			ItemStack irondust = new ItemStack(ItemIndex.ironShavings);
			for (int i = 0; i < event.player.inventory.getSizeInventory(); i++)
			{
				ItemStack slotStack = event.player.inventory.getStackInSlot(i);
				if (!slotStack .isEmpty() && (slotStack.getItem() == ItemIndex.hammer || slotStack.getItem() == ItemIndex.bloodstoneHammer))
				{
					event.player.dropItem(irondust, true);
				}
			}
		}
		if(event.crafting.getItem() == Items.IRON_HOE)
		{
			ItemStack irondust = new ItemStack(ItemIndex.ironShavings);
			for (int i = 0; i < event.player.inventory.getSizeInventory(); i++)
			{
				ItemStack slotStack = event.player.inventory.getStackInSlot(i);
				if (!slotStack .isEmpty() && (slotStack.getItem() == ItemIndex.hammer || slotStack.getItem() == ItemIndex.bloodstoneHammer))
				{
					event.player.dropItem(irondust, true);
				}
			}
		}
		if(event.crafting.getItem() == Items.IRON_SHOVEL)
		{
			ItemStack irondust = new ItemStack(ItemIndex.ironShavings);
			for (int i = 0; i < event.player.inventory.getSizeInventory(); i++)
			{
				ItemStack slotStack = event.player.inventory.getStackInSlot(i);
				if (!slotStack .isEmpty() && (slotStack.getItem() == ItemIndex.hammer || slotStack.getItem() == ItemIndex.bloodstoneHammer))
				{
					event.player.dropItem(irondust, true);
				}
			}
		}
		if(event.crafting.getItem() == Items.IRON_SWORD)
		{
			ItemStack irondust = new ItemStack(ItemIndex.ironShavings);
			for (int i = 0; i < event.player.inventory.getSizeInventory(); i++)
			{
				ItemStack slotStack = event.player.inventory.getStackInSlot(i);
				if (!slotStack .isEmpty() && (slotStack.getItem() == ItemIndex.hammer || slotStack.getItem() == ItemIndex.bloodstoneHammer))
				{
					event.player.dropItem(irondust, true);
				}
			}
		}
		
		//
		if(event.crafting.getItem() == Items.IRON_DOOR)
		{
			ItemStack irondust = new ItemStack(ItemIndex.ironShavings);
			for (int i = 0; i < event.player.inventory.getSizeInventory(); i++)
			{
				ItemStack slotStack = event.player.inventory.getStackInSlot(i);
				if (!slotStack .isEmpty() && (slotStack.getItem() == ItemIndex.hammer || slotStack.getItem() == ItemIndex.bloodstoneHammer))
				{
					event.player.dropItem(irondust, true);
				}
			}
		}
		
		//Armor
		if(event.crafting.getItem() == Items.IRON_BOOTS)
		{
			ItemStack irondust = new ItemStack(ItemIndex.ironShavings);
			for (int i = 0; i < event.player.inventory.getSizeInventory(); i++)
			{
				ItemStack slotStack = event.player.inventory.getStackInSlot(i);
				if (!slotStack .isEmpty() && (slotStack.getItem() == ItemIndex.hammer || slotStack.getItem() == ItemIndex.bloodstoneHammer))
				{
					event.player.dropItem(irondust, true);
				}
			}
		}
		if(event.crafting.getItem() == Items.IRON_CHESTPLATE)
		{
			ItemStack irondust = new ItemStack(ItemIndex.ironShavings);
			for (int i = 0; i < event.player.inventory.getSizeInventory(); i++)
			{
				ItemStack slotStack = event.player.inventory.getStackInSlot(i);
				if (!slotStack .isEmpty() && (slotStack.getItem() == ItemIndex.hammer || slotStack.getItem() == ItemIndex.bloodstoneHammer))
				{
					event.player.dropItem(irondust, true);
				}
			}
		}
		if(event.crafting.getItem() == Items.IRON_HELMET)
		{
			ItemStack irondust = new ItemStack(ItemIndex.ironShavings);
			for (int i = 0; i < event.player.inventory.getSizeInventory(); i++)
			{
				ItemStack slotStack = event.player.inventory.getStackInSlot(i);
				if (!slotStack .isEmpty() && (slotStack.getItem() == ItemIndex.hammer || slotStack.getItem() == ItemIndex.bloodstoneHammer))
				{
					event.player.dropItem(irondust, true);
				}
			}
		}
		if(event.crafting.getItem() == Items.IRON_LEGGINGS)
		{
			ItemStack irondust = new ItemStack(ItemIndex.ironShavings);
			for (int i = 0; i < event.player.inventory.getSizeInventory(); i++)
			{
				ItemStack slotStack = event.player.inventory.getStackInSlot(i);
				if (!slotStack .isEmpty() && (slotStack.getItem() == ItemIndex.hammer || slotStack.getItem() == ItemIndex.bloodstoneHammer))
				{
					event.player.dropItem(irondust, true);
				}
			}
		}
		
		//Reactioncraft
		if(event.crafting.getItem() == ItemIndex.chainLoop)
		{
			ItemStack irondust = new ItemStack(ItemIndex.ironShavings);
			for (int i = 0; i < event.player.inventory.getSizeInventory(); i++)
			{
				ItemStack slotStack = event.player.inventory.getStackInSlot(i);
				if (!slotStack .isEmpty() && (slotStack.getItem() == ItemIndex.hammer || slotStack.getItem() == ItemIndex.bloodstoneHammer))
				{
					event.player.dropItem(irondust, true);
				}
			}
		}
	}
}