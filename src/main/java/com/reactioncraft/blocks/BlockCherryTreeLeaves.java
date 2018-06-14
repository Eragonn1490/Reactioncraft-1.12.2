package com.reactioncraft.blocks;

import com.google.common.collect.Lists;
import com.reactioncraft.Reactioncraft;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import javax.annotation.Nonnull;
import java.util.List;

public class BlockCherryTreeLeaves extends BlockLeaves
{

    public BlockCherryTreeLeaves()
    {
        super();
        this.setCreativeTab(Reactioncraft.Reactioncraft);
        setDefaultState(blockState.getBaseState().withProperty(CHECK_DECAY,true).withProperty(DECAYABLE,true));
    }

    //NOTICE order of properties must match the order in blockstate file
    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this,CHECK_DECAY,DECAYABLE);
    }

    @Override
    public BlockPlanks.EnumType getWoodType(int meta) {
        return BlockPlanks.EnumType.BIRCH;
    }


    @Override
    public List<ItemStack> onSheared(@Nonnull ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
        return Lists.newArrayList(new ItemStack(this));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return 0;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        IBlockState sideste=blockAccess.getBlockState(pos.offset(side));
        return sideste.getBlock() != Blocks.AIR.getDefaultState();
    }

    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }


}
