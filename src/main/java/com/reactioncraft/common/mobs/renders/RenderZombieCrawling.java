package com.reactioncraft.common.mobs.renders;

import org.lwjgl.opengl.GL11;

import com.reactioncraft.common.mobs.entities.EntityZombieCrawling;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderZombieCrawling extends RenderLiving<EntityZombieCrawling>
{																				///assets/rcmobs/textures/entity/quadzombie.png
    public RenderZombieCrawling(RenderManager renderManager, ModelBase modelbase, float f)
    {
        super(renderManager,modelbase, f);
    }


    @Override
    protected void preRenderCallback(EntityZombieCrawling entitylivingbaseIn, float partialTickTime) {
        GL11.glScalef(1.0F, 1.0F, 1.0F);
    }

    @Override
    protected float handleRotationFloat(EntityZombieCrawling livingBase, float partialTicks) {
        return super.handleRotationFloat(livingBase, partialTicks);
    }

    
    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    @Override
    protected ResourceLocation getEntityTexture(EntityZombieCrawling par1Entity)
    {
    	return (new ResourceLocation("reactioncraft:textures/entity/quadzombie.png"));
    }
}