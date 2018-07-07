package com.reactioncraft.common.blocks;

import java.util.Random;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.common.blocks.enums.EnumLevers;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBookcaseLever extends BlockBase
{
	public static final PropertyEnum<EnumLevers> TYPE = PropertyEnum.<EnumLevers>create("type", EnumLevers.class);
	public static final PropertyBool POWERED = PropertyBool.create("powered");

	public BlockBookcaseLever(Material materialIn)
	{
		super(materialIn);
		this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, EnumLevers.FULL).withProperty(POWERED, Boolean.valueOf(false)));
		this.setCreativeTab(Reactioncraft.Reactioncraft);
	}

	/**
	 * Gets the metadata of the item this Block can drop. This method is called when the block gets destroyed. It
	 * returns the metadata of the dropped item based on the old metadata of the block.
	 */
	public int damageDropped(IBlockState state)
	{
		if (state.getValue(BlockBookcaseLever.TYPE) == EnumLevers.FULL)
		{
			return 0;
		}
		if (state.getValue(BlockBookcaseLever.TYPE) == EnumLevers.SCROLLCASE_FULL)
		{
			return 1;
		}
		else
		{
			return (state.getValue(TYPE)).getMetadata();
		}
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) 
	{
		EnumLevers desertBlocks=state.getValue(BlockBookcaseLever.TYPE);
		//        if(desertBlocks==EnumLevers.)

		return super.getItemDropped(state, rand, fortune);
	}

	@Override
	public void getSubBlocks(CreativeTabs creativeTabs, NonNullList<ItemStack> items) 
	{
		items.add(new ItemStack(this,1,EnumLevers.FULL.getMetadata()));
		items.add(new ItemStack(this,1,EnumLevers.SCROLLCASE_FULL.getMetadata()));
	}
	

    public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
    	if (!((Boolean)blockState.getValue(POWERED)).booleanValue())
        {
            return 0;
        }
        else
        {
            return 15;
        }
    }

    public int getStrongPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        if (!((Boolean)blockState.getValue(POWERED)).booleanValue())
        {
            return 0;
        }
        else
        {
            return 15;
        }
    }

    /**
     * Can this block provide power. Only wire currently seems to have this change based on its state.
     */
    public boolean canProvidePower(IBlockState state)
    {
        return true;
    }

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) 
	{
		if (worldIn.isRemote)
        {
            return true;
        }
        else
        {
            state = state.cycleProperty(POWERED);
            worldIn.setBlockState(pos, state, 3);
            float f = ((Boolean)state.getValue(POWERED)).booleanValue() ? 0.6F : 0.5F;
            worldIn.playSound((EntityPlayer)null, pos, SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.BLOCKS, 0.3F, f);
            worldIn.notifyNeighborsOfStateChange(pos, this, false);
            return true;
        }
	}

	/**
	 * Convert the given metadata into a BlockState for this Block
	 */
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(TYPE, EnumLevers.byMetadata(meta));
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
		return new BlockStateContainer(this, TYPE, POWERED);
	}
}