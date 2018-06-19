package com.reactioncraft.items;

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
        String[] types = new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"};
        return super.getUnlocalizedName() + "." + types[par1ItemStack.getItemDamage()];
    }
}
