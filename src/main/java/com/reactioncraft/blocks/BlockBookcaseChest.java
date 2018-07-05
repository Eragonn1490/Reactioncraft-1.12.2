package com.reactioncraft.blocks;

import java.util.Random;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.blocks.enums.EnumChestBlocks;
import com.reactioncraft.blocks.enums.EnumDesertBlocks;
import com.reactioncraft.blocks.enums.EnumEmptyBlocks;
import com.reactioncraft.registration.instances.BlockIndex;
import com.reactioncraft.tiles.TileEntityFreezer;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBookcaseChest extends BlockBase implements MetadataArray
{
	protected String name;
	public static final PropertyEnum<EnumChestBlocks> TYPE = PropertyEnum.<EnumChestBlocks>create("type", EnumChestBlocks.class);

	public BlockBookcaseChest(Material material)
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
	public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest)
	{
		if(willHarvest) {
			return true; //if it will harvest, delay deletion of the block until after getDrops
		}
		return super.removedByPlayer(state, world, pos, player, willHarvest);
	}
	
	@Override
	public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te, ItemStack stack)
	{
		super.harvestBlock(world, player, pos, state, te, stack);
		world.setBlockToAir(pos);
	}
	
	/**
     * Gets the metadata of the item this Block can drop. This method is called when the block gets destroyed. It
     * returns the metadata of the dropped item based on the old metadata of the block.
     */
    public int damageDropped(IBlockState state)
    {
    	if (state.getValue(BlockBookcaseChest.TYPE) == EnumChestBlocks.BOOKSHELF)
		{
    		return 0;
		}
    	else
    	{
    		return (state.getValue(TYPE)).getMetadata();
    	}
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
    	EnumChestBlocks chests = state.getValue(BlockBookcaseChest.TYPE);
//        if(desertBlocks==EnumDesertBlocks.)
        return super.getItemDropped(state, rand, fortune);
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        for (EnumDesertBlocks types : EnumDesertBlocks.values())
        {
            items.add(new ItemStack(this, 1, types.getMetadata()));
        }
    }


    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(TYPE, EnumChestBlocks.byMetadata(meta));
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