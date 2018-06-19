package com.reactioncraft.items;


import com.reactioncraft.Reactioncraft;
import com.reactioncraft.common.ItemModelProvider;
import com.reactioncraft.items.tools.ItemBaseSword;

public class ItemKnife extends ItemBaseSword implements ItemModelProvider
{
	protected String name;
	
    public ItemKnife(String name)
    {
        super(name, ToolMaterial.IRON);
        this.name = name;
        this.setMaxStackSize(1);
        this.setMaxDamage(25);
        this.setCreativeTab(Reactioncraft.ReactioncraftItems);
        this.setNoRepair();
        
    }

    /**
     * Returns true if players can use this item to affect the world (e.g. placing blocks, placing ender eyes in portal)
     * when not in creative
     */
    public boolean canItemEditBlocks()
    {
        return false;
    }
    
    public void registerItemModel() 
	{
		Reactioncraft.proxy.registerItemRenderer(this, 0, this.name);
	}
}