package com.reactioncraft;


//Java
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.reactioncraft.api.ExclusionList;
import com.reactioncraft.api.OreDictionaryRegistry;
import com.reactioncraft.common.capabilities.CapabilityTriggerHz;
import com.reactioncraft.common.creativetabs.RCBlockTab;
import com.reactioncraft.common.creativetabs.RCFoodTab;
import com.reactioncraft.common.creativetabs.RCItemTab;
import com.reactioncraft.common.creativetabs.RCTestTab;
import com.reactioncraft.common.energystorageblock.network.NetworkHandler;
import com.reactioncraft.common.events.LootTableHandler;
import com.reactioncraft.common.registration.BlockRegistry;
import com.reactioncraft.common.registration.EntityRCRegistry;
import com.reactioncraft.common.registration.EventRegistry;
import com.reactioncraft.common.registration.ItemRegistry;
import com.reactioncraft.common.registration.MaterialIndex;
import com.reactioncraft.common.registration.RecipesManager;
import com.reactioncraft.common.registration.TileEntityRegistry;
import com.reactioncraft.common.registration.Villagers;
import com.reactioncraft.common.utils.Logger;
import com.reactioncraft.common.utils.ReactioncraftConfiguration;
import com.reactioncraft.common.utils.constants;
import com.reactioncraft.common.world.BiomeHandler;
import com.reactioncraft.common.world.Worldgen;
import com.reactioncraft.core.BuildcraftProxy;
import com.reactioncraft.core.EnergyModProxy;
import com.reactioncraft.core.IC2Proxy;
import com.reactioncraft.core.ServerProxy;

//Minecraft
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
//Forge
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.server.FMLServerHandler;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;


@Mod(modid = constants.MODID, name = constants.BaseID, version = constants.VERSION, acceptedMinecraftVersions = "[1.12]")
@Mod.EventBusSubscriber(modid = constants.MODID)
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
	
	//Energy Proxies
	public static List<EnergyModProxy> energyModProxies = new ArrayList();

	//Setup Config Files
	public static ReactioncraftConfiguration config, millenaireC;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		Logger.setLogger(event.getModLog());
		Logger.info("[Reactioncraft] Pre-initialization started");
		CapabilityTriggerHz.register();
		
		NetworkHandler.init();
        NetworkRegistry.INSTANCE.registerGuiHandler(this, proxy);
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new ServerProxy());

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
		
		energyModProxies.forEach(proxy -> proxy.init());
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
			energyModProxies.add(BuildcraftProxy.INSTANCE);
			System.out.println("[Reactioncraft] Found Buildcraft!");
		} else { System.out.println("[Reactioncraft] Did not find Buildcraft!"); }
		
		
		if(constants.IC2 == true)
		{
			 energyModProxies.add(IC2Proxy.INSTANCE);
			System.out.println("[Reactioncraft] Found Industrialcraft 2!");
		} else { System.out.println("[Reactioncraft] Did not find Industrialcraft 2!"); }

		if(constants.railcraft == true)
		{
			System.out.println("[Reactioncraft] Found railcraft!");
			
		} else { System.out.println("[Reactioncraft] Did not find railcraft!"); }
		
		if(constants.loadedRf == true)
		{
			System.out.println("[Reactioncraft] Found RF-API!");
		} else { System.out.println("[Reactioncraft] Did not find RF-API!"); }
		
		energyModProxies.forEach(proxy -> proxy.postInit());
		
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