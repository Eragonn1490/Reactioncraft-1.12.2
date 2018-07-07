package com.reactioncraft.common.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/**For blocks with metadata*/
public class ItemMulti extends ItemBlock
{
    public ItemMulti(Block par1)
    {
        super(par1);
        this.setHasSubtypes(true);
    }

    /**
     * Returns the metadata of the block which this Item (ItemBlock) can place
     */
    public int getMetadata(int m)
    {
        return m;
    }

    /**
     * Returns the unlocalized of this item. This version accepts an ItemStack so different stacks can have
     * differents based on their damage or NBT.
     */
    public String getUnlocalizedName(ItemStack par1ItemStack)
    {
        return super.getUnlocalizedName() + "." + par1ItemStack.getItemDamage();
    }
}