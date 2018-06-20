package com.reactioncraft.common.events;

import java.util.Map;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableMap;
import com.reactioncraft.blocks.BlockBookshelf;
import com.reactioncraft.blocks.enums.EnumBookshelf;
import com.reactioncraft.core.Logger;
import com.reactioncraft.registration.instances.BlockIndex;
import com.reactioncraft.registration.instances.ItemIndex;
import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.*;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventContainerClass
{
	static final String culture ="Variation";

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

			//End of adding new mob drops.
			.build();
	
	@SuppressWarnings("unused")
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
		}
		catch (NullPointerException var6) {} //This line is an empty exception dont change.
	}

	@SuppressWarnings("unused")
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
}