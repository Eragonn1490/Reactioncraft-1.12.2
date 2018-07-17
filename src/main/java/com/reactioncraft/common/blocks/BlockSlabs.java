package com.reactioncraft.common.blocks;

import java.util.Random;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.common.instances.BlockIndex;
import com.reactioncraft.common.utils.constants;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

/**
 * Basic Slab Class. When creating a new slab you need to have your own version
 * of this with your hardness and resistance being set
 * 
 * @author CJMinecraft
 *
 */
public abstract class BlockSlabs extends BlockSlab 
{
	public BlockSlabs() 
	{
		super(Material.IRON); //Normal block stuff
		this.setHardness(3);
		this.setResistance(20);
	
		//Set the default state
		IBlockState state = this.blockState.getBaseState();
		if (!this.isDouble()) {
			state = state.withProperty(HALF, EnumBlockHalf.BOTTOM);
		}
		setDefaultState(state);
		this.useNeighborBrightness = true; //Makes it so that you don't get dark patches on the block
	}

	/**
	 * Makes it so that your block doesn't accept meta data
	 */
	@Override
	public String getUnlocalizedName(int meta) {
		return this.getUnlocalizedName();
	}

	/**
	 * Only use if your block has multiple types, i.e {@link ChipTypes}. If yours does not then just use what I put
	 */
	@Override
	public IProperty<?> getVariantProperty() {
		return HALF;
	}

	/**
	 * Only use if your block has multiple types, i.e {@link ChipTypes}. If yours does not then just use what I put
	 */
	@Override
	public Comparable<?> getTypeForItem(ItemStack stack) {
		return EnumBlockHalf.BOTTOM;
	}

	/**
	 * Make it so that meta data won't work with our block
	 */
	@Override
	public int damageDropped(IBlockState state) {
		return 0;
	}

	/**
	 * Gets the state from the meta data so our block saves correctly
	 */
	@Override
	public IBlockState getStateFromMeta(int meta) 
	{
		if (!this.isDouble())
			return this.getDefaultState().withProperty(HALF, EnumBlockHalf.values()[meta % EnumBlockHalf.values().length]);
		return this.getDefaultState();
	}

	/**
	 * Gets the meta from the state so it will save correctly
	 */
	@Override
	public int getMetaFromState(IBlockState state) {
		if (this.isDouble())
			return 0;
		return ((EnumBlockHalf) state.getValue(HALF)).ordinal() + 1;
	}

	/**
	 * Use your block from your {@link ModBlocks} class
	 */
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(BlockIndex.SlabHalf);
	}

	/**
	 * Register it so that your block has its own half.
	 * MUST DO!!
	 */
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { HALF });
	}
}
