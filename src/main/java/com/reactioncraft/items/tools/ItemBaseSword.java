package com.reactioncraft.items.tools;

import com.google.common.collect.ImmutableSet;
import com.reactioncraft.Reactioncraft;
import com.reactioncraft.common.ItemModelProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ResourceLocation;

import java.util.Set;

public class ItemBaseSword extends ItemSword implements ItemModelProvider
{
	protected String name;
	public ItemBaseSword(String unlocalizedName, ToolMaterial material)
	{
        super(material);
        this.name = unlocalizedName;
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(new ResourceLocation(Reactioncraft.MODID, unlocalizedName));
	}
	
	@Override
	public Set<String> getToolClasses(ItemStack stack) 
	{
		return ImmutableSet.of("sword");
	}
	
	@Override
    public void registerItemModel() 
    {
        Reactioncraft.proxy.registerItemRenderer(this, 0, this.name);
    }
}