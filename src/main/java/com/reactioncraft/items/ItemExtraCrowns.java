package com.reactioncraft.items;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.common.ItemModelProvider;
import com.reactioncraft.registration.instances.ItemIndex;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class ItemExtraCrowns extends ItemArmor implements ItemModelProvider
{
    protected int enchantability;
    protected String name;
    
    /** The EnumArmorMaterial used for this ItemArmor */
    private final ArmorMaterial material;

    public ItemExtraCrowns(String name, ArmorMaterial par2EnumArmorMaterial, int par3, EntityEquipmentSlot par4)
    {
        super(par2EnumArmorMaterial, par3, par4);
        this.material = par2EnumArmorMaterial;
        this.enchantability = 30;
        this.name = name;
		this.setRegistryName(new ResourceLocation(Reactioncraft.MODID, this.name));
		this.setUnlocalizedName(Reactioncraft.MODID + "." + this.name);
    }
    
    @Override
	public void registerItemModel() 
	{
		Reactioncraft.proxy.registerItemRenderer(this, 0, this.name);
	}

    @Nullable
    @Override
    public String getArmorTexture(ItemStack itemstack, Entity entity, EntityEquipmentSlot slot, String type) {
        ItemStack Crown = new ItemStack(ItemIndex.diamondcrown);
        ItemStack KinglyChest = new ItemStack(ItemIndex.kinglyChest);
        ItemStack KinglyBoots = new ItemStack(ItemIndex.kinglyBoots);
        ItemStack KinglyLegs = new ItemStack(ItemIndex.kinglyLegs);

        return itemstack != Crown && itemstack != KinglyChest && itemstack != KinglyBoots ? (itemstack == KinglyLegs ? "reactioncraft:textures/models/armor/diamondcrown_layer_2.png" : null) : "reactioncraft:textures/models/armor/diamondcrown_layer_1.png";
    }

    
    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    public int getItemEnchantability()
    {
        return this.enchantability;
    }
}