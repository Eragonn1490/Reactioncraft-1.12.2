package com.reactioncraft.items;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.utils.constants;
import net.minecraft.block.Block;
import net.minecraft.item.ItemSeeds;

/**
 * Created on 12/31/17.
 */
public class ItemBaseSeed extends ItemSeeds {
    public ItemBaseSeed(Block crops, Block soil,String unl_name) {
        super(crops, soil);
        setUnlocalizedName(unl_name);
    }

    @Override
    public String getUnlocalizedName() {
        return constants.MODID+"."+super.getUnlocalizedName();
    }
}
