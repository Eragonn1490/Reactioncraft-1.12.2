package com.reactioncraft.items;

import com.reactioncraft.registration.instances.ItemIndex;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class ItemTossInLiquid extends ItemBase
{

	public ItemTossInLiquid(String name) 
	{
		super(name);
	}

	
}