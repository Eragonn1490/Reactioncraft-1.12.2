package com.reactioncraft.common.mobs.renders;

import javax.annotation.Nullable;

import org.lwjgl.opengl.GL11;

import com.reactioncraft.common.mobs.entities.EntityJellyfish;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;


public class RenderJellyfish extends RenderLiving<EntityJellyfish>
{
    public RenderJellyfish(ModelBase modelbase, RenderManager renderManager, float f)
    {
        super(renderManager,modelbase, f);
    }

    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
    public void preRenderCallback(EntityLiving entityliving, float f)
    {
        GL11.glScalef(1.25F, 1.25F, 1.25F);
    }

    public void rotateAnimal(EntityLiving entityliving)
    {
        GL11.glRotatef(90F, -1F, 0.0F, 0.0F);
    }
    
    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */  
    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityJellyfish entity) {
        switch (entity.getTexture())
        {
             case 0: return new ResourceLocation("reactioncraft:textures/entity/Jellyfish.png");
             case 1: return new ResourceLocation("reactioncraft:textures/entity/Jellyfish2.png");
            default: return new ResourceLocation("reactioncraft:textures/entity/Jellyfish.png");
        }
    }
}
