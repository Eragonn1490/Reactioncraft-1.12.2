package com.reactioncraft.common.blocks;

import com.reactioncraft.common.instances.BlockIndex;
import com.reactioncraft.common.instances.ItemIndex;

import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Mirror;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockBaseDoor extends BlockDoor
{
    public BlockBaseDoor(Material materialIn)
    {
        super(materialIn);
    }

    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(this.getItem());
    }

    private Item getItem()
    {
        return this == BlockIndex.ironBookcasedoor ? ItemIndex.ironBookcasedoor :   (this == BlockIndex.woodenBookcase ? ItemIndex.woodenBookcasedoor : 
        	   this == BlockIndex.ironscrollcasedoor ? ItemIndex.ironBookcasedoor : (this == BlockIndex.scrollcasedoor ? ItemIndex.woodenBookcasedoor :
        	  (this == BlockIndex.cherrydoor ? ItemIndex.cherry_door : ItemIndex.cherry_door)));
    }

    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

    /**
     * Returns the blockstate with the given mirror of the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        return mirrorIn == Mirror.NONE ? state : state.withRotation(mirrorIn.toRotation(state.getValue(FACING))).cycleProperty(HINGE);
    }
}