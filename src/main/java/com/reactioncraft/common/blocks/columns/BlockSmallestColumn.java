package com.reactioncraft.common.blocks.columns;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockSmallestColumn extends BlockColumn
{
	//Left Right
	static double x1 = 0.7;
	static double x2 = 0.301;

	//up Down
	static double y1 = 0;
	static double y2 = 1.0;

	//Z axis
	static double z1 = 0.301;
	static double z2 = 0.7;

	static final AxisAlignedBB BOUNDING_BOX=new AxisAlignedBB(x1, y1, z1, x2, y2, z2);

	public BlockSmallestColumn(Material material)
	{
		super(material);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) 
	{
		return BOUNDING_BOX;
	}

	@Override
	@Deprecated
	public boolean isOpaqueCube(IBlockState state) 
	{
		return false;
	}



	@Override
	@Deprecated
	public boolean isFullCube(IBlockState state) 
	{
		return false;
	}
}