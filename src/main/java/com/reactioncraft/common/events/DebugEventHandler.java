package com.reactioncraft.common.events;

import com.reactioncraft.common.blocks.BlockStatues;
import com.reactioncraft.common.blocks.columns.BlockColumn;
import com.reactioncraft.common.blocks.enums.EnumColumnTypes;
import com.reactioncraft.common.blocks.enums.EnumStatueBlocks;
import com.reactioncraft.common.instances.BlockIndex;
import com.reactioncraft.common.instances.ItemIndex;
import com.reactioncraft.common.utils.Logger;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DebugEventHandler
{
	@SubscribeEvent
	public void onPlayerPlaceEvent(RightClickBlock event)
	{
		ItemStack bagGold   = new ItemStack(ItemIndex.chicken_head);
		EntityPlayer playerIn = event.getEntityPlayer();
		BlockPos pos = event.getPos();
		World worldIn = event.getWorld();
		IBlockState state = worldIn.getBlockState(pos);
		Block block = state.getBlock();
		EnumFacing facing = event.getFace();
		EnumHand hand = playerIn.getActiveHand();
		ItemStack itemstack = playerIn.getHeldItemMainhand();

		if(event.getEntityPlayer().getHeldItemMainhand().getItem() == bagGold.getItem())
		{
			if (facing == EnumFacing.UP && playerIn.canPlayerEdit(pos.offset(facing), facing, itemstack) && worldIn.isAirBlock(pos.up()))
			{
				Logger.info("Tower Created");
				worldIn.setBlockState(pos.up(11), BlockIndex.emptystatues.getStateFromMeta(3));//Coil Empty block
				worldIn.setBlockState(pos.up(10), BlockIndex.statues.getStateFromMeta(7));//Coil
				worldIn.setBlockState(pos.up(9),  BlockIndex.smallestColumn.getStateFromMeta(11));
				worldIn.setBlockState(pos.up(8),  BlockIndex.smallestColumn.getStateFromMeta(11));
				worldIn.setBlockState(pos.up(7),  BlockIndex.miniColumn.getStateFromMeta(11));
				worldIn.setBlockState(pos.up(6),  BlockIndex.miniColumn.getStateFromMeta(11));
				worldIn.setBlockState(pos.up(5),  BlockIndex.column.getStateFromMeta(11));
				worldIn.setBlockState(pos.up(4),  BlockIndex.blockWirelessController.getDefaultState());
				worldIn.setBlockState(pos.up(3),  BlockIndex.blockWirelessConnector.getDefaultState());
				worldIn.setBlockState(pos.up(2),  BlockIndex.column.getStateFromMeta(11));
				worldIn.setBlockState(pos.up(),   BlockIndex.bloodstonebricks.getDefaultState());
				playerIn.getHeldItemMainhand().shrink(1);
				event.hasResult();
			}
		}
	}
	
	//This Class is never called, this class is a placeholder incase these events are ever needed again...
		/**
		//This works, allows you to place an item via event
		@SuppressWarnings("unused")
		@SubscribeEvent
		public void onPlayerPlaceEvent(RightClickBlock event)
		{
			ItemStack bagGold   = new ItemStack(ItemIndex.coins, 1, 15);
			ItemStack bagSilver = new ItemStack(ItemIndex.coins, 1, 10);
			ItemStack bagBronze = new ItemStack(ItemIndex.coins, 1, 5);
			EntityPlayer playerIn = event.getEntityPlayer();
			BlockPos pos = event.getPos();
			World worldIn = event.getWorld();
			IBlockState state = worldIn.getBlockState(pos);
			Block block = state.getBlock();
			EnumFacing facing = event.getFace();
			EnumHand hand = playerIn.getActiveHand();
			ItemStack itemstack = playerIn.getHeldItemMainhand();

			if(event.getEntityPlayer().getHeldItemMainhand().getItem() == bagGold.getItem())
			{
				if (facing == EnumFacing.UP && playerIn.canPlayerEdit(pos.offset(facing), facing, itemstack) && worldIn.isAirBlock(pos.up()))
				{
					Logger.info("Gold");
					worldIn.setBlockState(pos.up(), BlockIndex.statues.getStateFromMeta(6));//.withProperty(BlockStatues.TYPE, EnumStatueBlocks.BAG_G));
					playerIn.getHeldItemMainhand().shrink(1);
					event.hasResult();
				}
			}

			if(event.getEntityPlayer().getHeldItemMainhand().getItem() == bagSilver.getItem())
			{
				if (facing == EnumFacing.UP && playerIn.canPlayerEdit(pos.offset(facing), facing, itemstack) && worldIn.isAirBlock(pos.up()))
				{
					Logger.info("silver");
					worldIn.setBlockState(pos.up(), BlockIndex.statues.getStateFromMeta(5));//.withProperty(BlockStatues.TYPE, EnumStatueBlocks.BAG_S));
					playerIn.getHeldItemMainhand().shrink(1);
					event.hasResult();
				}
			}

			if(event.getEntityPlayer().getHeldItemMainhand().getItem() == bagBronze.getItem())
			{
				if (facing == EnumFacing.UP && playerIn.canPlayerEdit(pos.offset(facing), facing, itemstack) && worldIn.isAirBlock(pos.up()))
				{
					Logger.info("Bronze");
					worldIn.setBlockState(pos.up(), BlockIndex.statues.getStateFromMeta(4));//.withProperty(BlockStatues.TYPE, EnumStatueBlocks.BAG_B));
					playerIn.getHeldItemMainhand().shrink(1);
					event.hasResult();
				}
			}
			else if (event.getEntityPlayer().getHeldItemMainhand().getItem() != bagBronze.getItem() || event.getEntityPlayer().getHeldItemMainhand().getItem() != bagSilver.getItem() || event.getEntityPlayer().getHeldItemMainhand().getItem() != bagGold.getItem())
			{
				Logger.info("Not the right meta");
				event.isCanceled();
			}
			else { event.isCanceled(); }
		}
		 **/
}