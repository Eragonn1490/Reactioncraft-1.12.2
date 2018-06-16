package com.reactioncraft.mobs.common.ai;

import com.reactioncraft.entities.EntitySeaCreeper;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.MathHelper;

import java.util.Random;

/**
 * Do not use - not finished
 */
public class AISwim extends EntityAIBase {

    EntitySeaCreeper seaCreeper;
    public AISwim(EntitySeaCreeper seaCreeper_)
    {
        seaCreeper=seaCreeper_;
    }

    @Override
    public boolean shouldExecute() {
        return seaCreeper.getCreeperState()!=1;
    }

    @Override
    public void updateTask() {
        int idletime=seaCreeper.getIdleTime();
        if(idletime>100)
        {
            seaCreeper.randommotionX=0;
            seaCreeper.randommotionY=0;
            seaCreeper.randommotionZ=0;
        }
        else {
            Random random=seaCreeper.getRNG();
            if(random.nextInt(50)==0 || !seaCreeper.isInWater() || (seaCreeper.randommotionZ==0 && seaCreeper.randommotionY==0 && seaCreeper.randommotionX==0))
            {
                float rf= (float) (random.nextFloat()* Math.PI*2);
                float x=MathHelper.cos(rf)*0.2f;
                float y=-0.1f+random.nextFloat()*0.2f;
                float z=MathHelper.sin(rf)*0.2f;
                seaCreeper.randommotionX=x;
                seaCreeper.randommotionY=y;
                seaCreeper.randommotionZ=z;

            }
        }
    }
}
