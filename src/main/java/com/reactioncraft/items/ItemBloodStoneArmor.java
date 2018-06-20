package com.reactioncraft.items;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.common.ItemModelProvider;
import com.reactioncraft.registration.MaterialIndex;
import com.reactioncraft.registration.instances.ItemIndex;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

/**
 * Created on 12/25/17.
 */
public class ItemBloodStoneArmor extends ItemArmor implements ItemModelProvider{
    public ItemBloodStoneArmor( int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(MaterialIndex.EnumBloodstoneArmor, renderIndexIn, equipmentSlotIn);
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        Item item=stack.getItem();
        if(item== ItemIndex.bloodstonehelm || item==ItemIndex.bloodstonechest || item==ItemIndex.bloodstoneboots) return "reactioncraft:textures/models/armor/bloodstone_1.png";
        return "reactioncraft:textures/models/armor/bloodstone_2.png";
    }

    @Override
    public void registerItemModel() {
        Reactioncraft.proxy.registerItemRenderer(this,0,getRegistryName().getResourcePath());
    }
}
