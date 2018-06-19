package com.reactioncraft.items.tools;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.registration.instances.ItemIndex;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;


public class ItemBloodstoneAxe extends ItemBaseAxe
{
	public ItemBloodstoneAxe(String var1, ToolMaterial var2)
	{
		super(var1, var2);
		this.setCreativeTab(Reactioncraft.ReactioncraftItems);
	}

	/**
	 * Return whether this item is repairable in an anvil.
	 */
	@Override
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
	{
		return par2ItemStack.getItem() == ItemIndex.ingotbloodstone || super.getIsRepairable(par1ItemStack, par2ItemStack);
	}

	@Override
	public float getStrVsBlock(ItemStack stack, IBlockState state)
	{
		Material material = state.getMaterial();
		return material != Material.WOOD && material != Material.PLANTS && material != Material.VINE ? super.getStrVsBlock(stack, state) : this.getDestroySpeed(stack,state);
	}
}