package com.reactioncraft.blocks;

import com.reactioncraft.Reactioncraft;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;

public class BlockBase extends Block
{

	protected String name;

	public BlockBase(Material material)
	{
		super(material);
	}

	public void registerItemModel(ItemBlock itemBlock)
	{
		Reactioncraft.proxy.registerItemRenderer(itemBlock, 0, getRegistryName().getResourcePath());
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return true;
	}

	
	@Override
	public BlockBase setCreativeTab(CreativeTabs tab)
	{
		super.setCreativeTab(tab);
		return this;
	}
}