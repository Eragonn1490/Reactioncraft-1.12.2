package com.reactioncraft.blocks;

import com.reactioncraft.registration.instances.ItemIndex;
import net.minecraft.item.Item;

/**
 * Created on 12/31/17.
 */
public class BlockCornPlant extends BlockReactionPlant {

    @Override
    protected Item getSeed() {
        return ItemIndex.cornSeed;
    }

    @Override
    protected Item getCrop() {
        return ItemIndex.rawcorn;
    }
}
