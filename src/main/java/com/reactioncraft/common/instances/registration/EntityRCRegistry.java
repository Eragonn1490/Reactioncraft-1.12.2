package com.reactioncraft.common.instances.registration;

import java.awt.Color;
import java.util.List;
import com.reactioncraft.Reactioncraft;
import com.reactioncraft.common.entities.*;
import com.reactioncraft.common.mobs.entities.*;
import com.reactioncraft.common.utils.constants;
import com.reactioncraft.common.world.village.*;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.*;
import net.minecraftforge.fml.common.registry.*;

public class EntityRCRegistry 
{
	static int eid = 0;
	/** Registers Villager Housing **/
	public static void registerVillagers() 
	{
		VillagerRegistry.instance().registerVillageCreationHandler(new ReactioncraftHouseHandler());
		VillagerRegistry.instance().registerVillageCreationHandler(new ReactioncraftFarmHandler());
		VillagerRegistry.instance().registerVillageCreationHandler(new ReactioncraftBankHandler());
		VillagerRegistry.instance().registerVillageCreationHandler(new ReactioncraftField());
		Villagers.registerVillageComponents();
	}
	
	/** Registers Mobs **/
	public static void registerMobs() 
	{
		//NOTICE the colors can be changed as needed. First is shell color, second is spot color
		//FIXME some entities can hang the game
		//EntityRegistry.registerModEntity(new ResourceLocation(constants.MODID,"bee"), EntityBee.class,"bee",eid++, Reactioncraft.instance,60,3,true,new Color(1,1,1).getRGB(),new Color(1,1,1).getRGB());
		//EntitySpawnPlacementRegistry.setPlacementType(EntityBee.class, EntityLiving.SpawnPlacementType.ON_GROUND);

		//
		EntityRegistry.registerModEntity(new ResourceLocation(constants.MODID,"hydrolisc"), EntityHydrolisc.class,"hydrolisc",eid++, Reactioncraft.instance,60,3,true,new Color(1,1,1).getRGB(),new Color(1,1,1).getRGB());
		EntitySpawnPlacementRegistry.setPlacementType(EntityHydrolisc.class, EntityLiving.SpawnPlacementType.ON_GROUND);

		//Jellyfish
		EntityRegistry.registerModEntity(new ResourceLocation(constants.MODID,"jellyfish"), EntityJellyfish.class,"jellyfish",eid++, Reactioncraft.instance,60,3,true,new Color(1,1,1).getRGB(),new Color(1,90,5).getRGB());
		EntitySpawnPlacementRegistry.setPlacementType(EntityJellyfish.class, EntityLiving.SpawnPlacementType.IN_WATER);
		
		//
		//EntityRegistry.registerModEntity(new ResourceLocation(constants.MODID,"sea_creeper"), EntitySeaCreeper.class,"sea_creeper",2, Reactioncraft.instance,60,3,true,new Color(1,1,1).getRGB(),new Color(1,1,1).getRGB());
		//EntitySpawnPlacementRegistry.setPlacementType(EntitySeaCreeper.class, EntityLiving.SpawnPlacementType.IN_WATER);

		//
		EntityRegistry.registerModEntity(new ResourceLocation(constants.MODID,"crawling_skeleton"), EntitySkeletonCrawling.class,"crawling_skeleton",eid++, Reactioncraft.instance,60,2,true,new Color(1,1,1).getRGB(),new Color(1,120,1).getRGB());
		EntitySpawnPlacementRegistry.setPlacementType(EntitySkeletonCrawling.class, EntityLiving.SpawnPlacementType.ON_GROUND);

		//
		EntityRegistry.registerModEntity(new ResourceLocation(constants.MODID,"crawling_zombie"), EntityZombieCrawling.class,    "crawling_zombie",  eid++, Reactioncraft.instance,60,2,true,new Color(1,1,1).getRGB(),new Color(1,150,1).getRGB());
		EntitySpawnPlacementRegistry.setPlacementType(EntityZombieCrawling.class, EntityLiving.SpawnPlacementType.ON_GROUND);



		//biomes to spawn in
		ForgeRegistries.BIOMES.forEach(biome -> 
		{
			if(!(biome instanceof BiomeOcean) || !(biome instanceof BiomeHell) || !(biome instanceof BiomeEnd))
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
				//listEntries.add(new Biome.SpawnListEntry(EntitySkeletonCrawling.class,25,1,2));
				//listEntries.add(new Biome.SpawnListEntry(EntityZombieCrawling.class,  25,1,2));
			}
		});
	}

	public static void registerThrowableEntites()
	{
		EntityRegistry.registerModEntity(new ResourceLocation(constants.MODID,"Mapinabottle"),  EntityMap.class        , "Mapinabottle" , eid++, Reactioncraft.instance, 60, 2, true);
		EntityRegistry.registerModEntity(new ResourceLocation(constants.MODID,"Shipinabottle"), EntityShipBottled.class, "Shipinabottle", eid++, Reactioncraft.instance, 60, 2, true);
	}
}