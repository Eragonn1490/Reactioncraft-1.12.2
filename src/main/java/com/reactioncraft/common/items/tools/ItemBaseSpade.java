package com.reactioncraft.common.items.tools;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.common.utils.ItemModelProvider;
import com.reactioncraft.common.utils.constants;

import net.minecraft.item.ItemSpade;
import net.minecraft.util.ResourceLocation;

public class ItemBaseSpade extends ItemSpade implements ItemModelProvider
{ 
	protected String name;
    public ItemBaseSpade(String var1, ToolMaterial var2)
    {
        super(var2);
        this.name = var1;
		this.setRegistryName(new ResourceLocation(constants.MODID, name));
        this.setUnlocalizedName(name);
        this.setCreativeTab(Reactioncraft.ReactioncraftItems);
    }
    
    @Override
    public void registerItemModel() 
    {
        Reactioncraft.proxy.registerItemRenderer(this, 0, this.name);
    }
}