package com.reactioncraft.blocks;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.common.ColumnTypes;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockColumn extends Block
{
	public static final PropertyEnum<ColumnTypes> TYPE = PropertyEnum.create("type", ColumnTypes.class);
	public BlockColumn(Material material)
	{
		super(material);
		this.setCreativeTab(Reactioncraft.Reactioncraft);
		this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, ColumnTypes.BROWN1));

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
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return (face==EnumFacing.UP || face==EnumFacing.DOWN) ? BlockFaceShape.SOLID : BlockFaceShape.UNDEFINED;
	}

	@Override
	public float getBlockHardness(IBlockState blockState, World worldIn, BlockPos pos) {

		return 3;
	}

    @Override
    public float getPlayerRelativeBlockHardness(IBlockState state, EntityPlayer player, World worldIn, BlockPos pos) {

        return super.getPlayerRelativeBlockHardness(state, player, worldIn, pos);
    }

    //	@Override
//	public float getBlockHardness(World par1World, int par2, int par3, int par4)
//	{
//		par1World.getBlockMetadata(par2, par3, par4);
//
//		if(par1World.getBlockMetadata(par2, par3, par4) == 1)
//		{
//			return 3.0F;
//		}
//
//		if(par1World.getBlockMetadata(par2, par3, par4) == 2)
//		{
//			return 120.0F;
//		}
//
//		if(par1World.getBlockMetadata(par2, par3, par4) == 3)
//		{
//			return 1.5F;
//		}
//
//		if(par1World.getBlockMetadata(par2, par3, par4) == 4)
//		{
//			return 2.0F;
//		}
//
//		if(par1World.getBlockMetadata(par2, par3, par4) == 5)
//		{
//			return 3.0F;
//		}
//
//		if(par1World.getBlockMetadata(par2, par3, par4) == 6)
//		{
//			return 0.8F;
//		}
//
//		else
//		{
//			return 3.0F;
//		}
//	}


	public int damageDropped(IBlockState state)
	{
		return (state.getValue(TYPE)).ordinal();
	}

	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
		for (ColumnTypes types : ColumnTypes.values())
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
		return getDefaultState().withProperty(TYPE,ColumnTypes.values()[meta]);
	}

	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this,TYPE);
	}


	private static final AxisAlignedBB BOUNDING_BOX=new AxisAlignedBB(0.0625,0,0.0625,0.9375,1,0.9375);
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return BOUNDING_BOX;
	}

	@Override
	public boolean canPlaceTorchOnTop(IBlockState state, IBlockAccess world, BlockPos pos) {
		return true;
	}

}
