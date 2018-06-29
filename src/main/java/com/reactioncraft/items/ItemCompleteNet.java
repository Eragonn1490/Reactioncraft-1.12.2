package com.reactioncraft.items;

import com.google.common.collect.ImmutableSet;
import com.reactioncraft.Reactioncraft;
import com.reactioncraft.common.ItemModelProvider;
import com.reactioncraft.core.Logger;
import com.reactioncraft.registration.instances.ItemIndex;
import com.reactioncraft.utils.constants;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

public class ItemCompleteNet extends ItemSword implements ItemModelProvider
{
	protected final String name;

	public ItemCompleteNet(String name, ToolMaterial mat)
	{
		super(mat);
		this.name = name;
		this.setRegistryName(new ResourceLocation(constants.MODID, name));
		this.setUnlocalizedName(constants.MODID + "." + name);
		this.setMaxStackSize(1);
		this.setCreativeTab(Reactioncraft.ReactioncraftItems);
	}

	@Override
	public int getMaxDamage(ItemStack stack)
	{
		NBTTagCompound compound = stack.getTagCompound();

		if(compound != null)
		{

			int hiltLevel = compound.getInteger("hilt");
			int netLevel  = compound.getInteger("net");

			int returnedAmt = (10 * (hiltLevel + netLevel));

			return returnedAmt;
		}
		else
		{
			return 1;
		}
	}


	@Override
	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn)
    {
		NBTTagCompound compound = stack.getTagCompound();

		if (compound != null)
		{
			int hiltLevel = compound.getInteger("hilt");
			int netLevel  = compound.getInteger("net");

			int returnedAmt = (10 * (hiltLevel + netLevel));

			ItemIndex.complete_net.setMaxDamage(returnedAmt);
		}
    }


	@Override
	public Set<String> getToolClasses(ItemStack stack) {
		return ImmutableSet.of("pickaxe", "axe", "shovel", "sword");
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
	{
		if (!player.world.isRemote && entity instanceof EntityLiving && !Reactioncraft.exclusionList.isExcluded(entity))
		{
			String entityId = entity.getName().toString();

			NBTTagCompound entityTag = new NBTTagCompound();
			entity.writeToNBTOptional(entityTag);
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

			if(entity instanceof EntityVillager)
			{
				String professionName = ((EntityVillager) entity).getProfessionForge().getRegistryName().toString();
				int    profession = ((EntityVillager) entity).getProfession();
				entityTag.setInteger("Profession", profession);
				entityTag.setString ("ProfessionName", professionName);
			}


			ItemStack is = new ItemStack(ItemIndex.caught);
			NBTTagCompound nbt = new NBTTagCompound();
			nbt.setTag("EntityData", entityTag);
			nbt.setString("name", entityId);


			is.setTagCompound(nbt);
			player.dropItem(is, false);

			if(this.name.equals("creative_net"))
			{
				entity.setDead();
				return true;
			}
			else if(!this.name.equals("creative_net"))
			{
				entity.setDead();
				stack.attemptDamageItem(1,itemRand, (EntityPlayerMP) player);

				if (stack.getItemDamage() >= stack.getMaxDamage())
				{
					stack.shrink(1);
				}
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}

	/**
	 * Returns True is the item is renderer in full 3D when held.
	 */
	@SideOnly(Side.CLIENT)
	public boolean isFull3D()
	{
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) 
	{
		if (stack.getTagCompound() != null)
		{
			tooltip.add("Hilt Level: " + stack.getTagCompound().getInteger("hilt"));
			tooltip.add("Net Level: "  + stack.getTagCompound().getInteger("net"));
			int returnedAmt = stack.getMaxDamage();
			tooltip.add("Uses: " + returnedAmt );
		}
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	@Override
	public void registerItemModel()
	{
		Reactioncraft.proxy.registerItemRenderer(this, 0, this.name);
	}
}