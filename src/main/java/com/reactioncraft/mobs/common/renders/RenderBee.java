package com.reactioncraft.mobs.common.renders;

import com.reactioncraft.entities.EntityBee;
import com.reactioncraft.mobs.common.models.ModelBee;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderBee extends RenderLiving<EntityBee>
{
	protected ModelBee model;
	private float scale;
	
	public RenderBee(ModelBee par1ModelBase, RenderManager renderManager, float scale_)
	{
		super(renderManager, par1ModelBase,0.2f);
		model =par1ModelBase;
		this.scale = scale_;
	}

    @Override
    public void doRender(EntityBee entity, double x, double y, double z, float entityYaw, float partialTicks) {

        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }


    @Override
    public float prepareScale(EntityBee entitylivingbaseIn, float partialTicks) {
	    GL11.glScalef(0.4f, 0.4f, 0.4f);
        return super.prepareScale(entitylivingbaseIn, partialTicks);
    }



    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
    protected void preRenderCallback(EntityBee par1EntityLiving, float par2)
    {
//        this.prepareScale(par1EntityLiving, par2);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     **/
    @Override
    protected ResourceLocation getEntityTexture(EntityBee par1Entity)
    {
    	return (new ResourceLocation("reactioncraft:textures/entity/tjtexture.png"));
    }
}
