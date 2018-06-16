package com.reactioncraft.mobs.common.renders;

import com.reactioncraft.entities.EntitySkeletonCrawling;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class RenderSkeletonCrawling extends RenderLiving<EntitySkeletonCrawling>
{
    public RenderSkeletonCrawling(RenderManager renderManager, ModelBase modelbase, float shadow)
    {
        super(renderManager, modelbase, shadow);
    }


    @Override
    public float prepareScale(EntitySkeletonCrawling entitylivingbaseIn, float partialTicks) {
        return super.prepareScale(entitylivingbaseIn, partialTicks);
    }

    @Override
    protected void preRenderCallback(EntitySkeletonCrawling entitylivingbaseIn, float partialTickTime) {
        GL11.glScalef(1.0F, 1.0F, 1.0F);
    }

    @Override
    protected float handleRotationFloat(EntitySkeletonCrawling livingBase, float partialTicks) {
        return super.handleRotationFloat(livingBase, partialTicks);
    }

    public void rotateAnimal(EntityLiving entityliving)
    {
        GL11.glRotatef(90.0F, -1.0F, 0.0F, 0.0F);
    }



    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntitySkeletonCrawling entity) {
        switch (entity.getSkeletonVariation())
        {
            case 0: return new ResourceLocation("reactioncraft:textures/entity/skeleton1.png");
            case 1: return new ResourceLocation("reactioncraft:textures/entity/skeleton2.png");
            case 2: return new ResourceLocation("reactioncraft:textures/entity/skeleton3.png");
        }
        return null;

    }
}
