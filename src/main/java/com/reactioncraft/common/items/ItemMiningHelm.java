package com.reactioncraft.common.items;

import com.reactioncraft.common.utils.constants;

import net.minecraft.inventory.EntityEquipmentSlot;

public class ItemMiningHelm extends ItemExtraCrowns
{
	protected int enchantability;
    protected String name;
    
    /** The EnumArmorMaterial used for this ItemArmor */
    private final ArmorMaterial material;

    public ItemMiningHelm(String name, ArmorMaterial par2EnumArmorMaterial, int par3, EntityEquipmentSlot par4)
    {
        super(name, par2EnumArmorMaterial, par3, par4);
        this.material = par2EnumArmorMaterial;
        this.enchantability = 30;
        this.name = name;
		this.setUnlocalizedName(constants.MODID + "." + this.name);
		this.setMaxStackSize(1);
    }
    
	
    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    public int getItemEnchantability()
    {
        return this.enchantability;
    }
}