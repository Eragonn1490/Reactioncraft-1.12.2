package com.reactioncraft.common.blocks;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.common.blocks.enums.EnumStairTypes;
import com.reactioncraft.common.blocks.enums.EnumStairTypes;
import com.reactioncraft.common.utils.constants;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

public class BlockStair extends BlockStairs 
{	
	public static final PropertyEnum<EnumStairTypes> TYPE = PropertyEnum.create("type", EnumStairTypes.class);
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	public static final PropertyEnum<BlockStairs.EnumHalf> HALF = PropertyEnum.<BlockStairs.EnumHalf>create("half", BlockStairs.EnumHalf.class);
	public static final PropertyEnum<BlockStairs.EnumShape> SHAPE = PropertyEnum.<BlockStairs.EnumShape>create("shape", BlockStairs.EnumShape.class);

	public BlockStair(IBlockState iBlockState) 
	{
		super(iBlockState);
		this.setHardness(3);
		this.setResistance(20);
		this.useNeighborBrightness = true;
		this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, EnumStairTypes.BROWN1));
		this.setCreativeTab(Reactioncraft.ReactioncraftTest);
	}

	public int damageDropped(IBlockState state)
	{
		return (state.getValue(TYPE)).ordinal();
	}

	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) 
	{
		for (EnumStairTypes types : EnumStairTypes.values())
		{
			items.add(new ItemStack(this, 1, types.ordinal()));
		}
	}

	/**
	 * Convert the BlockState into the correct metadata value
	 */
	public int getMetaFromState(IBlockState state)
	{
		return (state.getValue(TYPE)).ordinal();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(TYPE,EnumStairTypes.values()[meta]);
	}

	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this,TYPE, FACING, HALF, SHAPE);
	}
}
