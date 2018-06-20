package com.reactioncraft.items;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.common.ItemModelProvider;
import com.reactioncraft.utils.constants;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class ItemBase extends Item implements ItemModelProvider
{

	public ItemBase(String name) 
	{
		this.setRegistryName(new ResourceLocation(constants.MODID, name));
		this.setUnlocalizedName(constants.MODID + "." +name);
		this.setCreativeTab(Reactioncraft.ReactioncraftItems);
	}

	@Override
	public void registerItemModel() 
	{
		Reactioncraft.proxy.registerItemRenderer(this, 0, this.getRegistryName().getResourcePath());
	}

	@Override
	public ItemBase setCreativeTab(CreativeTabs tab) 
	{
		super.setCreativeTab(tab);
		return this;
	}
}