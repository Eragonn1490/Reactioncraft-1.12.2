package com.reactioncraft.common.items;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;


public class ItemPieceHilt extends ItemBase
{
    public ItemPieceHilt(String string)
    {
        super(string);
//        this.setCreativeTab(null);
        this.setMaxStackSize(1);
    }

    @Override
    public void addInformation(ItemStack itemStack, @Nullable World worldIn, List<String> list, ITooltipFlag flagIn) {
        if (itemStack.getTagCompound() != null)
        {
            list.add("Level: " + itemStack.getTagCompound().getInteger("str1"));
        }
        else
        {
            list.add("Please craft to see results");
        }
    }
}
