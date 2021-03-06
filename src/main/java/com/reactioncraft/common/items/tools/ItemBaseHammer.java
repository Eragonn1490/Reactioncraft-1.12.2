package com.reactioncraft.common.items.tools;

import java.util.List;

import javax.annotation.Nullable;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.common.utils.ItemModelProvider;
import com.reactioncraft.common.utils.constants;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ItemBaseHammer extends ItemSword implements ItemModelProvider
{
	protected String name;
	public int myReturnedAmt;
	
    public ItemBaseHammer(String name, int myReturnedAmt)
    {
        super(ToolMaterial.WOOD);
        this.name = name;
        this.setMaxStackSize(1);
        this.myReturnedAmt = myReturnedAmt;
        this.setMaxDamage(myReturnedAmt);
        this.setCreativeTab(Reactioncraft.ReactioncraftItems);
        this.setRegistryName(new ResourceLocation(constants.MODID, name));
		this.setUnlocalizedName(constants.MODID + "." + name);
    }
    
    @Override
    public void registerItemModel() 
    {
        Reactioncraft.proxy.registerItemRenderer(this, 0, this.name);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add("Uses: " + myReturnedAmt);
    }
}