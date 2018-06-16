package com.reactioncraft.mobs.common.renders;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.entities.EntityHydrolisc;
import com.reactioncraft.mobs.common.models.ModelHydrolisc;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderHydrolisc extends RenderLiving<EntityHydrolisc>
{
	public ModelHydrolisc model;
	private float scale = 1.0F;
	
	private static final ResourceLocation field_110833_a = new ResourceLocation(Reactioncraft.MODID, "textures/entity/hydrolisctexture.png");
	
	public RenderHydrolisc(ModelHydrolisc par1ModelBase, RenderManager par2, float scale_)
	{
		super(par2,par1ModelBase, 0.5f);
		model = par1ModelBase;
		this.scale = scale_;
	}

    


    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
    public void preRenderCallback(EntityHydrolisc par1EntityLiving, float par2)
    {
        GL11.glScalef(this.scale, this.scale, this.scale);
    }
    
    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    @Override
    protected ResourceLocation getEntityTexture(EntityHydrolisc par1Entity)
    {
    	return (new ResourceLocation("reactioncraft:textures/entity/hydrolisctexture.png"));
    }
}
