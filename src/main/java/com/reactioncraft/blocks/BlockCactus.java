package com.reactioncraft.blocks;

import com.reactioncraft.registration.instances.BlockIndex;
import net.minecraft.block.BlockBush;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

/**
 * Created on 12/27/17.
 */
public class BlockCactus extends BlockBush{

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
        return EnumPlantType.Desert;
    }


    @Override
    public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, IPlantable plantable) {
        return super.canSustainPlant(state, world, pos, direction, plantable);
    }

    @Override
    protected boolean canSustainBush(IBlockState state) {
        if(state.getBlock()== BlockIndex.dark_sand) return true;
        return super.canSustainBush(state);
    }
}
