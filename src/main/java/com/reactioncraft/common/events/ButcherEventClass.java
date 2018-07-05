package com.reactioncraft.common.events;

import com.google.common.collect.ImmutableMap;
import com.reactioncraft.core.Logger;
import com.reactioncraft.registration.instances.ItemIndex;
import com.reactioncraft.utils.constants;
import net.minecraft.entity.*;
import net.minecraft.entity.boss.*;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.*;
import net.minecraft.init.Items;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.Map;
import javax.annotation.Nullable;


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