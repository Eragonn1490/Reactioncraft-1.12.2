package com.reactioncraft.common.blocks;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.common.registration.instances.ItemIndex;

import net.minecraft.block.BlockDragonEgg;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class BlockHive extends BlockDragonEgg
{
	private EntityPlayer entityplayer;
	
	public BlockHive()
	{
		super();
		this.setCreativeTab(Reactioncraft.ReactioncraftTest);
	}

	@Override
	public void onBlockDestroyedByPlayer(World world, BlockPos pos, IBlockState state) {
		if (!world.isRemote)
		{
			
		}
		super.onBlockDestroyedByPlayer(world, pos, state);
	}


	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}


	@Override
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		return true;
	}


	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
		items.add(new ItemStack(this,1,0));
	}

	@Override
	public int damageDropped(IBlockState state) {

		return super.damageDropped(state);
	}


	@Override
	public void getDrops(NonNullList<ItemStack> ret, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		if(world instanceof WorldServer) {
			for (int i = 0; i < ((WorldServer) world).rand.nextInt(3); i++)
				ret.add(new ItemStack(ItemIndex.pollencomb, 1, 0));
			for (int i = 0; i < ((WorldServer) world).rand.nextInt(3); i++)
				ret.add(new ItemStack(ItemIndex.honeycomb, 1, 0));
		}
	}
}