package com.reactioncraft;

import net.minecraft.item.ItemStack;

/**
 * Created on 12/21/17.
 */
public class Tools {

    public static void decreaseStackSize(ItemStack itemStack)
    {
        itemStack.setCount(itemStack.getCount()-1);
    }
}