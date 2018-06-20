package com.reactioncraft.blocks;

import com.reactioncraft.Reactioncraft;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import java.util.Random;

public class BlockNewSponge extends BlockBase
{
	private int absorbtion;

	public BlockNewSponge( int absorbtionSetting)
	{
		super(Material.SPONGE);
		this.absorbtion = absorbtionSetting;
		this.setCreativeTab(Reactioncraft.Reactioncraft);
		this.setTickRandomly(true);
	}
	
	
	@Override
	public int tickRate(World par1World)
    {
        return 1;
    }

	@Override
	public void onBlockDestroyedByExplosion(World worldIn, BlockPos pos, Explosion explosionIn) {
		onBlockDestroyedByPlayer(worldIn,pos,worldIn.getBlockState(pos));
	}

	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		if(!worldIn.isRemote)
		{
			suckUpWater(worldIn,pos);
		}
	}
	
	private void suckUpWater(World world, BlockPos pos)
	{
		for(int xCount = -absorbtion; xCount<absorbtion+1; xCount++)
		{
			for(int yCount = -absorbtion; yCount<absorbtion+1; yCount++)
			{
				for(int zCount = -absorbtion; zCount<absorbtion+1; zCount++)
				{
					BlockPos blockPos=new BlockPos(xCount+pos.getX(),yCount+pos.getY(),zCount+pos.getZ());
					IBlockState blockState=world.getBlockState(blockPos);
					if(blockState== Blocks.FLOWING_WATER.getDefaultState() || blockState==Blocks.WATER.getDefaultState())
					{
						world.setBlockToAir(blockPos);
					}
				}
			}
		}
	}

	@Override
	public void onNeighborChange(IBlockAccess world, BlockPos pos, BlockPos neighbor) {
		if(world instanceof WorldServer)
			suckUpWater((World) world,pos);
	}

	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		if(!worldIn.isRemote) suckUpWater(worldIn,pos);
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		suckUpWater(worldIn,pos);
	}
}