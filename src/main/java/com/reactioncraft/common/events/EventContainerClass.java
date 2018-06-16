package com.reactioncraft.common.events;

import javax.annotation.Nullable;

import com.reactioncraft.blocks.BlockBookshelf;
import com.reactioncraft.common.EnumBookshelf;
import com.reactioncraft.core.Logger;
import com.reactioncraft.registration.instances.BlockIndex;
import com.reactioncraft.registration.instances.ItemIndex;

import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
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


	@SuppressWarnings("unused")
	@SubscribeEvent
	public void onPlayerInteractEvent(PlayerInteractEvent event)
	{
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

	@SuppressWarnings("unused")
	@SubscribeEvent
	public void onEntityDrop(LivingDropsEvent event)
	{
		if (event.getSource().getDamageType().equals("player"))
		{
			if (event.getEntityLiving() instanceof EntityPig){}

			if (event.getEntityLiving() instanceof EntityChicken){}

			if (event.getEntityLiving() instanceof EntityBat){}

			if (event.getEntityLiving() instanceof EntityCow) {}

			if (event.getEntityLiving() instanceof EntityHorse){}

			if (event.getEntityLiving() instanceof EntityMooshroom){}

			if (event.getEntityLiving() instanceof EntityOcelot){}

			if (event.getEntityLiving() instanceof EntitySheep){}

			if (event.getEntityLiving() instanceof EntityRabbit){}

			if (event.getEntityLiving() instanceof EntityPolarBear){}
			
			if (event.getEntityLiving() instanceof EntityVillager){}

			if (event.getEntityLiving() instanceof EntityWolf){}

			if (event.getEntityLiving() instanceof EntityPlayer){}

			if (event.getEntityLiving() instanceof EntityCreeper){}

			if (event.getEntityLiving() instanceof EntityPigZombie) {}

			if (event.getEntityLiving() instanceof EntityWitch){}

			if (event.getEntityLiving() instanceof EntityZombie){}

			if(event.getEntity().getEntityData().getCompoundTag(culture) != null){}
			
			event.getEntityLiving().dropItem(ItemIndex.bones, 1);
			event.hasResult();
		}
	}
}