package com.reactioncraft.common.items.tools;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.common.utils.ItemModelProvider;
import com.reactioncraft.common.utils.constants;

import net.minecraft.item.ItemPickaxe;
import net.minecraft.util.ResourceLocation;

public class ItemBasicPickaxe extends ItemPickaxe implements ItemModelProvider
{
    public ItemBasicPickaxe(String var1, ToolMaterial var2)
    {
        super(var2);
		this.setRegistryName(new ResourceLocation(constants.MODID, var1));
        this.setUnlocalizedName(var1);
        this.setCreativeTab(Reactioncraft.ReactioncraftItems);
    }
    
    public void registerItemModel() 
	{
		Reactioncraft.proxy.registerItemRenderer(this, 0, getRegistryName().getResourcePath());
	}
}