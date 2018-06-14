package com.reactioncraft;


import com.reactioncraft.api.ExclusionList;
import com.reactioncraft.api.OreDictionaryRegistry;
import com.reactioncraft.common.events.LootTableHandler;
import com.reactioncraft.core.*;
import com.reactioncraft.creativetabs.*;
import com.reactioncraft.entities.*;
import com.reactioncraft.registration.*;
import com.reactioncraft.registration.instances.*;
import com.reactioncraft.utils.*;
import com.reactioncraft.world.BiomeHandler;
import com.reactioncraft.world.Worldgen;
import com.reactioncraft.world.village.*;

import forestry.api.recipes.RecipeManagers;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeOcean;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.server.FMLServerHandler;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;

import java.awt.*;
import java.io.File;
import java.util.List;

@Mod(modid = Reactioncraft.MODID, name = Reactioncraft.NAME, version = Reactioncraft.VERSION, acceptedMinecraftVersions = Reactioncraft.MCVersion)
@SuppressWarnings("unused")
public class Reactioncraft
{
	public static final String NAME = constants.BaseID;
	public static final String MODID = constants.MODID;
	public static final String VERSION = constants.VERSION;
	public static final String MCVersion = constants.MCVersion;


	//Proxies
	@SidedProxy(serverSide = "com.reactioncraft.core.ServerProxy", clientSide = "com.reactioncraft.core.ClientProxy")
	public static ServerProxy proxy;

	//Instance
	@Mod.Instance(MODID)
	public static com.reactioncraft.Reactioncraft instance;

	//Creative Tabs
	public static CreativeTabs Reactioncraft      = new RCBlockTab(MODID);
	public static CreativeTabs ReactioncraftItems = new RCItemTab(MODID+" items");
	public static CreativeTabs Reactioncraftfood  = new RCFoodTab(MODID+" food");

	//Exclusion List of Entities
	public static ExclusionList exclusionList=new ExclusionList();

	//For Wild_Card Values (Replace as it pops up)
	public static final int WILDCARD_VALUE = OreDictionary.WILDCARD_VALUE;

	//Config
	public static ReactioncraftConfiguration config;
	public static ReactioncraftConfiguration millenaire;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		Logger.setLogger(event.getModLog());
		Logger.info("Reactioncraft Pre-initialization started");

		config = new ReactioncraftConfiguration(new File(event.getModConfigurationDirectory(), "Reactioncraft/Basemod.wizim"));
		
		clientorserver(event);

