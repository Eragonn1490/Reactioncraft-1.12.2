package com.reactioncraft.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

import com.mojang.authlib.GameProfile;
import com.reactioncraft.Reactioncraft;
import com.reactioncraft.api.BoneDropMap;
import com.reactioncraft.registration.instances.ItemIndex;

import ic2.api.info.Info;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.monster.EntityPolarBear;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.fml.common.Loader;

public class constants 
{
	//Mod Info
	public static final String BaseID    = "Reactioncraft 3: Rebirth";
	public static final String MODID     = "reactioncraft";
	public static final String VERSION   = "0.6.5.0";
	public static final String MCVersion = "1.12.2";
	
	//config constants
	public static double distance;
	public static int MaxEnergy;
	public static boolean deleteWaterBlock;

	//Check if mods are loaded
	public static boolean IC2, Forestry, millenaire, Buildcraft, loadedRf;

	/** Fake Player **/
	WorldServer worldServer = DimensionManager.getWorld(0); // default world
	GameProfile gameProfile = new GameProfile(UUID.randomUUID(), "FakePlayer");
	FakePlayer fakePlayer = new FakePlayer(worldServer, gameProfile);
	MinecraftServer minecraftServer = fakePlayer.mcServer;

	public static void config()
	{
		//Add Reactioncraft Config Stuff here
		MaxEnergy = Reactioncraft.config.getInt("Energy", "Maximum Energy", 100000, 0, Integer.MAX_VALUE, "This is the max value of energy stored in the energy block");
		distance  = Reactioncraft.config.getInt("Distance", "Maximum Distance", 10, 5, 32, "The range for activating the transceiver block");
		deleteWaterBlock = Reactioncraft.config.getBoolean("Water", "Delete Water?", false, "Should right clicking water with a bowl drain the block?");
	}

