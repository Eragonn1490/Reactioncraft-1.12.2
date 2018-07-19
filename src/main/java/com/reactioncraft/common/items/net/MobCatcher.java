package com.reactioncraft.common.items.net;

import java.util.Random;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.api.ExclusionList;
import com.reactioncraft.common.instances.ItemIndex;
import com.reactioncraft.common.utils.Logger;
import com.reactioncraft.common.utils.constants;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentTranslation;

public class MobCatcher 
{
	static Random rand = new Random();
	static final String culture ="culture";
	static boolean result;
	
	
	public static boolean catchEntities(ItemStack stack, EntityPlayer player, Entity entity) 
	{
		NBTTagCompound entityTag = new NBTTagCompound();
		entity.writeToNBTOptional(entityTag);
		String entityId = entity.getName().toString();
		ItemStack is = new ItemStack(ItemIndex.caught);

		if(Reactioncraft.exclusionList.isExcluded(entityId))//Check if entity was excluded
		{
			//Logger.info("Excluded by exclusion list");
			result = false;
			return result;
		}

		if(!Reactioncraft.exclusionList.isExcluded(entityId))//if entity wasnt excluded
		{
			if (entity instanceof EntityVillager)//check if its a villager
			{
				//Logger.info("[1] Entity is a villager");
				createCaughtVillagerEntity(entity, player, stack);
				result = true;
				return result;
			}

			if(constants.millenaire == false) //check mod
			{
				if (entity instanceof EntityLiving)//create item stack
				{
					createCaughtEntity(entity, player, stack);
					result = true;	
					return result;
				}
			}

			if(constants.millenaire == true) //Forcibly Exclude Millenaire Mobs from being caught to fix a bug on millenaires
			{
				if(entity.getEntityData().hasKey(culture) != entityTag.hasKey(culture))
				{
					player.sendMessage(new TextComponentTranslation("Cannot catch millenaire villagers!"));
					result = false;
					return result;
				}

				if(entity.getEntityData().hasKey(culture) == entityTag.hasKey(culture))
				{
					createCaughtEntity(entity, player, stack);
					result = true;	
					return result;
				}
			}
		}

		else
		{ 
			result = false; 
			return result;
		}
		return result;
	}

	private static void createCaughtEntity(Entity entity, EntityPlayer player, ItemStack stack) 
	{
		NBTTagCompound entityTag = new NBTTagCompound();
		entity.writeToNBTOptional(entityTag);
		String entityId = entity.getName().toString();
		ItemStack is = new ItemStack(ItemIndex.caught);

		entityTag.removeTag("Pos");
		entityTag.removeTag("Motion");
		entityTag.removeTag("Rotation");
		entityTag.removeTag("Dimension");
		entityTag.removeTag("PortalCooldown");
		entityTag.removeTag("InLove");
		entityTag.removeTag("HurtTime");
		entityTag.removeTag("DeathTime");
		entityTag.removeTag("AttackTime");
		entityTag.setString("name", entityId);


		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setTag("EntityData", entityTag);
		nbt.setString("name", entityId);


		is.setTagCompound(nbt);
		player.dropItem(is, false);

		if(stack.getUnlocalizedName().equals(constants.MODID + "." + "creative_net"))
		{
			entity.setDead();
			return;
		}

		else if(!stack.getUnlocalizedName().equals(constants.MODID + "." + "creative_net"))
		{
			entity.setDead();

			stack.damageItem(1, player);

			if (stack.getItemDamage() >= stack.getMaxDamage())
			{
				stack.shrink(1);
			}
			return;
		}
		else
		{
			return;
		}
	}

	private static void createCaughtVillagerEntity(Entity entity, EntityPlayer player, ItemStack stack) 
	{
		NBTTagCompound entityTag = new NBTTagCompound();
		NBTTagCompound villagerTag = new NBTTagCompound();
		entity.writeToNBTOptional(entityTag);
		entity.writeToNBT(villagerTag);
		String entityId = entity.getName().toString();
		ItemStack is = new ItemStack(ItemIndex.caught);
		
	
		entityTag.removeTag("Pos");
		entityTag.removeTag("Motion");
		entityTag.removeTag("Rotation");
		entityTag.removeTag("Dimension");
		entityTag.removeTag("PortalCooldown");
		entityTag.removeTag("InLove");
		entityTag.removeTag("HurtTime");
		entityTag.removeTag("DeathTime");
		entityTag.removeTag("AttackTime");
		entityTag.setString("name", entityId);
		
		//Store Villager Data
		villagerTag.setInteger("Profession", ((EntityVillager) entity).getProfession());
		villagerTag.setString("ProfessionName", ((EntityVillager) entity).getProfessionForge().getRegistryName().toString());

		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setTag("EntityData", entityTag);
		nbt.setTag("VillagerData", villagerTag);
		nbt.setString("name", entityId);


		is.setTagCompound(nbt);
		player.dropItem(is, false);

		if(stack.getUnlocalizedName().equals(constants.MODID + "." + "creative_net"))
		{
			entity.setDead();
			return;
		}

		else if(!stack.getUnlocalizedName().equals(constants.MODID + "." + "creative_net"))
		{
			entity.setDead();

			stack.damageItem(1, player);

			if (stack.getItemDamage() >= stack.getMaxDamage())
			{
				stack.shrink(1);
			}
			return;
		}
		else
		{
			return;
		}
	}
}