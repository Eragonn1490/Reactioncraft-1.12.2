package com.reactioncraft.registration;

import com.reactioncraft.common.events.*;
import com.reactioncraft.craftinghandlers.*;
import com.reactioncraft.world.BiomeHandler;
import net.minecraftforge.common.MinecraftForge;

public class EventRegistry 
{
	//All Events Registered Here
	public static void eventInit() 
	{
		MinecraftForge.EVENT_BUS.register(new ButcherEventClass());
		MinecraftForge.EVENT_BUS.register(new EntityAIEventClass());
		MinecraftForge.EVENT_BUS.register(new EventContainerClass());
		MinecraftForge.EVENT_BUS.register(new NetCraftingHandler());
		MinecraftForge.EVENT_BUS.register(new MachinesCraftingHandler());
		MinecraftForge.EVENT_BUS.register(new CraftablesCraftingHandler());
		MinecraftForge.EVENT_BUS.register(new HammerCraftingHandler());
		MinecraftForge.EVENT_BUS.register(new TransmitterCraftingHandler());
		MinecraftForge.EVENT_BUS.register(new BiomeHandler());
	}
}