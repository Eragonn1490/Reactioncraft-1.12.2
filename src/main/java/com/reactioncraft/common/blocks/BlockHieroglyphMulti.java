package com.reactioncraft.common.blocks;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.common.blocks.enums.EnumHieroGlyphs;
import com.reactioncraft.common.instances.BlockIndex;
import com.reactioncraft.common.instances.ItemIndex;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockHieroglyphMulti extends BlockBase
{
	public static final PropertyEnum<EnumHieroGlyphs> TYPE = PropertyEnum.<EnumHieroGlyphs>create("type", EnumHieroGlyphs.class);

	public BlockHieroglyphMulti(Material materialIn)
	{
		super(materialIn);
		this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, EnumHieroGlyphs.BROWN1));
		this.setCreativeTab(Reactioncraft.Reactioncraft);
	}

	public void registerItemModel(ItemBlock itemBlock)
	{
		Reactioncraft.proxy.registerItemRenderer(itemBlock, 0, getRegistryName().getResourcePath());
	}

	//Custom Drops
	@Override
	public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te, ItemStack stack)
	{
		super.harvestBlock(world, player, pos, state, te, stack);
		world.setBlockToAir(pos);
	}

	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) 
	{
		EnumHieroGlyphs type = state.getValue(TYPE);

		super.getDrops(drops, world, pos, state, fortune); 

		if(type == EnumHieroGlyphs.BLUE1){
			drops.clear();
			drops.add(new ItemStack(BlockIndex.hieroglyph, 1, 12));
			drops.add(new ItemStack(ItemIndex.uncutLBGem));
		}

		if(type == EnumHieroGlyphs.BLUE2){
			drops.clear();
			drops.add(new ItemStack(BlockIndex.hieroglyph, 1, 12));
			drops.add(new ItemStack(ItemIndex.uncutLBGem));
		} 

		if(type == EnumHieroGlyphs.BLUE3){
			drops.clear();
			drops.add(new ItemStack(BlockIndex.hieroglyph, 1, 12));
			drops.add(new ItemStack(ItemIndex.uncutLBGem));
		} 
		if(type == EnumHieroGlyphs.CYAN1){
			drops.clear();
			drops.add(new ItemStack(BlockIndex.hieroglyph, 1, 12));
			drops.add(new ItemStack(ItemIndex.uncutLBGem));
		}

		if(type == EnumHieroGlyphs.CYAN2){
			drops.clear();
			drops.add(new ItemStack(BlockIndex.hieroglyph, 1, 12));
			drops.add(new ItemStack(ItemIndex.uncutLBGem));
		} 

		if(type == EnumHieroGlyphs.CYAN3){
			drops.clear();
			drops.add(new ItemStack(BlockIndex.hieroglyph, 1, 12));
			drops.add(new ItemStack(ItemIndex.uncutLBGem));
		}
		if(type == EnumHieroGlyphs.GOLD1){
			drops.clear();
			drops.add(new ItemStack(BlockIndex.hieroglyph, 1, 12));
			drops.add(new ItemStack(ItemIndex.uncutLBGem));
		}

		if(type == EnumHieroGlyphs.GOLD2){
			drops.clear();
			drops.add(new ItemStack(BlockIndex.hieroglyph, 1, 12));
			drops.add(new ItemStack(ItemIndex.uncutLBGem));
		} 

		if(type == EnumHieroGlyphs.GOLD3){
			drops.clear();
			drops.add(new ItemStack(BlockIndex.hieroglyph, 1, 12));
			drops.add(new ItemStack(ItemIndex.uncutLBGem));
		}

		else { drops.add(new ItemStack(Items.AIR)); }
	}

	/**
	 * Gets the metadata of the item this Block can drop. This method is called when the block gets destroyed. It
	 * returns the metadata of the dropped item based on the old metadata of the block.
	 */
	public int damageDropped(IBlockState state)
	{
		return 12;
	}

	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
		for (EnumHieroGlyphs types : EnumHieroGlyphs.values())
		{
			items.add(new ItemStack(this, 1, types.getMetadata()));
		}
	}

	/**
	 * Convert the given metadata into a BlockState for this Block
	 */
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(TYPE, EnumHieroGlyphs.byMetadata(meta));
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
		return new BlockStateContainer(this, new IProperty[] {TYPE});
	}
}