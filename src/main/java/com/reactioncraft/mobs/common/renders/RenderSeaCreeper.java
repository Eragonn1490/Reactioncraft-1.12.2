package com.reactioncraft.mobs.common.renders;

import com.reactioncraft.entities.EntitySeaCreeper;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;



public class RenderSeaCreeper extends RenderLiving<EntitySeaCreeper>
{
	private static final ResourceLocation villagerTextures             = new ResourceLocation("reactioncraft:textures/entity/seacreeper1.png");
    private static final ResourceLocation farmerVillagerTextures       = new ResourceLocation("reactioncraft:textures/entity/seacreeper2.png");
    private static final ResourceLocation librarianVillagerTextures    = new ResourceLocation("reactioncraft:textures/entity/seacreeper3.png");
    private static final ResourceLocation priestVillagerTextures       = new ResourceLocation("reactioncraft:textures/entity/seacreeper4.png");
    private static final ResourceLocation smithVillagerTextures        = new ResourceLocation("reactioncraft:textures/entity/seacreeper5.png");
    private static final ResourceLocation butcherVillagerTextures      = new ResourceLocation("reactioncraft:textures/entity/seacreeper6.png");
    private static final ResourceLocation smithVillagerTextures1       = new ResourceLocation("reactioncraft:textures/entity/seacreeper7.png");
    private static final ResourceLocation butcherVillagerTextures1     = new ResourceLocation("reactioncraft:textures/entity/seacreeper8.png");
    
	public RenderSeaCreeper(ModelBase modelbase, RenderManager renderManager, float f)
	{
		super(renderManager, modelbase,f);
	}

	@Override
	protected void preRenderCallback(EntitySeaCreeper entitylivingbaseIn, float partialTickTime) {
		GL11.glScalef(1.0F, 1.0F, 1.0F);
	}



	public void rotateAnimal(EntityLiving entityliving)
	{
		GL11.glRotatef(90F, -1F, 0.0F, 0.0F);
	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
	 */
	@Override
	/**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntitySeaCreeper par1Entity)
    {
        return this.func_110902_a((EntitySeaCreeper)par1Entity);
    }
	
	protected ResourceLocation func_110902_a(EntitySeaCreeper par1EntityVillager)
    {
//        switch (par1EntityVillager.getProfession())
//        {
//            case 0:
//                return farmerVillagerTextures;
//            case 1:
//                return librarianVillagerTextures;
//            case 2:
//        return
//            case 3:
//                return smithVillagerTextures;
//            case 4:
//                return butcherVillagerTextures;
//            case 5:
//                return smithVillagerTextures1;
//            case 6:
//                return butcherVillagerTextures1;
////            default:
////                return VillagerRegistry.getVillagerSkin(par1EntityVillager.getProfession(), villagerTextures);
//        }
        return villagerTextures;
    }
}
