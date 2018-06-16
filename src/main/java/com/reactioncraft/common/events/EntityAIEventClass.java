package com.reactioncraft.common.events;

import com.reactioncraft.mobs.common.ai.EntityAIEatGrassMate;

import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIEatGrass;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

//Make EntityAnimals eat grass and reproduce

public class EntityAIEventClass
{
    @SuppressWarnings("unused")
	@SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent event)
    {
        if (event.getEntity() instanceof EntityAnimal)
        {
        	EntityAnimal flee = (EntityAnimal)event.getEntity();
            flee.tasks.addTask(1, new EntityAIEatGrassMate(flee));
        }	
    }
}