package com.reactioncraft.items;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.items.tools.ItemBasicSword;
import com.reactioncraft.registration.instances.ItemIndex;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;


public class ItemBat extends ItemBasicSword
{
    public ItemBat(String var1, ToolMaterial var2)
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
    	ItemStack planks = new ItemStack(Blocks.PLANKS);
        return par2ItemStack.getItem() == planks.getItem() || super.getIsRepairable(par1ItemStack, par2ItemStack);
    }
}