		try {
			config.load();
			constants.config();
		}
		finally {
			if (config.hasChanged()) { config.save(); }
		}

		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new ServerProxy());
		proxy.registerRenderInformation();
		MaterialIndex.initMaterials();

		MinecraftForge.EVENT_BUS.register(this);
		MinecraftForge.EVENT_BUS.register(new BlockRegistry());
		MinecraftForge.EVENT_BUS.register(new ItemRegistry());
		MinecraftForge.EVENT_BUS.register(new BiomeHandler());
		MinecraftForge.EVENT_BUS.register(new LootTableHandler());
		
		VillagerRegistry.instance().registerVillageCreationHandler(new ReactioncraftHouseHandler());
		VillagerRegistry.instance().registerVillageCreationHandler(new ReactioncraftFarmHandler());
		Villagers.registerVillageComponents();

		TileEntityRegistry.registerTileEntities();

		int eid=0;
		//NOTICE the colors can be changed as needed. First is shell color, second is spot color
		//FIXME some entities can hang the game
		EntityRegistry.registerModEntity(new ResourceLocation(MODID,"hydrolisc"), EntityHydrolisc.class,"hydrolisc",eid++,instance,60,3,true,new Color(1,1,1).getRGB(),new Color(1,1,1).getRGB());
		EntitySpawnPlacementRegistry.setPlacementType(EntityHydrolisc.class, EntityLiving.SpawnPlacementType.ON_GROUND);
		//
		//        EntityRegistry.registerModEntity(new ResourceLocation(MODID,"sea_creeper"), EntitySeaCreeper.class,"sea_creeper",2,instance,60,3,true,new Color(1,1,1).getRGB(),new Color(1,1,1).getRGB());
		//        EntitySpawnPlacementRegistry.setPlacementType(EntitySeaCreeper.class, EntityLiving.SpawnPlacementType.IN_WATER);
		EntityRegistry.registerModEntity(new ResourceLocation(MODID,"crawling_skeleton"), EntitySkeletonCrawling.class,"crawling_skeleton",eid++,instance,60,2,true,new Color(1,1,1).getRGB(),new Color(1,1,1).getRGB());
		EntitySpawnPlacementRegistry.setPlacementType(EntitySkeletonCrawling.class, EntityLiving.SpawnPlacementType.ON_GROUND);

		EntityRegistry.registerModEntity(new ResourceLocation(MODID,"crawling_zombie"),EntityZombieCrawling.class,"crawling_zombie",eid++,instance,60,2,true,new Color(1,1,1).getRGB(),new Color(1,150,1).getRGB());
		EntitySpawnPlacementRegistry.setPlacementType(EntityZombieCrawling.class, EntityLiving.SpawnPlacementType.ON_GROUND);



		//TODO biomes to spawn in
		ForgeRegistries.BIOMES.forEach(biome -> {
			if(!(biome instanceof BiomeOcean))
			{
				List<Biome.SpawnListEntry> listEntries= biome.getSpawnableList(EnumCreatureType.MONSTER);
				//100 is the max weight
				listEntries.add(new Biome.SpawnListEntry(EntitySkeletonCrawling.class,25,1,2));
				listEntries.add(new Biome.SpawnListEntry(EntityZombieCrawling.class,  25,1,2));
				//and so on
			}
		});
	}

	private void clientorserver(FMLPreInitializationEvent event) 
	{
		if(event.getSide() == Side.CLIENT)
		{
			millenaire = new ReactioncraftConfiguration(new File(Minecraft.getMinecraft().mcDataDir,  "/mods/millenaire-custom/mods/Reactioncraft-Mill/reactioncraft_placeholder.txt"));
		}
		if(event.getSide() == Side.SERVER)
		{
			millenaire = new ReactioncraftConfiguration(new File(FMLServerHandler.instance().getServer().getDataDirectory(),  "/mods/millenaire-custom/mods/Reactioncraft-Mill/reactioncraft_placeholder.txt"));
		}
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		OreDictionaryRegistry.registerOres();
		EventRegistry.eventInit();

		//NOTICE
		GameRegistry.registerWorldGenerator(new Worldgen(), 3);

		RecipesManager.registerRecipes();
	}

	@Mod.EventHandler
	public void modsLoaded(FMLPostInitializationEvent evt)
	{		
		//millenaire integration
		try
		{
			if(constants.millenaire())
			{	
				if(evt.getSide() == Side.CLIENT)
				{
					File file = (Minecraft.getMinecraft().mcDataDir);
					constants.configmillenaire(file);
				}
				if(evt.getSide() == Side.SERVER)
				{
					File file = FMLServerHandler.instance().getServer().getDataDirectory().getAbsoluteFile();
					constants.configmillenaire(file);
				}
				
				try {
					millenaire.load();
				}
				finally {
					if (millenaire.hasChanged()) { millenaire.save(); }
				}
			}
			System.out.println("Millenaire integration loaded !");
		}
		catch (ClassNotFoundException e)
		{
			System.out.println("Reactioncraft did not find millenaire, added recipes disabled!");
		}

		//Forestry integration
		try
		{
			if(constants.forestry())
			{
				System.out.println("Forestry integration loaded !");
			}
		}
		catch (ClassNotFoundException e)
		{
			System.out.println("Reactioncraft did not find Forestry, added recipes disabled!");
		}

		Logger.info("Reactioncraft has fully Loaded!");
	}

	@SubscribeEvent
	public void registerVillagers(RegistryEvent.Register<VillagerRegistry.VillagerProfession> registryEvent)
	{
		IForgeRegistry<VillagerRegistry.VillagerProfession> registry=registryEvent.getRegistry();
		Villagers.register(registry);
	}

	@SubscribeEvent
	public void onMissingMappings(RegistryEvent.MissingMappings missingMappings)
	{

	}
}