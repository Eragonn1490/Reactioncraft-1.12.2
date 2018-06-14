package com.reactioncraft.core;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.entities.*;
import com.reactioncraft.items.ItemMulti;
import com.reactioncraft.mobs.common.models.ModelBee;
import com.reactioncraft.mobs.common.models.ModelHydrolisc;
import com.reactioncraft.mobs.common.models.ModelSeaCreeper;
import com.reactioncraft.mobs.common.models.ModelZombieCrawling;
import com.reactioncraft.mobs.common.renders.*;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy extends ServerProxy
{
    public static String BORE_TEXTURE = "/mods/reactioncraft/textures/railcraft/";

    @Deprecated
    @Override
    public void registerItemRenderer(Item item, int meta, String id) 
    {
    	if (item instanceof ItemMulti) 
    	{
    		this.registerItemBlockRenderer(item, meta);
		}
    	else
    	{
    		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Reactioncraft.MODID + ":" + id, "inventory"));
    	}
    }

	@Override
	public void registerItemBlockRenderer(ItemBlock itemBlock,int meta) {
		ModelLoader.setCustomModelResourceLocation(itemBlock,meta,new ModelResourceLocation(Reactioncraft.MODID+":"+itemBlock.getRegistryName().getResourcePath(),"inventory"));
	}

	@Override
	public void setItemBlockWithMetadataInventoryModel(ItemBlock itemBlock, String... variants) {
		int m=0;
		for (String variant : variants) {
			ModelLoader.setCustomModelResourceLocation(itemBlock,m++,new ModelResourceLocation(Reactioncraft.MODID+":"+itemBlock.getRegistryName().getResourcePath()+variant,"inventory"));
		}
	}

	//FIXME clean up blockstates
	@Deprecated
	public void registerItemBlockRenderer(Item item, int range)
    {
    	ModelLoader.setCustomModelResourceLocation(item, 0,   new ModelResourceLocation(item.getRegistryName(), "type=one1"));   // meta 0
    	ModelLoader.setCustomModelResourceLocation(item, 1,  new ModelResourceLocation(item.getRegistryName(), "type=one2"));   // meta 1
    	ModelLoader.setCustomModelResourceLocation(item, 2,   new ModelResourceLocation(item.getRegistryName(), "type=one3"));   // meta 2
    	ModelLoader.setCustomModelResourceLocation(item, 3,  new ModelResourceLocation(item.getRegistryName(), "type=two1"));   // meta 3
    	ModelLoader.setCustomModelResourceLocation(item, 4,  new ModelResourceLocation(item.getRegistryName(), "type=two2"));   // meta 4
    	ModelLoader.setCustomModelResourceLocation(item, 5,   new ModelResourceLocation(item.getRegistryName(), "type=two3"));   // meta 5
    	ModelLoader.setCustomModelResourceLocation(item, 6,   new ModelResourceLocation(item.getRegistryName(), "type=three1")); // meta 6
    	ModelLoader.setCustomModelResourceLocation(item, 7,  new ModelResourceLocation(item.getRegistryName(), "type=three2")); // meta 7
    	ModelLoader.setCustomModelResourceLocation(item, 8,  new ModelResourceLocation(item.getRegistryName(), "type=three3")); // meta 8
    	ModelLoader.setCustomModelResourceLocation(item, 9,   new ModelResourceLocation(item.getRegistryName(), "type=four1"));  // meta 9
    	ModelLoader.setCustomModelResourceLocation(item, 10,  new ModelResourceLocation(item.getRegistryName(), "type=four2"));  // meta 10
    	ModelLoader.setCustomModelResourceLocation(item, 11,  new ModelResourceLocation(item.getRegistryName(), "type=four3"));  // meta 11
    	ModelLoader.setCustomModelResourceLocation(item, 12,  new ModelResourceLocation(item.getRegistryName(), "type=five1"));  // meta 12
    	ModelLoader.setCustomModelResourceLocation(item, 13, new ModelResourceLocation(item.getRegistryName(), "type=five2"));  // meta 13
    	ModelLoader.setCustomModelResourceLocation(item, 14,  new ModelResourceLocation(item.getRegistryName(), "type=five3"));  // meta 14
    	ModelLoader.setCustomModelResourceLocation(item, 15,  new ModelResourceLocation(item.getRegistryName(), "type=six1" ));  // meta 15
    }


	@Override
    public void registerRenderInformation()
    {

		RenderingRegistry.registerEntityRenderingHandler(EntityBee.class,manager -> new RenderBee(new ModelBee(1),manager,0.5f));
//		RenderingRegistry.registerEntityRenderingHandler(EntityStalker.class,manager -> new RenderStalker(new Model));

		RenderingRegistry.registerEntityRenderingHandler(EntityHydrolisc.class,manager -> new RenderHydrolisc(new ModelHydrolisc(0.2f),manager,1));
		RenderingRegistry.registerEntityRenderingHandler(EntitySeaCreeper.class,manager -> new RenderSeaCreeper(new ModelSeaCreeper(),manager,0));
//        RenderingRegistry.registerEntityRenderingHandler(EntitySeaCreeper.class, new RenderSeaCreeper(new ModelSeaCreeper(), 0.5F));
//        RenderingRegistry.registerEntityRenderingHandler(EntityStalker.class, new RenderStalker(new ModelCreeper(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityZombieCrawling.class,manager -> new RenderZombieCrawling(manager,new ModelZombieCrawling(),0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntitySkeletonCrawling.class,manager -> new RenderSkeletonCrawling(manager,new ModelZombieCrawling(),0.5f));
    }


}