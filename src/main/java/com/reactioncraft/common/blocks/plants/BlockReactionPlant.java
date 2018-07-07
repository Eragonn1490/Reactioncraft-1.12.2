package com.reactioncraft.common.blocks.plants;

import net.minecraft.block.BlockCrops;
import net.minecraft.item.Item;

/**
 * Created on 12/31/17.
 */
public abstract class BlockReactionPlant extends BlockCrops {

    @Override
    protected abstract Item getSeed();

    @Override
    protected abstract Item getCrop();
}
