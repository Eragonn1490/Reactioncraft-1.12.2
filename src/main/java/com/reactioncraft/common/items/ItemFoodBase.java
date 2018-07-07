package com.reactioncraft.common.items;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.common.utils.ItemModelProvider;
import com.reactioncraft.common.utils.OreDictionaryInterface;
import com.reactioncraft.common.utils.constants;

import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;

public class ItemFoodBase extends ItemFood implements ItemModelProvider, OreDictionaryInterface
{

    public ItemFoodBase(String name, int amount, float saturation, boolean isWolfFood)
    {
        super(amount, saturation, isWolfFood);

        this.setRegistryName(new ResourceLocation(constants.MODID, name));
        this.setUnlocalizedName(constants.MODID + "." + name);
        this.setCreativeTab(Reactioncraft.Reactioncraftfood);
    }

    @Override
    public void initOreDict(String key, Item item) 
    {
        OreDictionary.registerOre(key, item);
    }

    @Override
    public void registerItemModel() 
    {
        Reactioncraft.proxy.registerItemRenderer(this, 0, getRegistryName().getResourcePath());
    }
}