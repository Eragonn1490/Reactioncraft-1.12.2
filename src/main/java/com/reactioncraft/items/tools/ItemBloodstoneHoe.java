package com.reactioncraft.items.tools;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.registration.instances.ItemIndex;
import net.minecraft.item.ItemStack;

public class ItemBloodstoneHoe extends ItemBasicHoe
{
    public ItemBloodstoneHoe(String var1, ToolMaterial var2)
    {
        super(var1, var2);
        this.setCreativeTab(Reactioncraft.ReactioncraftItems);
    }

    @Override
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
	{
		return par2ItemStack.getItem() == ItemIndex.ingotbloodstone || super.getIsRepairable(par1ItemStack, par2ItemStack);
	}
}