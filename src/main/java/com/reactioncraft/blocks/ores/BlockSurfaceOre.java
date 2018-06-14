package com.reactioncraft.blocks.ores;

import java.util.ArrayList;
import java.util.Random;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.blocks.BlockBase;
import com.reactioncraft.blocks.MetadataArray;
import com.reactioncraft.core.Logger;
import com.reactioncraft.registration.instances.BlockIndex;
import com.reactioncraft.registration.instances.ItemIndex;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSurfaceOre extends BlockBase implements MetadataArray
{
	public static final PropertyEnum<EnumSurfaceOres> TYPE = PropertyEnum.<EnumSurfaceOres>create("type", EnumSurfaceOres.class);

	public BlockSurfaceOre(Material materialIn)
	{
		super(materialIn);
		this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, EnumSurfaceOres.LIGHT_BLUE_GEM));
		this.setCreativeTab(Reactioncraft.Reactioncraft);
	}
	
	//Custom Drops
	@Override
	public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te, ItemStack stack)
	{
		super.harvestBlock(world, player, pos, state, te, stack);
		world.setBlockToAir(pos);
	}
	
	@Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        //Logger.info("Called GetDrops");//For Debugging
        EnumSurfaceOres type = state.getValue(TYPE);
 
        super.getDrops(drops, world, pos, state, fortune); 
 
        if(type == EnumSurfaceOres.DESERT_COAL){
        	drops.clear();
            drops.add(new ItemStack(Items.COAL, 1, 0));
            //Logger.info("Dropped", drops);//For Debugging
        }
        
        if(type == EnumSurfaceOres.LIGHT_BLUE_GEM){
        	drops.clear();
            drops.add(new ItemStack(ItemIndex.uncutLBGem));
            //Logger.info("Dropped", drops);//For Debugging
        } 
        
        if(type == EnumSurfaceOres.DARK_BLUE_GEM){
        	drops.clear();
            drops.add(new ItemStack(ItemIndex.uncutDBGem));
            //Logger.info("Dropped", drops);//For Debugging
        } 
 
        else {
            drops.add(new ItemStack(Items.AIR));
            //Logger.info("Dropped Nothing");//For Debugging
        }
    }
	
	
	/**
     * Gets the metadata of the item this Block can drop. This method is called when the block gets destroyed. It
     * returns the metadata of the dropped item based on the old metadata of the block.
     */
    public int damageDropped(IBlockState state)
    {
        return (state.getValue(TYPE)).getMetadata();
    }



	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
		for (EnumSurfaceOres types : EnumSurfaceOres.values())
		{
			items.add(new ItemStack(this, 1, types.getMetadata()));
		}
	}


	/**
	 * Convert the given metadata into a BlockState for this Block
	 */
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(TYPE, EnumSurfaceOres.byMetadata(meta));
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