package com.reactioncraft.common.mobs.renders;

import org.lwjgl.opengl.GL11;

import com.reactioncraft.common.mobs.entities.EntityStalker;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderStalker extends RenderLiving<EntityStalker>
{
    public RenderStalker(ModelBase modelbase, RenderManager renderManager, float f)
    {
        super(renderManager,modelbase, f);
    }


    @Override
    public float prepareScale(EntityStalker entitylivingbaseIn, float partialTicks) {
        GL11.glScalef(1.0F, 1.0F, 1.0F);
        return super.prepareScale(entitylivingbaseIn, partialTicks);
    }

    @Override
    protected void preRenderCallback(EntityStalker entitylivingbaseIn, float partialTickTime) {
        prepareScale(entitylivingbaseIn, partialTickTime);
    }


    public void rotateAnimal(EntityLiving entityliving)
    {
        GL11.glRotatef(90F, -1F, 0.0F, 0.0F);
    }
    
    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     **/
    @Override
    protected ResourceLocation getEntityTexture(EntityStalker par1Entity)
    {
    	return (new ResourceLocation("reactioncraft:textures/entity/Stalker.png"));
    }
}