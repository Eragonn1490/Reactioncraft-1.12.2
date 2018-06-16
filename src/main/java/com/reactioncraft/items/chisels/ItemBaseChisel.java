package com.reactioncraft.items.chisels;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.common.ItemModelProvider;
import net.minecraft.item.ItemSword;

public class ItemBaseChisel extends ItemSword implements ItemModelProvider
{
	protected String name;
	
    public ItemBaseChisel(String name)
    {
        super(ToolMaterial.WOOD);
        this.name = name;
        this.setMaxStackSize(1);
        this.setMaxDamage(10);
        this.setCreativeTab(Reactioncraft.ReactioncraftItems);
        this.setNoRepair();
    }

    @Override
    public String getUnlocalizedName() {
        return "item."+Reactioncraft.MODID+"."+getRegistryName().getResourcePath();
    }

    /**
     * Returns true if players can use this item to affect the world (e.g. placing blocks, placing ender eyes in portal)
     * when not in creative
     */
    public boolean canItemEditBlocks()
    {
        return false;
    }

    @Override
    public void registerItemModel() 
    {
        Reactioncraft.proxy.registerItemRenderer(this, 0, this.name);
    }
}