	public static void configmillenaire(File file)
	{	
		new File( file + "/mods/millenaire-custom/mods/Reactioncraft-Mill/goals/genericcooking/").mkdirs();
		new File( file + "/mods/millenaire-custom/mods/Reactioncraft-Mill/cultures/mayan/").mkdirs();
		new File( file + "/mods/millenaire-custom/mods/Reactioncraft-Mill/cultures/mayan/shop/").mkdirs();


		final String itemlist = file + "/mods/millenaire-custom/mods/Reactioncraft-Mill/itemlist.txt";
		final String cookedCorn = file + "/mods/millenaire-custom/mods/Reactioncraft-Mill/goals/genericcooking/cookedCorn.txt";
		final String traded_goods = file + "/mods/millenaire-custom/mods/Reactioncraft-Mill/cultures/mayan/traded_goods.txt";
		final String shopgoods = file + "/mods/millenaire-custom/mods/Reactioncraft-Mill/cultures/mayan/shop/mayantownhall.txt";

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(itemlist))) 
		{
			String content = "//Item key;item id;item meta;label (indicative only)\r\n" + 
					"//Item ids starting with mln will have the item range value added to them; i.e. mln0 is actually 0+25744 (by default)\r\n" + 
					"rawcorn;reactioncraft:rawcorn;0\r\n" + 
					"cookedCorn:reactioncraft:cookedCorn;0";

			bw.write(content);
			bw.close();
		} catch (IOException e) { e.printStackTrace(); }

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(cookedCorn))) 
		{
			String content = "//Note pour les utilisateurs de Windows: si vous voyez le fichier entier sur une seule ligne, ouvrez-le avec PSPAd ou UltraEdit plutôt que Notepad\r\n" + 
							 "\r\n" + 
							 "//Note to Windows users: if you see the entire file in one line, open it using PSPad or UltraEdit instead of Notepad\r\n" + 
							 "\r\n" + 
							 "priority=50\r\n" + 
							 "\r\n" + 
							 "//Si vrai (\"true\"), ce but se passe dans le bâtiment central \r\n" + 
							 "//If true, this goal happens in the central building\r\n" + 
							 "townhallgoal=false\r\n" + 
							 "\r\n" + 
							 "//spécifier si l'étiquette ou les phrases pour ce but n'ont pas le code du but lui-même\r\n" + 
							 "//specify if the label and sentences for this goal is not the name of the goal itself\r\n" + 
							 "sentencekey=cookcorn\r\n" + 
							 "labelkey=cookcorn\r\n" + 
							 "\r\n" + 
							 "//L'objet à cuire\r\n" + 
							 "//The item to be cooked\r\n" + 
							 "itemtocook=rawcorn\r\n" + 
							 "\r\n" + 
							 "//s'il y a plus d'objets dans le centre du village que ça, arrêter le but\r\n" + 
							 "//if more than that number of item is present in townhall, stop goal\r\n" + 
							 "buildinglimit=cookedCorn,20\r\n" + 
							 "\r\n" + 
							 "//Nombre minimum d'objets disponibles à ajouter à une cuisson\r\n" + 
							 "//Minimum number of items that can be added to a cooking\r\n" + 
							 "minimumtocook=3\r\n" + 
							 "\r\n" + 
							 "//Pair d'objets dont les quantités doivent être équilibrées dans le bâtiment central. S'il y a plus du second que du premier, le but est arrêté\r\n" + 
							"//Pair of items whose quantity to balance in the TH. If there are more of the second than the first, the goal won't trigger\r\n" + 
							"itemsbalance=rawcorn,cookedCorn\r\n" + 
							"\r\n" + 
							"helditems=rawcorn";

			bw.write(content);
			bw.close();
		} catch (IOException e) { e.printStackTrace(); }

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(traded_goods))) 
		{
			String content = "//name, selling price, buying price, reserved quantity, target quantity, foreign merchant price, auto-generated, needed equipment(deprecated), minimum reputation\r\n" + 
							 "\r\n" + 
							 "rawcorn,3,2,16,32,3,false,,0,tradehelp.mayanfood\r\n" + 
							 "cookedCorn,3,2,16,32,3,false,,0,tradehelp.mayanfood\r\n" + 
							 "\r\n" + 
							 "\r\n" + 
							 "";

			bw.write(content);
			bw.close();
		} catch (IOException e) { e.printStackTrace(); }

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(shopgoods))) 
		{
			String content = "sells=dirtwall,pathdirt,pathslabs,wood,wood_pine,wood_birch,wood_jungle,wood_acacia,wood_darkoak,gold,mayangold,cobblestone,stone,maize,masa,wah,purse,summoningwand,parchment_mayanvillagers,parchment_mayanbuildings,parchment_mayanitems,parchment_mayanfull\r\n" + 
							 "\r\n" + 
							 "buys=wood,wood_pine,wood_birch,wood_jungle,wood_acacia,wood_darkoak,stone,cobblestone,sandstone,wool_white,gold,mayangold\r\n" + 
							 "\r\n" + 
							 "buysoptional=rawcorn,cookedCorn\r\n" + 
							 "\r\n" + 
							 "deliverto=wood,wood_pine,wood_birch,wood_jungle,wood_acacia,wood_darkoak,cobblestone,stone,maize,mayangold,mayanstatue,masa,wah,sandstone,dirtwall,pathdirt,pathgravel,pathgravelslabs,pathochretiles,pathsandstone,pathslabs";

			bw.write(content);
			bw.close();
		} catch (IOException e) { e.printStackTrace(); }
	}

	public static void init() 
	{
		if(Info.isIc2Available())
			IC2=true;
		if(Loader.isModLoaded("forestry"))
			Forestry=true;
		if(Loader.isModLoaded("millenaire"))
			millenaire=true;
		if(Loader.isModLoaded("buildcraftcore"))
			Buildcraft = true;
		if(Loader.isModLoaded("redstoneflux"));
			loadedRf = true;
	}
}