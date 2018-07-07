package com.reactioncraft.common.items;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.common.items.tools.ItemBasicSword;

import net.minecraft.init.Items;
import net.minecraft.item.Item.ToolMaterial;
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
     * Returns true if players can use this item to affect the world (e.g. placing blocks, placing ender eyes in portal)
     * when not in creative
     */
    public boolean canItemEditBlocks()
    {
        return false;
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