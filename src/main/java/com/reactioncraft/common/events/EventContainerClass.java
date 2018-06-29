package com.reactioncraft.common.events;

import java.util.Map;

import javax.annotation.Nullable;
import com.google.common.collect.ImmutableMap;
import com.reactioncraft.Reactioncraft;
import com.reactioncraft.api.BoneDropMap;
import com.reactioncraft.blocks.*;
import com.reactioncraft.blocks.BlockBookshelf;
import com.reactioncraft.blocks.enums.*;
import com.reactioncraft.core.Logger;
import com.reactioncraft.registration.instances.*;
import com.reactioncraft.utils.constants;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.*;
import net.minecraft.init.*;
import net.minecraft.item.*;
import net.minecraft.stats.StatList;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.fluids.*;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventContainerClass
{
	static final String culture ="culture";

	private static Map<Object, Object> dropMap = ImmutableMap.builder()
			//Add new drops for mobs below
			.put(EntityPig.class,        ItemIndex.bones)
			.put(EntityChicken.class,    ItemIndex.bones)
			.put(EntityCow.class,        ItemIndex.bones)
			.put(EntityHorse.class,      ItemIndex.bones)
			.put(EntityZombie.class,     ItemIndex.bones)
			.put(EntityBat.class,        ItemIndex.bones)
			.put(EntityMooshroom.class,  ItemIndex.bones)
			.put(EntityOcelot.class,     ItemIndex.bones)
			.put(EntityRabbit.class,     ItemIndex.bones)
			.put(EntityPolarBear.class,  ItemIndex.bones)
			.put(EntityVillager.class,   ItemIndex.bones)
			.put(EntityPlayer.class,     ItemIndex.bones)
			.put(EntityPlayerMP.class,   ItemIndex.bones)
			.put(EntityCreeper.class,    ItemIndex.bones)
			.put(EntityPigZombie.class,  ItemIndex.bones)
			.put(EntityWitch.class,      ItemIndex.bones)
			//End of Vanilla mob drops.
			.build();

	@SubscribeEvent
	public void onEntityDropBones(LivingDropsEvent event)
	{
		EntityLivingBase deadEntity = event.getEntityLiving();
		World world = deadEntity.getEntityWorld();
		BlockPos position = deadEntity.getPosition();

		double rand = event.getEntityLiving().getEntityWorld().rand.nextDouble();

		try
		{
			if (event.getSource().getDamageType().equals("player"))
			{
				ItemStack drop = new ItemStack((Item)dropMap.get(deadEntity.getClass()));
				deadEntity.entityDropItem(drop, 0.0F);
			}

			if(constants.millenaire == true)
			{
				if(((EntityLiving)deadEntity).getAttackTarget().getEntityData().getCompoundTag(culture) != null)
				{
					ItemStack drop = new ItemStack(ItemIndex.bones);
					event.getEntityLiving().entityDropItem(drop, 0.0F);
				}
			}

		}
		catch (NullPointerException var6) {} //This line is an empty exception dont change.
	}

	@SubscribeEvent
	public void onPlayerInteractEvent(PlayerInteractEvent event)
	{ //Change Bookcase into Reactioncraft removable Book version
		ItemStack book = new ItemStack(Items.BOOK);
		EntityPlayer playerIn = event.getEntityPlayer();
		BlockPos pos = event.getPos();
		World worldIn = event.getWorld();
		IBlockState state = worldIn.getBlockState(pos);


		if(state.getBlock().getDefaultState() == Blocks.BOOKSHELF.getDefaultState())
		{
			if(event.getEntityPlayer().getHeldItemMainhand().isEmpty())
			{
				//worldIn.setBlockState(pos, Blocks.BONE_BLOCK.getDefaultState());
				//worldIn.setBlockState(pos, BlockIndex.bookcases.getDefaultState().withProperty(TYPE));
				worldIn.setBlockState(pos, BlockIndex.bookcases.getDefaultState().withProperty(BlockBookshelf.TYPE, EnumBookshelf.TWO_THIRDS));
				playerIn.inventory.addItemStackToInventory(book);
				event.hasResult();
			}
		}
	}


	@SubscribeEvent
	public void onItemUsedOnWater(RightClickBlock event) 
	{
		//Modified From https://github.com/Darkhax-Minecraft/Thirsty-Bottles/blob/master/src/main/java/net/darkhax/tb/ThirstyBottles.java
		if (event.getWorld().isRemote)
			return;


		if (event.getItemStack() != null && event.getItemStack().getItem() == Items.BOWL) 
		{
			BlockPos pos = new BlockPos(event.getHitVec());
			IBlockState state = event.getWorld().getBlockState(pos);
			EntityPlayer player = event.getEntityPlayer();


			if (state == null) 
				return;

			if (state.getMaterial() == Material.WATER && (state.getBlock() instanceof IFluidBlock || state.getBlock() instanceof BlockLiquid) && Blocks.WATER.canCollideCheck(state, true)) 
			{
				event.getWorld().playSound(player, player.posX, player.posY, player.posZ, SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.NEUTRAL, 1.0F, 1.0F);

				event.getEntityPlayer().setHeldItem(event.getHand(), transformBottle(event.getItemStack(), event.getEntityPlayer(), new ItemStack(ItemIndex.bowlwater)));

				if(constants.deleteWaterBlock == true)
				{
					event.getWorld().setBlockToAir(pos);
				}
			}
		}
	}


	private ItemStack transformBottle(ItemStack input, EntityPlayer player, ItemStack stack) 
	{
		input.shrink(1);

		player.addStat(StatList.getObjectUseStats(input.getItem()));

		if (input.getCount() < 1) 
		{
			return stack;
		} 
		else 
		{
			if (!player.inventory.addItemStackToInventory(stack)) 
			{
				player.dropItem(stack, false);
			}
			return input;
		}
	}

	//@SubscribeEvent
	//public void onModEntityGetCaught(PlayerInteractEvent event){}
}