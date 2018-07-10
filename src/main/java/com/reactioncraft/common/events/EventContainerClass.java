package com.reactioncraft.common.events;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.reactioncraft.common.blocks.enums.EnumBookshelf;
import com.reactioncraft.common.registration.instances.BlockIndex;
import com.reactioncraft.common.registration.instances.ItemIndex;
import com.reactioncraft.common.registration.instances.PropertyIndex;
import com.reactioncraft.common.utils.constants;
import com.reactioncraft.api.ExclusionList;
import com.reactioncraft.common.blocks.BlockBookshelf;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntityPolarBear;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.fluids.IFluidBlock;
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
			.put(EntityBat.class,        ItemIndex.bones)//Maybe Replace with Bat Wing?
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
			.put(EntityWitherSkeleton.class, Items.AIR) //Maybe Replace with Wither Rib ?
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

				if(PropertyIndex.deleteWaterBlock == true)
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

	@SubscribeEvent
	public void onEntityDrop(LivingDropsEvent event)
	{
		EntityLivingBase entity = event.getEntityLiving();
		World world = entity.getEntityWorld();
		BlockPos position = entity.getPosition();

		double rand = event.getEntityLiving().getEntityWorld().rand.nextDouble();

		try
		{
			if (event.getSource().getDamageType().equals("player"))
			{
				ItemStack e = ((EntityLivingBase)event.getSource().getTrueSource()).getHeldItemMainhand();

				if (e.getItem() == ItemIndex.meat_cleaver && rand < 1.0D)
				{
					NBTTagCompound tag = new NBTTagCompound();
					entity.writeToNBT(tag);
					for(Object o : tag.getKeySet())
					{
						System.out.println((String) o);
					}
				}
			}
		}
		catch (NullPointerException var6) {} //This line is an empty exception dont change.
	}
}