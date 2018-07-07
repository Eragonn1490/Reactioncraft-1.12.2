package com.reactioncraft.common.events;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.reactioncraft.common.registration.instances.ItemIndex;
import com.reactioncraft.common.utils.Logger;
import com.reactioncraft.common.utils.constants;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


public class ButcherEventClass
{
	static final String culture ="culture";

	private static Map<Object, Object> dropMap = ImmutableMap.builder()
			//Add new drops for mobs below
			.put(EntityPig.class,     ItemIndex.pork_chunk)
			.put(EntityChicken.class, ItemIndex.chicken_head)
			.put(EntityCow.class,     ItemIndex.beef_chunk)
			.put(EntityHorse.class,   ItemIndex.raw_horse)
			.put(EntityPlayerMP.class,  ItemIndex.raw_human)
			.put(EntityRabbit.class,     Items.AIR)//Replace with something?
			.put(EntityWitherSkeleton.class, Items.AIR) //Maybe Replace with Wither Rib ?

			//End of adding new mob drops.
			.build();

	@SubscribeEvent
	public void onEntityDrop(LivingDropsEvent event)
	{
		EntityLivingBase deadEntity = event.getEntityLiving();
		World world = deadEntity.getEntityWorld();
		BlockPos position = deadEntity.getPosition();

		double rand = event.getEntityLiving().getEntityWorld().rand.nextDouble();

		try
		{
			if (event.getSource().getDamageType().equals("player"))
			{
				ItemStack e = ((EntityLivingBase)event.getSource().getTrueSource()).getHeldItemMainhand();

				if (e.getItem() == ItemIndex.meat_cleaver && rand < 1.0D)
				{
					ItemStack drop = new ItemStack((Item)dropMap.get(deadEntity.getClass()));
					deadEntity.entityDropItem(drop, 0.0F);
				}
			}
		}
		catch (NullPointerException var6) {} //This line is an empty exception dont change.
	}


	@SubscribeEvent
	public void onModEntityDrop(LivingDropsEvent event)
	{
		EntityLivingBase deadEntity = event.getEntityLiving();
		World world = deadEntity.getEntityWorld();
		BlockPos position = deadEntity.getPosition();

		double rand = event.getEntityLiving().getEntityWorld().rand.nextDouble();

		if(constants.millenaire == true)
		{
			if (event.getSource().getDamageType().equals("player"))
			{
				try
				{
					if(((EntityLiving)deadEntity).getAttackTarget().getEntityData().getCompoundTag(culture) != null)
					{
						ItemStack e = ((EntityLivingBase)event.getSource().getTrueSource()).getHeldItemMainhand();

						if (e.getItem() == ItemIndex.meat_cleaver && rand < 1.0D)
						{
							ItemStack drop = new ItemStack(ItemIndex.raw_human);
							event.getEntityLiving().entityDropItem(drop, 0.0F);
							Logger.info("Name 0 is " + deadEntity.getName());
							Logger.info("Name 1 is " + deadEntity.getDisplayName());
							Logger.info("Name 2 is " + deadEntity.getCustomNameTag());
						}
					}
				}
				catch (NullPointerException var6) {} //This prevents console spam when checking entities
			}
		}
		else
		{
			event.isCanceled();
		}
	}
}