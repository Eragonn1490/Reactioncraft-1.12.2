package com.reactioncraft.items.tools;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.common.ItemModelProvider;
import net.minecraft.item.ItemHoe;
import net.minecraft.util.ResourceLocation;

public class ItemBasicHoe extends ItemHoe implements ItemModelProvider
{
    public ItemBasicHoe(String var1, ToolMaterial var2)
    {
        super(var2);
		this.setRegistryName(new ResourceLocation(Reactioncraft.MODID, var1));
        this.setUnlocalizedName(var1);
        this.setCreativeTab(Reactioncraft.ReactioncraftItems);
    }
    
    @Override
    public void registerItemModel() 
    {
        Reactioncraft.proxy.registerItemRenderer(this, 0, getRegistryName().getResourcePath());
    }
}