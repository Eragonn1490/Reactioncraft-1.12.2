package com.reactioncraft.items.chisels;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.common.ItemModelProvider;
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
        this.setRegistryName(new ResourceLocation(Reactioncraft.MODID, unlocalizedName));
    }
    
    @Override
	public void registerItemModel() 
	{
		Reactioncraft.proxy.registerItemRenderer(this, 0, this.name);
	}
}