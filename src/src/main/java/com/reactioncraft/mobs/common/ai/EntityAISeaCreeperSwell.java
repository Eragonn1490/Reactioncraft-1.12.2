package com.reactioncraft.mobs.common.ai;

import com.reactioncraft.mobs.common.entities.EntitySeaCreeper;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAISeaCreeperSwell extends EntityAIBase
{
    EntitySeaCreeper swellingSeaCreeper;
    EntityLivingBase creeperAttackTarget;

    public EntityAISeaCreeperSwell(EntitySeaCreeper par1EntityCreeper)
    {
        this.swellingSeaCreeper = par1EntityCreeper;
        this.setMutexBits(1);
    }


    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        EntityLivingBase entitylivingbase = this.swellingSeaCreeper.getAttackTarget();
        return this.swellingSeaCreeper.getCreeperState() > 0 || entitylivingbase != null && this.swellingSeaCreeper.getDistanceSq(entitylivingbase) < 9.0D;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.swellingSeaCreeper.getNavigator().clearPath();
        this.creeperAttackTarget = this.swellingSeaCreeper.getAttackTarget();
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        this.creeperAttackTarget = null;
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        if (this.creeperAttackTarget == null)
        {
            this.swellingSeaCreeper.setCreeperState(-1);
        }
        else if (this.swellingSeaCreeper.getDistanceSq(this.creeperAttackTarget) > 49.0D)
        {
            this.swellingSeaCreeper.setCreeperState(-1);
        }
        else if (!this.swellingSeaCreeper.getEntitySenses().canSee(this.creeperAttackTarget))
        {
            this.swellingSeaCreeper.setCreeperState(-1);
        }
        else
        {
            this.swellingSeaCreeper.setCreeperState(1);
        }
    }
}