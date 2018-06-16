package com.reactioncraft.blocks;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.blocks.BlockBase;
import com.reactioncraft.blocks.MetadataArray;
import com.reactioncraft.common.EnumDesertBlocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class BlockDesertMulti extends BlockBase implements MetadataArray
{
    public static final PropertyEnum<EnumDesertBlocks> TYPE = PropertyEnum.<EnumDesertBlocks>create("type", EnumDesertBlocks.class);

    public BlockDesertMulti(Material materialIn)
    {
        super(materialIn);
        this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, EnumDesertBlocks.DESERT_Bricks));
        this.setCreativeTab(Reactioncraft.Reactioncraft);
    }

    /**
	 * Gets the metadata of the item this Block can drop. This method is called when the block gets destroyed. It
	 * returns the metadata of the dropped item based on the old metadata of the block.
	 */
	@Override
	public int damageDropped(IBlockState state)
	{
		if (state.getValue(BlockDesertMulti.TYPE) == EnumDesertBlocks.Darkstone)
		{
			return 1;
		}
		else
		{
			return (state.getValue(TYPE)).getMetadata();
		}
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
        return this.getDefaultState().withProperty(TYPE, EnumDesertBlocks.byMetadata(meta));
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