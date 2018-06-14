package com.reactioncraft.blocks;

import com.reactioncraft.registration.instances.ItemIndex;
import net.minecraft.item.Item;

public class BlockAncientPlant extends BlockReactionPlant
{

    public BlockAncientPlant()
    {
        super();
//        float f = 0.5F;
//        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
    }

    @Override
    protected Item getSeed() {
        return ItemIndex.ancientSeeds;
    }

    @Override
    protected Item getCrop() {
        return ItemIndex.ancientFruit;
    }
}
