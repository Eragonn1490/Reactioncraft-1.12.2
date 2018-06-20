package com.reactioncraft.mobs.common.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelSeaCreeper extends ModelBase
{
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer leg3;
    ModelRenderer leg4;
    ModelRenderer leg1;
    ModelRenderer leg2;
    ModelRenderer top_fin_second_peice;
    ModelRenderer top_fin_first_peice;

    public ModelSeaCreeper()
    {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.head = new ModelRenderer(this, 0, 0);
        this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8);
        this.head.setRotationPoint(0.0F, 11.0F, -11.0F);
        this.head.setTextureSize(64, 32);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 16, 16);
        this.body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4);
        this.body.setRotationPoint(0.0F, 7.0F, -7.0F);
        this.body.setTextureSize(64, 32);
        this.body.mirror = true;
        this.setRotation(this.body, 1.588146F, 0.0F, 0.0F);
        this.leg3 = new ModelRenderer(this, 0, 16);
        this.leg3.addBox(-2.0F, 0.0F, -1.0F, 4, 6, 4);
        this.leg3.setRotationPoint(-2.0F, 8.0F, 5.0F);
        this.leg3.setTextureSize(64, 32);
        this.leg3.mirror = true;
        this.setRotation(this.leg3, 1.561502F, -0.2230717F, 0.0F);
        this.leg4 = new ModelRenderer(this, 0, 16);
        this.leg4.addBox(-2.0F, 0.0F, 1.0F, 4, 6, 4);
        this.leg4.setRotationPoint(2.0F, 10.0F, 5.0F);
        this.leg4.setTextureSize(64, 32);
        this.leg4.mirror = true;
        this.setRotation(this.leg4, 1.561502F, 0.2974289F, 0.0F);
        this.leg1 = new ModelRenderer(this, 0, 16);
        this.leg1.addBox(0.0F, 0.0F, -2.0F, 4, 6, 4);
        this.leg1.setRotationPoint(-7.0F, 7.0F, 3.0F);
        this.leg1.setTextureSize(64, 32);
        this.leg1.mirror = true;
        this.setRotation(this.leg1, 1.59868F, -0.4089647F, 0.0F);
        this.leg2 = new ModelRenderer(this, 0, 16);
        this.leg2.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4);
        this.leg2.setRotationPoint(5.0F, 7.0F, 4.0F);
        this.leg2.setTextureSize(64, 32);
        this.leg2.mirror = true;
        this.setRotation(this.leg2, 1.59868F, 0.5205006F, 0.0F);
        this.top_fin_second_peice = new ModelRenderer(this, 8, 5);
        this.top_fin_second_peice.addBox(0.0F, 2.0F, 0.0F, 2, 1, 3);
        this.top_fin_second_peice.setRotationPoint(-1.0F, 0.0F, -2.0F);
        this.top_fin_second_peice.setTextureSize(64, 32);
        this.top_fin_second_peice.mirror = true;
        this.setRotation(this.top_fin_second_peice, 0.0F, 0.0F, 0.0F);
        this.top_fin_first_peice = new ModelRenderer(this, 5, 3);
        this.top_fin_first_peice.addBox(0.0F, 0.0F, 0.0F, 2, 2, 7);
        this.top_fin_first_peice.setRotationPoint(-1.0F, 3.0F, -4.0F);
        this.top_fin_first_peice.setTextureSize(64, 32);
        this.top_fin_first_peice.mirror = true;
        this.setRotation(this.top_fin_first_peice, 0.0F, 0.0F, 0.0F);
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.head.render(f5);
        this.body.render(f5);
        this.leg3.render(f5);
        this.leg4.render(f5);
        this.leg1.render(f5);
        this.leg2.render(f5);
        this.top_fin_second_peice.render(f5);
        this.top_fin_first_peice.render(f5);
    }

    private void setRotation(ModelRenderer modelrenderer, float f, float f1, float f2)
    {
        modelrenderer.rotateAngleX = f;
        modelrenderer.rotateAngleY = f1;
        modelrenderer.rotateAngleZ = f2;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, EntityLiving entity)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.leg1.rotateAngleY = (float)Math.sin((double)(f2 * 0.25F)) * 0.25F;
        this.leg2.rotateAngleY = (float)Math.sin((double)(f2 * 0.25F)) * 0.25F;
        this.leg3.rotateAngleY = (float)Math.sin((double)(f2 * 0.25F)) * 0.25F;
        this.leg4.rotateAngleY = (float)Math.sin((double)(f2 * 0.25F)) * 0.25F;
    }
}