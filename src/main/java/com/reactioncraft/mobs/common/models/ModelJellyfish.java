package com.reactioncraft.mobs.common.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelJellyfish extends ModelBase
{
    ModelRenderer Head;
    ModelRenderer Leg1;
    ModelRenderer Leg2;
    ModelRenderer Leg3;
    ModelRenderer Leg4;

    public ModelJellyfish()
    {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.Head = new ModelRenderer(this, 0, 0);
        this.Head.addBox(0.0F, 0.0F, 0.0F, 5, 4, 5);
        this.Head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Head.setTextureSize(32, 32);
        this.Head.mirror = true;
        this.setRotation(this.Head, 0.0F, 0.0F, 0.0F);
        this.Leg1 = new ModelRenderer(this, 0, 9);
        this.Leg1.addBox(-0.5F, 0.0F, -0.5F, 1, 6, 1);
        this.Leg1.setRotationPoint(1.0F, 3.0F, 1.0F);
        this.Leg1.setTextureSize(32, 32);
        this.Leg1.mirror = true;
        this.setRotation(this.Leg1, 0.0F, 0.0F, 0.0F);
        this.Leg2 = new ModelRenderer(this, 0, 9);
        this.Leg2.addBox(-0.5F, 0.0F, -0.5F, 1, 6, 1);
        this.Leg2.setRotationPoint(4.0F, 3.0F, 1.0F);
        this.Leg2.setTextureSize(32, 32);
        this.Leg2.mirror = true;
        this.setRotation(this.Leg2, 0.0F, 0.0F, 0.0F);
        this.Leg3 = new ModelRenderer(this, 0, 9);
        this.Leg3.addBox(-0.5F, 0.0F, -0.5F, 1, 6, 1);
        this.Leg3.setRotationPoint(4.0F, 3.0F, 4.0F);
        this.Leg3.setTextureSize(32, 32);
        this.Leg3.mirror = true;
        this.setRotation(this.Leg3, 0.0F, 0.0F, 0.0F);
        this.Leg4 = new ModelRenderer(this, 0, 9);
        this.Leg4.addBox(-0.5F, 0.0F, -0.5F, 1, 6, 1);
        this.Leg4.setRotationPoint(1.0F, 3.0F, 4.0F);
        this.Leg4.setTextureSize(32, 32);
        this.Leg4.mirror = true;
        this.setRotation(this.Leg4, 0.0F, 0.0F, 0.0F);
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.Head.render(f5);
        this.Leg1.render(f5);
        this.Leg2.render(f5);
        this.Leg3.render(f5);
        this.Leg4.render(f5);
    }

    private void setRotation(ModelRenderer modelrenderer, float f, float f1, float f2)
    {
        modelrenderer.rotateAngleX = f;
        modelrenderer.rotateAngleY = f1;
        modelrenderer.rotateAngleZ = f2;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, EntityLiving entityliving)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entityliving);
        this.Leg1.rotateAngleY = (float)Math.sin((double)(f2 * 0.25F)) * 0.25F;
        this.Leg2.rotateAngleY = (float)Math.sin((double)(f2 * 0.25F)) * 0.25F;
        this.Leg3.rotateAngleY = (float)Math.sin((double)(f2 * 0.25F)) * 0.25F;
        this.Leg4.rotateAngleY = (float)Math.sin((double)(f2 * 0.25F)) * 0.25F;
    }
}
