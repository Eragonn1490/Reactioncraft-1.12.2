package com.reactioncraft.core;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.entities.EntityMap;
import com.reactioncraft.items.ItemMulti;
import com.reactioncraft.mobs.common.entities.*;
import com.reactioncraft.mobs.common.models.*;
import com.reactioncraft.mobs.common.renders.*;
import com.reactioncraft.registration.instances.ItemIndex;
import com.reactioncraft.utils.constants;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy extends ServerProxy
{
    public static String BORE_TEXTURE = "/mods/reactioncraft/textures/railcraft/";

    //@Override

    //public void registerItemRenderer(Item item, int meta, String id) 
    //{
    //	if (!(item instanceof ItemMulti))
    //	{
    //		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(constants.MODID + ":" + id, "inventory"));
    //	}
    //}
    
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
    		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(constants.MODID + ":" + id, "inventory"));
    	}
    }

	@Override
	public void setItemBlockWithMetadataInventoryModel(ItemBlock itemBlock, String... variants) {

		int m=0;

		for (String variant : variants) {
			ModelLoader.setCustomModelResourceLocation(itemBlock,m++,new ModelResourceLocation(constants.MODID+":"+itemBlock.getRegistryName().getResourcePath()+variant,"inventory"));
		}
	}

	@Override
	public void registerBlockItemRenderer(ItemBlock item, int metadataRange)
    {
		for (int i = 0; i < metadataRange; i++) {
			ModelLoader.setCustomModelResourceLocation(item,i,new ModelResourceLocation(constants.MODID+":"+item.getRegistryName(),"type="+i));
		}
    }

	@Override
	public void registerItemBlockRenderer(ItemBlock itemBlock,int meta) {
		ModelLoader.setCustomModelResourceLocation(itemBlock,meta,new ModelResourceLocation(constants.MODID+":"+itemBlock.getRegistryName().getResourcePath(),"inventory"));
	}

	
	//FIXME clean up blockstates
	@Deprecated
	public void registerItemBlockRenderer(Item item, int range)
    {
    	ModelLoader.setCustomModelResourceLocation(item, 0,   new ModelResourceLocation(item.getRegistryName(), "type=0"));   // meta 0
    	ModelLoader.setCustomModelResourceLocation(item, 1,  new ModelResourceLocation(item.getRegistryName(),  "type=1"));   // meta 1
    	ModelLoader.setCustomModelResourceLocation(item, 2,   new ModelResourceLocation(item.getRegistryName(), "type=2"));   // meta 2
    	ModelLoader.setCustomModelResourceLocation(item, 3,  new ModelResourceLocation(item.getRegistryName(),  "type=3"));   // meta 3
    	ModelLoader.setCustomModelResourceLocation(item, 4,  new ModelResourceLocation(item.getRegistryName(),  "type=4"));   // meta 4
    	ModelLoader.setCustomModelResourceLocation(item, 5,   new ModelResourceLocation(item.getRegistryName(), "type=5"));   // meta 5
    	ModelLoader.setCustomModelResourceLocation(item, 6,   new ModelResourceLocation(item.getRegistryName(), "type=6")); // meta 6
    	ModelLoader.setCustomModelResourceLocation(item, 7,  new ModelResourceLocation(item.getRegistryName(),  "type=7")); // meta 7
    	ModelLoader.setCustomModelResourceLocation(item, 8,  new ModelResourceLocation(item.getRegistryName(),  "type=8")); // meta 8
    	ModelLoader.setCustomModelResourceLocation(item, 9,   new ModelResourceLocation(item.getRegistryName(), "type=9"));  // meta 9
    	ModelLoader.setCustomModelResourceLocation(item, 10,  new ModelResourceLocation(item.getRegistryName(), "type=10"));  // meta 10
    	ModelLoader.setCustomModelResourceLocation(item, 11,  new ModelResourceLocation(item.getRegistryName(), "type=11"));  // meta 11
    	ModelLoader.setCustomModelResourceLocation(item, 12,  new ModelResourceLocation(item.getRegistryName(), "type=12"));  // meta 12
    	ModelLoader.setCustomModelResourceLocation(item, 13, new ModelResourceLocation(item.getRegistryName(),  "type=13"));  // meta 13
    	ModelLoader.setCustomModelResourceLocation(item, 14,  new ModelResourceLocation(item.getRegistryName(), "type=14"));  // meta 14
    	ModelLoader.setCustomModelResourceLocation(item, 15,  new ModelResourceLocation(item.getRegistryName(), "type=15" ));  // meta 15
    }


	@Override
    public void registerRenderInformation()
    {

//		RenderingRegistry.registerEntityRenderingHandler(EntityBee.class,manager -> new RenderBee(new ModelBee(1),manager,0.5f));
//		RenderingRegistry.registerEntityRenderingHandler(EntityStalker.class,manager -> new RenderStalker(new Model));

		RenderingRegistry.registerEntityRenderingHandler(EntityHydrolisc.class,manager -> new RenderHydrolisc(new ModelHydrolisc(0.2f),manager,1));
		RenderingRegistry.registerEntityRenderingHandler(EntitySeaCreeper.class,manager -> new RenderSeaCreeper(new ModelSeaCreeper(),manager,0));
//        RenderingRegistry.registerEntityRenderingHandler(EntitySeaCreeper.class, new RenderSeaCreeper(new ModelSeaCreeper(), 0.5F));
//        RenderingRegistry.registerEntityRenderingHandler(EntityStalker.class, new RenderStalker(new ModelCreeper(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityZombieCrawling.class,manager -> new RenderZombieCrawling(manager,new ModelZombieCrawling(),0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntitySkeletonCrawling.class,manager -> new RenderSkeletonCrawling(manager,new ModelZombieCrawling(),0.5f));

		//RenderingRegistry.registerEntityRenderingHandler(EntityMap.class,manager -> new RenderMap(manager, ItemIndex.mapinabottle, null));
    }
}