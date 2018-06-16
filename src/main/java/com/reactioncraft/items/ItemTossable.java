package com.reactioncraft.items;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.common.ItemModelProvider;
import com.reactioncraft.entities.EntityMap;
import com.reactioncraft.registration.instances.ItemIndex;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class ItemTossable extends Item implements ItemModelProvider
{
	protected String name;
	
    public ItemTossable(String name)
    {
        this.maxStackSize = 16;
        this.name = name;
		this.setRegistryName(new ResourceLocation(Reactioncraft.MODID, this.name));
		this.setUnlocalizedName(Reactioncraft.MODID + "." + this.name);
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

        worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_EGG_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        if (!worldIn.isRemote)
        {


            if(itemStackIn.getItem()==ItemIndex.mapinabottle)
            {
                EntityMap entityMap = new EntityMap(worldIn, playerIn);
                entityMap.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
                worldIn.spawnEntity(entityMap);
            }
            else {

                EntityEgg entityegg = new EntityEgg(worldIn, playerIn);
                entityegg.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
                worldIn.spawnEntity(entityegg);
            }
        }

        playerIn.addStat(StatList.getObjectUseStats(this));
        return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
    }


}