package com.reactioncraft.blocks;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.registration.instances.BlockIndex;
import com.reactioncraft.world.BiomeGenReactionDesert;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.IPlantable;

import java.util.Random;

import static net.minecraft.block.BlockSapling.STAGE;

public class BlockCherryTreeSapling extends BlockBush implements IGrowable
{
	public BlockCherryTreeSapling()
	{
		setDefaultState(blockState.getBaseState().withProperty(STAGE,0));
		this.setCreativeTab(Reactioncraft.Reactioncraft);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this,STAGE);
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if(!worldIn.isRemote) {
			super.updateTick(worldIn, pos, state, rand);
			if (worldIn.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(7) == 0)
			{
				this.grow(worldIn, pos, state, rand);
			}
		}
	}

	public void grow(World worldIn, BlockPos pos, IBlockState state, Random rand)
	{
		if (state.getValue(STAGE) == 0)
		{
			worldIn.setBlockState(pos, state.cycleProperty(STAGE), 4);
		}
		else
		{
			this.generateTree(worldIn, pos, state, rand);
		}
	}

	public void generateTree(World worldIn, BlockPos pos, IBlockState state, Random rand)
	{
		if (!net.minecraftforge.event.terraingen.TerrainGen.saplingGrowTree(worldIn, rand, pos)) return;
		WorldGenerator worldgenerator = BiomeGenReactionDesert.cherryTrees;

		worldIn.setBlockState(pos,Blocks.AIR.getDefaultState());
		if (!worldgenerator.generate(worldIn, rand, pos))
		{
			worldIn.setBlockState(pos,state);
		}
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(STAGE);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(STAGE,meta);
	}


	@Override
	public int damageDropped(IBlockState state) {
		return 0;
	}

	@Override
	public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, IPlantable plantable) {
		return state.getBlock()== BlockIndex.dark_sand || state== Blocks.SAND.getDefaultState() ||
				state==Blocks.DIRT.getDefaultState();
	}

	@Override
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
		return true;
	}

	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		return true;
	}

	@Override
	public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		grow(worldIn, pos, state, rand);
	}
}