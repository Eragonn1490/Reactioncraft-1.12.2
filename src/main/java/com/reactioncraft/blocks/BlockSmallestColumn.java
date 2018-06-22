package com.reactioncraft.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockSmallestColumn extends BlockColumn
{
	static final AxisAlignedBB BOUNDING_BOX=new AxisAlignedBB(0.1875,0,0.1875,0.8125,1,0.8125);
	
	public BlockSmallestColumn(Material material)
	{
		super(material);
		//this.setCreativeTab(null);//Needs to Fix Bounding Box
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return BOUNDING_BOX;
	}
}