package com.reactioncraft.registration;

import java.awt.Color;
import java.util.List;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.core.RenderMap;
import com.reactioncraft.entities.*;
import com.reactioncraft.mobs.common.entities.*;
import com.reactioncraft.registration.instances.ItemIndex;
import com.reactioncraft.utils.constants;
import com.reactioncraft.world.village.*;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEnd;
import net.minecraft.world.biome.BiomeHell;
import net.minecraft.world.biome.BiomeOcean;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

public class EntityRCRegistry {

	/** Registers Villager Housing **/
	public static void registerVillagers() 
	{
		VillagerRegistry.instance().registerVillageCreationHandler(new ReactioncraftHouseHandler());
		VillagerRegistry.instance().registerVillageCreationHandler(new ReactioncraftFarmHandler());
		VillagerRegistry.instance().registerVillageCreationHandler(new ReactioncraftBankHandler());
		Villagers.registerVillageComponents();
	}
	
	/** Registers Mobs **/
	public static void registerMobs() 
	{
		int eid=0;
		//NOTICE the colors can be changed as needed. First is shell color, second is spot color
		//FIXME some entities can hang the game
		//EntityRegistry.registerModEntity(new ResourceLocation(constants.MODID,"bee"), EntityBee.class,"bee",eid++, Reactioncraft.instance,60,3,true,new Color(1,1,1).getRGB(),new Color(1,1,1).getRGB());
		//EntitySpawnPlacementRegistry.setPlacementType(EntityBee.class, EntityLiving.SpawnPlacementType.ON_GROUND);

		//
		EntityRegistry.registerModEntity(new ResourceLocation(constants.MODID,"hydrolisc"), EntityHydrolisc.class,"hydrolisc",eid++, Reactioncraft.instance,60,3,true,new Color(1,1,1).getRGB(),new Color(1,1,1).getRGB());
		EntitySpawnPlacementRegistry.setPlacementType(EntityHydrolisc.class, EntityLiving.SpawnPlacementType.ON_GROUND);

		//
		//        EntityRegistry.registerModEntity(new ResourceLocation(MODID,"sea_creeper"), EntitySeaCreeper.class,"sea_creeper",2, Reactioncraft.instance,60,3,true,new Color(1,1,1).getRGB(),new Color(1,1,1).getRGB());
		//        EntitySpawnPlacementRegistry.setPlacementType(EntitySeaCreeper.class, EntityLiving.SpawnPlacementType.IN_WATER);

		//
		EntityRegistry.registerModEntity(new ResourceLocation(constants.MODID,"crawling_skeleton"), EntitySkeletonCrawling.class,"crawling_skeleton",eid++, Reactioncraft.instance,60,2,true,new Color(1,1,1).getRGB(),new Color(1,1,1).getRGB());
		EntitySpawnPlacementRegistry.setPlacementType(EntitySkeletonCrawling.class, EntityLiving.SpawnPlacementType.ON_GROUND);

		//
		EntityRegistry.registerModEntity(new ResourceLocation(constants.MODID,"crawling_zombie"),EntityZombieCrawling.class,"crawling_zombie",eid++, Reactioncraft.instance,60,2,true,new Color(1,1,1).getRGB(),new Color(1,150,1).getRGB());
		EntitySpawnPlacementRegistry.setPlacementType(EntityZombieCrawling.class, EntityLiving.SpawnPlacementType.ON_GROUND);



		//TODO biomes to spawn in
		ForgeRegistries.BIOMES.forEach(biome -> 
		{
			if((biome instanceof BiomeOcean) || (biome instanceof BiomeHell) || (biome instanceof BiomeEnd))
			{
				List<Biome.SpawnListEntry> listEntries= biome.getSpawnableList(EnumCreatureType.MONSTER);
				//100 is the max weight
				listEntries.add(new Biome.SpawnListEntry(EntitySkeletonCrawling.class,0,0,0));
				listEntries.add(new Biome.SpawnListEntry(EntityZombieCrawling.class,  0,0,0));
				//and so on
			}
			else
			{
				List<Biome.SpawnListEntry> listEntries= biome.getSpawnableList(EnumCreatureType.MONSTER);
				//100 is the max weight
				listEntries.add(new Biome.SpawnListEntry(EntitySkeletonCrawling.class,25,1,2));
				listEntries.add(new Biome.SpawnListEntry(EntityZombieCrawling.class,  25,1,2));
			}
		});
	}

	public static void registerThrowableEntites()
	{
		
	}
}