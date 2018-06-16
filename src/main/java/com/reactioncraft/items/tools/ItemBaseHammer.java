package com.reactioncraft.items.tools;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.common.ItemModelProvider;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

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
        this.setRegistryName(new ResourceLocation(Reactioncraft.MODID, name));
		this.setUnlocalizedName(Reactioncraft.MODID + "." + name);
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