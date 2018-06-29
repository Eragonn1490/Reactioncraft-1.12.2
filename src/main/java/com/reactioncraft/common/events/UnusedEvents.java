package com.reactioncraft.common.events;

public class UnusedEvents 
{
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