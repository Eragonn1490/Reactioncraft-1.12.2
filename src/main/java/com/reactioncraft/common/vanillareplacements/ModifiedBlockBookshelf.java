package com.reactioncraft.common.vanillareplacements;

import java.util.Random;

import com.reactioncraft.common.blocks.BlockBase;
import com.reactioncraft.common.blocks.enums.EnumBookshelf;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class ModifiedBlockBookshelf extends BlockBase
{
	public static final PropertyEnum<EnumBookshelf> TYPE = PropertyEnum.<EnumBookshelf>create("type", EnumBookshelf.class);
	
    public ModifiedBlockBookshelf()
    {
        super(Material.WOOD);
        this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    }
 
    //Changed To use forge event. just left this here as an example
    
    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random random)
    {
        return 3;
    }

    /**
     * Get the Item that this Block should drop when harvested.
     */
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Items.BOOK;
    }
}