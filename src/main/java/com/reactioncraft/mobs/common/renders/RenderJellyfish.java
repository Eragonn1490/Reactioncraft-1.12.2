package com.reactioncraft.mobs.common.renders;

import com.reactioncraft.entities.EntityJellyfish;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;


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
    @Override
    protected ResourceLocation getEntityTexture(EntityJellyfish par1Entity)
    {
        return this.func_110902_a((EntityJellyfish)par1Entity);
    }
    
    protected ResourceLocation func_110902_a(EntityJellyfish par1EntityVillager)
    {
//        switch (par1EntityVillager.getProfession())
//        {
//            case 0:
//                return (new ResourceLocation("reactioncraft::textures/entity/Jellyfish.png"));
//            case 1:
//                return (new ResourceLocation("reactioncraft::textures/entity/Jellyfish2.png"));
/*            case 2:
                return priestVillagerTextures;
            case 3:
                return smithVillagerTextures;
            case 4:
                return butcherVillagerTextures;*/
//            default:
                return (new ResourceLocation("reactioncraft:textures/entity/Jellyfish.png"));
//        }
    }
    
//    protected ResourceLocation getEntityTexture(Entity par1Entity)
//    {
//    	return (new ResourceLocation("reactioncraft:textures/entity/Jellyfish.png"));
//    }
}
