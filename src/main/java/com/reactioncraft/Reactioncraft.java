package com.reactioncraft;


import com.reactioncraft.api.*;
import com.reactioncraft.core.*;
import com.reactioncraft.creativetabs.*;
import com.reactioncraft.mobs.common.entities.*;
import com.reactioncraft.registration.*;
import com.reactioncraft.utils.*;
import com.reactioncraft.world.*;
import com.reactioncraft.world.village.*;
import com.reactioncraft.common.events.LootTableHandler;

//API
import forestry.api.recipes.RecipeManagers;
import ic2.api.info.Info;

//Minecraft
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.biome.*;

//Forge
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.*;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.server.FMLServerHandler;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;

//Java
import java.io.File;


@Mod(modid = constants.MODID, name = constants.BaseID, version = constants.VERSION, acceptedMinecraftVersions = "[1.12]")


public class Reactioncraft
{
	//Proxies
	@SidedProxy(serverSide = "com.reactioncraft.core.ServerProxy", clientSide = "com.reactioncraft.core.ClientProxy")
	public static ServerProxy proxy;

	//Instance
	@Mod.Instance(constants.MODID)
	public static com.reactioncraft.Reactioncraft instance;

	//Creative Tabs
	public static CreativeTabs Reactioncraft      = new RCBlockTab(constants.MODID);
	public static CreativeTabs ReactioncraftItems = new RCItemTab (constants.MODID + " items");
	public static CreativeTabs Reactioncraftfood  = new RCFoodTab (constants.MODID + " food");
	public static CreativeTabs ReactioncraftTest  = new RCTestTab (constants.MODID + " testing area");

	//Exclusion List of Entities
	public static ExclusionList exclusionList = new ExclusionList();

	//For Wild_Card Values (Replace as it pops up)
	public static final int WILDCARD_VALUE = OreDictionary.WILDCARD_VALUE;

	//Setup Config Files
	public static ReactioncraftConfiguration config, millenaireC;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		Logger.setLogger(event.getModLog());
		Logger.info("[Reactioncraft] Pre-initialization started");

		config = new ReactioncraftConfiguration(new File(event.getModConfigurationDirectory(), "Reactioncraft/Basemod.wizim"));

		//Setup Config File Based Upon Side
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
		TileEntityRegistry.registerTileEntities();

		EntityRCRegistry.registerVillagers();
		EntityRCRegistry.registerMobs();
		EntityRCRegistry.registerThrowableEntites();
		constants.init();
	}	

	private void clientorserver(FMLPreInitializationEvent event) 
	{
		if(event.getSide() == Side.CLIENT)
		{
			millenaireC = new ReactioncraftConfiguration(new File(Minecraft.getMinecraft().mcDataDir,  "/mods/millenaire-custom/mods/Reactioncraft-Mill/reactioncraft_placeholder.txt"));
		}
		if(event.getSide() == Side.SERVER)
		{
			millenaireC = new ReactioncraftConfiguration(new File(FMLServerHandler.instance().getServer().getDataDirectory(),  "/mods/millenaire-custom/mods/Reactioncraft-Mill/reactioncraft_placeholder.txt"));
		}
	}


	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		EventRegistry.eventInit();
		GameRegistry.registerWorldGenerator(new Worldgen(), 3);
		RecipesManager.registerRecipes();
	}

	@Mod.EventHandler
	public void modsLoaded(FMLPostInitializationEvent evt)
	{
		OreDictionaryRegistry.registerOres();
		
		if(constants.millenaire == true)
		{	
			if(evt.getSide() == Side.CLIENT) {
				File file = (Minecraft.getMinecraft().mcDataDir);
				constants.configmillenaire(file);
			}
			if(evt.getSide() == Side.SERVER) {
				File file = FMLServerHandler.instance().getServer().getDataDirectory().getAbsoluteFile();
				constants.configmillenaire(file);
			}
			System.out.println("Millenaire integration loaded !");
		} else {	System.out.println("[Reactioncraft] Did not find millenaire, added recipes disabled!"); }
		
		
		if(constants.Forestry == true)
		{
			System.out.println("[Reactioncraft] Forestry integration loaded !");
		} else { System.out.println("[Reactioncraft] Did not find Forestry, added recipes disabled!"); }
		
		
		if(constants.Buildcraft == true)
		{
			System.out.println("[Reactioncraft] Found Buildcraft!");
		} else { System.out.println("[Reactioncraft] Did not find Buildcraft!"); }
		
		
		if(constants.IC2 == true)
		{
			System.out.println("[Reactioncraft] Found Industrialcraft 2!");
		} else { System.out.println("[Reactioncraft] Did not find Industrialcraft 2!"); }

		
		if(constants.loadedRf == true)
		{
			System.out.println("[Reactioncraft] Found RF-API!");
		} else { System.out.println("[Reactioncraft] Did not find RF-API!"); }
		
		
		Logger.info("[Reactioncraft] has fully Loaded!");
	}

	@SubscribeEvent
	public void registerVillagers(RegistryEvent.Register<VillagerRegistry.VillagerProfession> registryEvent)
	{
		IForgeRegistry<VillagerRegistry.VillagerProfession> registry=registryEvent.getRegistry();
		Villagers.register(registry);
	}

	@SubscribeEvent
	public void onMissingMappings(RegistryEvent.MissingMappings missingMappings){}
}