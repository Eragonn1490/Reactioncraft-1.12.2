package com.reactioncraft.common.events;

import net.minecraft.entity.passive.EntityAnimal;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

//Make EntityAnimals eat grass and reproduce
//Fully Working, but removed for now until i can find a way to make alot of mobs lag less (will really only affect mob farms)

public class EntityAIEventClass
{
	@SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent event)
    {
        if (event.getEntity() instanceof EntityAnimal)
        {
        	//EntityAnimal eatmate = (EntityAnimal)event.getEntity();
        	//eatmate.tasks.addTask(10, new EntityAIEatGrassMate(eatmate));
        }	
    }
}