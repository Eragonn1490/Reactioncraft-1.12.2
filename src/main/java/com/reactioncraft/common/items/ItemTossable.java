package com.reactioncraft.common.items;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.common.entities.EntityMap;
import com.reactioncraft.common.entities.EntityShipBottled;
import com.reactioncraft.common.instances.ItemIndex;
import com.reactioncraft.common.utils.ItemModelProvider;
import com.reactioncraft.common.utils.constants;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemTossable extends Item implements ItemModelProvider
{
	protected String name;
	
    public ItemTossable(String name)
    {
        this.maxStackSize = 16;
        this.name = name;
		this.setRegistryName(new ResourceLocation(constants.MODID, this.name));
		this.setUnlocalizedName(constants.MODID + "." + this.name);
		this.setCreativeTab(Reactioncraft.ReactioncraftItems);
    }

    @Override
	public void registerItemModel() 
	{
		Reactioncraft.proxy.registerItemRenderer(this, 0, this.name);
	}

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack itemStackIn=playerIn.getHeldItem(handIn);
        if (!playerIn.capabilities.isCreativeMode)
        {
            itemStackIn.shrink(1);
        }

        worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        if (!worldIn.isRemote)
        {
            if(itemStackIn.getItem()==ItemIndex.mapinabottle)
            {
                EntityMap entityMap = new EntityMap(worldIn, playerIn);
                entityMap.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.0F, 1.0F);
                worldIn.spawnEntity(entityMap);
            }
            if(itemStackIn.getItem()==ItemIndex.shipinabottle)
            {
                EntityShipBottled entityship = new EntityShipBottled(worldIn, playerIn);
                entityship.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.0F, 1.0F);
                worldIn.spawnEntity(entityship);
            }
        }

        playerIn.addStat(StatList.getObjectUseStats(this));
        return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
    }
}