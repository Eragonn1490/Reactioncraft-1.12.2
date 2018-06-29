package com.reactioncraft.blocks;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.blocks.enums.EnumStatueBlocks;
import com.reactioncraft.registration.instances.ItemIndex;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

public class BlockStatues extends BlockBase implements MetadataArray
{
	public static final PropertyEnum<EnumStatueBlocks> TYPE = PropertyEnum.<EnumStatueBlocks>create("type", EnumStatueBlocks.class);

	public BlockStatues(Material materialIn)
	{
		super(materialIn);
		this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, EnumStatueBlocks.CREEPER));
		this.setCreativeTab(Reactioncraft.Reactioncraft);
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean canPlaceTorchOnTop(IBlockState state, IBlockAccess world, BlockPos pos) {
		return false;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) 
	{
		if (state.getValue(BlockStatues.TYPE) == EnumStatueBlocks.CREEPER)
		{
			//Left Right
			double x1 = 0.0;
			double x2 = 0.75;

			//up Down
			double y1 = 0;
			double y2 = 2.0;

			//Z axis
			double z1 = 0.0;
			double z2 = 0.75;

			final AxisAlignedBB BOUNDING_BOX=new AxisAlignedBB(x1, y1, z1, x2, y2, z2);

			return BOUNDING_BOX;
		}

		if (state.getValue(BlockStatues.TYPE) == EnumStatueBlocks.BAG_B || state.getValue(BlockStatues.TYPE) == EnumStatueBlocks.BAG_S || state.getValue(BlockStatues.TYPE) == EnumStatueBlocks.BAG_G)
		{
			//Left Right
			double x1 = 0.25;
			double x2 = 0.75;

			//up Down
			double y1 = 0;
			double y2 = 0.5;

			//Z axis
			double z1 = 0.25;
			double z2 = 0.75;

			final AxisAlignedBB BOUNDING_BOX=new AxisAlignedBB(x1, y1, z1, x2, y2, z2);

			return BOUNDING_BOX;
		}

		if (state.getValue(BlockStatues.TYPE) == EnumStatueBlocks.GLASS)
		{
			//Left Right
			double x1 = 0.3;
			double x2 = 0.70;

			//up Down
			double y1 = 0;
			double y2 = 0.6;

			//Z axis
			double z1 = 0.3;
			double z2 = 0.70;

			final AxisAlignedBB BOUNDING_BOX=new AxisAlignedBB(x1, y1, z1, x2, y2, z2);

			return BOUNDING_BOX;
		}

		else
		{
			//Left Right
			double x1 = 0.0;
			double x2 = 1.0;

			//up Down
			double y1 = 0;
			double y2 = 1.5;

			//Z axis
			double z1 = 0.0;
			double z2 = 1.0;

			final AxisAlignedBB BOUNDING_BOX=new AxisAlignedBB(x1, y1, z1, x2, y2, z2);

			return BOUNDING_BOX;
		}
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean p_185477_7_)
	{
		addCollisionBoxToList(pos, entityBox, collidingBoxes, state.getBoundingBox(worldIn, pos));
	}

	/**
	 * Gets the metadata of the item this Block can drop. This method is called when the block gets destroyed. It
	 * returns the metadata of the dropped item based on the old metadata of the block.
	 */
	public int damageDropped(IBlockState state)
	{
		if (state.getValue(BlockStatues.TYPE) == EnumStatueBlocks.CREEPER)
		{
			return 0;
		}
		else
		{
			return (state.getValue(TYPE)).getMetadata();
		}
	}

	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) 
	{
		EnumStatueBlocks type = state.getValue(TYPE);

		super.getDrops(drops, world, pos, state, fortune); 

		if(type == EnumStatueBlocks.BAG_G)
		{
			drops.clear();
			drops.add(new ItemStack(ItemIndex.coins, 1, 15));
		}

		if(type == EnumStatueBlocks.BAG_S)
		{
			drops.clear();
			drops.add(new ItemStack(ItemIndex.coins, 1, 10));
		}

		if(type == EnumStatueBlocks.BAG_B)
		{
			drops.clear();
			drops.add(new ItemStack(ItemIndex.coins, 1, 5));
		}
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) 
	{
		EnumStatueBlocks desertBlocks=state.getValue(BlockStatues.TYPE);
		//        if(desertBlocks==EnumStatueBlocks.)
		return super.getItemDropped(state, rand, fortune);
	}

	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) 
	{
		items.add(new ItemStack(this,1,EnumStatueBlocks.CREEPER.getMetadata()));
		items.add(new ItemStack(this,1,EnumStatueBlocks.HUMAN.getMetadata()));
		items.add(new ItemStack(this,1,EnumStatueBlocks.BLAZE.getMetadata()));
		items.add(new ItemStack(this,1,EnumStatueBlocks.MIXER.getMetadata()));
		items.add(new ItemStack(this,1,EnumStatueBlocks.COIL.getMetadata()));
		items.add(new ItemStack(this,1,EnumStatueBlocks.GLASS.getMetadata()));
	}


	/**
	 * Convert the given metadata into a BlockState for this Block
	 */
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(TYPE, EnumStatueBlocks.byMetadata(meta));
	}


	/**
	 * Convert the BlockState into the correct metadata value
	 */
	public int getMetaFromState(IBlockState state)
	{
		return (state.getValue(TYPE)).getMetadata();
	}


	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, TYPE);
	}
}