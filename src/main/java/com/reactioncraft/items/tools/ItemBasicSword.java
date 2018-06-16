package com.reactioncraft.items.tools;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.common.ItemModelProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ResourceLocation;

public class ItemBasicSword extends ItemSword implements ItemModelProvider
{
	protected String name;
    public ItemBasicSword(String var1, ToolMaterial var2)
    {
        super(var2);
        this.name = var1;
		this.setRegistryName(new ResourceLocation(Reactioncraft.MODID, name));
        this.setUnlocalizedName(Reactioncraft.MODID+"."+name);
        this.setCreativeTab(Reactioncraft.ReactioncraftItems);
    }
    
    @Override
    public void registerItemModel() 
	{
		Reactioncraft.proxy.registerItemRenderer(this, 0, this.name);
	}

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.BLOCK;
    }

    @Override
    public boolean canHarvestBlock(IBlockState state, ItemStack stack) {
        return state.getBlock()==Blocks.WEB;
    }


    /**
     * Returns true if players can use this item to affect the world (e.g. placing blocks, placing ender eyes in portal)
     * when not in creative
     */
    public boolean canItemEditBlocks()
    {
        return false;
    }
}
