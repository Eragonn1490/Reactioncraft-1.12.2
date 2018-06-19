package com.reactioncraft.common.events;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PlantBoneMealEventClass
{
	@SuppressWarnings("unused")
	@SubscribeEvent
	public void onUseBonemeal(BonemealEvent event)
	{
		EntityPlayer player = event.getEntityPlayer();
		World world = event.getWorld();


//		if (event.ID == IntegratedBlocks.AncientPlant.blockID && !event.getWorld().isRemote)
//		{
//			((BlockAncientPlant)IntegratedBlocks.AncientPlant).fertilize(event.getWorld());
//			event.setResult(Event.Result.ALLOW);
//		}
//
//		if (event.ID == IntegratedBlocks.cornStalk.blockID && !event.getWorld().isRemote)
//		{
//			((BlockCornStalk)IntegratedBlocks.cornStalk).fertilize(event.getWorld());
//			event.setResult(Event.Result.ALLOW);
//		}
	}

}