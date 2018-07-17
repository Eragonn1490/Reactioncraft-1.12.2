package com.reactioncraft.common.instances.registration;

import com.reactioncraft.common.capabilities.TriggerHzHandler;
import com.reactioncraft.common.craftinghandlers.*;
import com.reactioncraft.common.events.*;
import com.reactioncraft.common.world.*;

import net.minecraftforge.common.MinecraftForge;

public class EventRegistry 
{
	//Events are Registered Here
	public static void eventInit() 
	{
		MinecraftForge.EVENT_BUS.register(new ButcherEventClass());
		MinecraftForge.EVENT_BUS.register(new EntityAIEventClass());
		MinecraftForge.EVENT_BUS.register(new EventContainerClass());
		MinecraftForge.EVENT_BUS.register(new NetCraftingHandler());
		MinecraftForge.EVENT_BUS.register(new MachinesCraftingHandler());
		MinecraftForge.EVENT_BUS.register(new CraftablesCraftingHandler());
		MinecraftForge.EVENT_BUS.register(new HammerCraftingHandler());
		MinecraftForge.EVENT_BUS.register(new BiomeHandler());
		MinecraftForge.EVENT_BUS.register(new TriggerHzHandler());
		MinecraftForge.EVENT_BUS.register(new DebugEventHandler());
	}
}