package com.reactioncraft.common.blocks.plants;

import java.util.Random;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.common.instances.BlockIndex;

import net.minecraft.block.BlockLog;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

public class BlockCherryTreeLog extends BlockLog implements IPlantable
{


    public BlockCherryTreeLog()
    {
        super();
        this.setCreativeTab(Reactioncraft.Reactioncraft);
        this.setHarvestLevel("axe", 0);
    }



    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this,LOG_AXIS);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int i = 0;
        BlockLog.EnumAxis enumfacing$axis = state.getValue(LOG_AXIS);

        if (enumfacing$axis == EnumAxis.X)
        {
            i |= 4;
        }
        else if (enumfacing$axis == EnumAxis.Z)
        {
            i |= 8;
        }

        return i;
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        EnumAxis enumfacing$axis = EnumAxis.Y;
        int i = meta & 12;

        if (i == 4)
        {
            enumfacing$axis = EnumAxis.X;
        }
        else if (i == 8)
        {
            enumfacing$axis = EnumAxis.Z;
        }

        return this.getDefaultState().withProperty(LOG_AXIS, enumfacing$axis);
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    @Override
    public int quantityDropped(Random par1Random)
    {
        return 1;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(BlockIndex.cherrywood);
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
        return EnumPlantType.Desert;
    }

    @Override
    public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
        return getDefaultState();
    }
}
