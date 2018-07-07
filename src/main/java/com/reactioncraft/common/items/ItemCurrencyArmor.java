package com.reactioncraft.common.items;

import javax.annotation.Nullable;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.common.registration.instances.ItemIndex;
import com.reactioncraft.common.utils.ItemModelProvider;
import com.reactioncraft.common.utils.constants;

import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ItemCurrencyArmor extends ItemArmor implements ItemModelProvider
{
    protected int enchantability;
    protected String name;
    
    /** The EnumArmorMaterial used for this ItemArmor */
    private final ArmorMaterial material;

    public ItemCurrencyArmor(String name, ArmorMaterial par2EnumArmorMaterial, int par3, EntityEquipmentSlot par4)
    {
        super(par2EnumArmorMaterial, par3, par4);
        this.material = par2EnumArmorMaterial;
        this.enchantability = 30;
        this.name = name;
		this.setRegistryName(new ResourceLocation(constants.MODID, this.name));
		this.setUnlocalizedName(constants.MODID + "." + this.name);
    }
    
    @Override
	public void registerItemModel() 
	{
		Reactioncraft.proxy.registerItemRenderer(this, 0, this.name);
	}

    @Nullable
    @Override
    public String getArmorTexture(ItemStack itemstack, Entity entity, EntityEquipmentSlot slot, String type) {
        Item item=itemstack.getItem();
        return item!=ItemIndex.crown && item!=ItemIndex.kinglyChest && item!=ItemIndex.kinglyBoots ? (item==ItemIndex.kinglyLegs ? "reactioncraft:textures/models/armor/goldcrown_layer_2.png" : null) : "reactioncraft:textures/models/armor/goldcrown_layer_1.png";
    }
    
    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    public int getItemEnchantability()
    {
        return this.enchantability;
    }
}