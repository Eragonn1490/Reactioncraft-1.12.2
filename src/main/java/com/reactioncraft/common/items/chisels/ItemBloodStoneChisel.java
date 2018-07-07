package com.reactioncraft.common.items.chisels;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.common.utils.ItemModelProvider;
import com.reactioncraft.common.utils.constants;

import net.minecraft.util.ResourceLocation;


public class ItemBloodStoneChisel extends ItemBaseChisel implements ItemModelProvider
{
    public ItemBloodStoneChisel(String unlocalizedName)
    {
        super(unlocalizedName);
        this.setMaxStackSize(1);
        this.setMaxDamage(4200);
        this.setCreativeTab(Reactioncraft.ReactioncraftItems);
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(new ResourceLocation(constants.MODID, unlocalizedName));
    }
    
    @Override
	public void registerItemModel() 
	{
		Reactioncraft.proxy.registerItemRenderer(this, 0, this.name);
	}
}