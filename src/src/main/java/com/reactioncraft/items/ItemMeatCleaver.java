package com.reactioncraft.items;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.items.tools.ItemBasicSword;
import com.reactioncraft.registration.instances.ItemIndex;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;


public class ItemMeatCleaver extends ItemBasicSword
{
    public ItemMeatCleaver(String var1, ToolMaterial var2)
    {
        super(var1, var2);
        this.setCreativeTab(Reactioncraft.ReactioncraftItems);
        this.setMaxStackSize(1);
    }

    /**
     * Return whether this item is repairable in an anvil.
     */
    @Override
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
	{
        return par2ItemStack.getItem() == Items.IRON_INGOT || super.getIsRepairable(par1ItemStack, par2ItemStack);
    }
}