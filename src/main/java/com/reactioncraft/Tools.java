package com.reactioncraft;


import net.minecraft.item.ItemStack;

/**
 * Created on 12/21/17.
 */
public class Tools {

    /**Tests whether stacks are equal except size*/
    public static boolean areItemTypesEqual(ItemStack one, ItemStack two)
    {
        if(!one.isEmpty() && !two.isEmpty())
        {
            if(one.getItem()==two.getItem() && one.getItemDamage()==two.getItemDamage() &&
                    one.getItem().getRegistryName().equals(two.getItem().getRegistryName())
                    && (ItemStack.areItemStackTagsEqual(one,two)))
                return true;
        }
        return false;
    }

}
