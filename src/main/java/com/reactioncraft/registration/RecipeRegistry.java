package com.reactioncraft.registration;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.registration.instances.BlockIndex;
import com.reactioncraft.registration.instances.ItemIndex;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RecipeRegistry
{
	public static void netrecipyInit() 
	{
		//Below is the crafting Recipe's for making the multi-Level / extra use Nets.
		Object[] levels = new Object[] {Blocks.PLANKS, Items.LEATHER, Items.IRON_INGOT, Items.GOLD_INGOT, Items.DIAMOND};
		for (int i = 0; i < levels.length; ++i)
		{
			Object[] hiltRec = new Object[] {" XI", "XIX", "IX ", 'X', Items.STICK, 'I', levels[i]};
			Object[] netRec = new Object[] {"IXI", "XIX", "IXI", 'X', Items.STRING, 'I', levels[i]};
			ItemStack hiltIs = new ItemStack(ItemIndex.hilt);
			ItemStack netIs = new ItemStack(ItemIndex.net);

			hiltIs.setTagCompound(new NBTTagCompound());
			netIs.setTagCompound(new NBTTagCompound());

			hiltIs.getTagCompound().setInteger("str1", i + 1);
			netIs.getTagCompound() .setInteger("str2", i + 1);
			GameRegistry.addShapedRecipe(new ResourceLocation(Reactioncraft.MODID,"net"+i),null,hiltIs,hiltRec);
			GameRegistry.addShapedRecipe(new ResourceLocation(Reactioncraft.MODID,"hilt"+i),null,netIs,netRec);
		}
	}

	public static void loadRecipesforVanilla()
	{
		GameRegistry.addSmelting(ItemIndex.iceBucket, new ItemStack(Items.WATER_BUCKET, 1), 0.5F);
	}
	
	public static void glassRecipesInit()
	{
		GameRegistry.addSmelting(Blocks.GLASS, new ItemStack(ItemIndex.moltenglass, 1, 0), 0.5F);
	}
	
	public static void foodRecipesInit()
	{
		GameRegistry.addSmelting(ItemIndex.salmonRaw, new ItemStack(ItemIndex.salmon), 0.5F);
		GameRegistry.addSmelting(ItemIndex.yellowTailRaw, new ItemStack(ItemIndex.yellowTailCooked), 0.5F);
		GameRegistry.addSmelting(ItemIndex.raw_bacon, new ItemStack(ItemIndex.bacon), 0.5F);
		GameRegistry.addSmelting(ItemIndex.rawNuggets, new ItemStack(ItemIndex.chickenNuggets), 0.5F);
		GameRegistry.addSmelting(ItemIndex.bagofpopcorn, new ItemStack(ItemIndex.poppedbagofpopcorn), 0.5F);
		GameRegistry.addSmelting(ItemIndex.rawcorn, new ItemStack(ItemIndex.cookedCorn), 0.5F);
		GameRegistry.addSmelting(ItemIndex.raw_human, new ItemStack(ItemIndex.cooked_human), 0.5F);
		GameRegistry.addSmelting(ItemIndex.raw_horse, new ItemStack(ItemIndex.cooked_horse), 0.5F);
	}
	
	/** Recipes that usually create vanilla items or blocks **/
	public static void miscRecipesInit() 
	{
		//
//	done	GameRegistry.addRecipe(new ItemStack(IntegratedItems.Hammer, 1), new Object[] {"XXX", "XIX", " I ", 'I', Items.STICK, 'X', Items.IRON_INGOT});
//	d	CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(IntegratedItems.bloodstoneHammer, 1), new Object[] {"XXX", "XIX", " I ", 'I', "goldRod", 'X', "ingotBloodstone"}));
		
		//
		GameRegistry.addSmelting(Blocks.TALLGRASS, new ItemStack(ItemIndex.straw), 0.5F);

		GameRegistry.addSmelting(BlockIndex.dark_sand, new ItemStack(Blocks.GLASS), 0.5F);
		GameRegistry.addSmelting(ItemIndex.goldrod, new ItemStack(Items.GOLD_INGOT, 2), 0.5F);
		GameRegistry.addSmelting(Blocks.OBSIDIAN, new ItemStack(ItemIndex.obsidianingot), 0.5F);

		//NOTICE
//	d	CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(new ItemStack(IntegratedBlocks.bloodstonebricks, 4, 0), new Object[] {"oreBloodstone", "oreBloodstone", "oreBloodstone", "oreBloodstone"}));

	}
	
	public static void oreSmelting()
	{

		GameRegistry.addSmelting(new ItemStack(BlockIndex.surfaceOres, 1, 1), new ItemStack(ItemIndex.uncutLBGem), 0.5F);
		GameRegistry.addSmelting(new ItemStack(BlockIndex.surfaceOres, 1, 2), new ItemStack(ItemIndex.uncutDBGem), 0.5F);
		GameRegistry.addSmelting(new ItemStack(BlockIndex.surfaceOres, 1, 3), new ItemStack(Items.GOLD_INGOT), 0.5F);
		GameRegistry.addSmelting(new ItemStack(BlockIndex.surfaceOres, 1, 4), new ItemStack(ItemIndex.ingotSilver), 0.5F);
		GameRegistry.addSmelting(new ItemStack(BlockIndex.surfaceOres,1,5),new ItemStack(ItemIndex.ingotCopper),0.5f);
	}
	
//	public static void desertRecipesInit()
//	{
//		//Weathered Hieroglyphics to darkstone or state 0 hireoglyph
////	d	CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(IntegratedBlocks.hireoglyph, 1, 0), new Object[]   {"YYY", "YX ", 'X',        new ItemStack(IntegratedBlocks.DesertBlocks, 1, 12), 'Y', "chisel"}));
////	d	CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(IntegratedBlocks.DesertBlocks, 1, 7), new Object[] {"YYY", " X ", 'X',        new ItemStack(IntegratedBlocks.DesertBlocks, 1, 12), 'Y', "chisel"}));
//
//		//Quartz to carved quartz
////	d	CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(new ItemStack(IntegratedBlocks.DesertBlocks, 1, 8), new Object[] {new ItemStack(IntegratedBlocks.DesertBlocks, 1, 3), "chisel"}));
//
//	}
	
	public static void currencyRecipesInit()
	{

		GameRegistry.addSmelting(ItemIndex.crown,        new ItemStack(ItemIndex.ingotRefinedgold, 3, 0), 0.5F);
		GameRegistry.addSmelting(ItemIndex.goldbucket,   new ItemStack(ItemIndex.moltengold), 0.5F);
		GameRegistry.addSmelting(ItemIndex.silverbucket, new ItemStack(ItemIndex.moltensilver), 0.5F);
		GameRegistry.addSmelting(ItemIndex.bronzebucket, new ItemStack(ItemIndex.moltenbronze), 0.5F);
		GameRegistry.addSmelting(ItemIndex.copperbucket, new ItemStack(ItemIndex.moltencopper), 0.5F);

	}
	
	public static void loadORES()
	{
		GameRegistry.addSmelting(new ItemStack(BlockIndex.endOres,1,1),new ItemStack(ItemIndex.meltedventinite),0.5f);
		GameRegistry.addSmelting(new ItemStack(BlockIndex.endOres,1,0),new ItemStack(ItemIndex.meltedwizimite),0.5f);
        GameRegistry.addSmelting(new ItemStack(BlockIndex.netherOres),new ItemStack(ItemIndex.bloodstoneclump),0.5f);
		GameRegistry.addSmelting(ItemIndex.bloodstonedust, new ItemStack(ItemIndex.ingotbloodstone), 0.5F);
		GameRegistry.addSmelting(ItemIndex.bloodstoneclump, new ItemStack(ItemIndex.ingotbloodstone), 0.5F);
		GameRegistry.addSmelting(ItemIndex.irondust, new ItemStack(ItemIndex.superheatedironingot, 2, 0), 0.5F);
		GameRegistry.addSmelting(ItemIndex.goldDust, new ItemStack(Items.GOLD_INGOT), 0.1F);

	}

	public static void loadDesertRecipes()
	{
		GameRegistry.addSmelting(new ItemStack(BlockIndex.desertBlocks,1,1), new ItemStack(BlockIndex.desertBlocks, 1, 2), 0.1F);
		GameRegistry.addSmelting(BlockIndex.cherrywood, new ItemStack(Items.COAL, 1, 1), 0.5F);
		GameRegistry.addSmelting(BlockIndex.redCactus, new ItemStack(Items.DYE, 1, 2), 0.15F);
		GameRegistry.addSmelting(BlockIndex.greenCactus, new ItemStack(Items.DYE, 1, 2), 0.15F);
}

	public static void loadCurrency()
	{

//
//		GameRegistry.addShapelessRecipe(new ItemStack(IntegratedItems.Metadatacoin, 2, 0), new Object[] {new ItemStack(IntegratedItems.moltencopper), new ItemStack(IntegratedItems.CoinMould, 1, Reactioncraft.WILDCARD_VALUE)});
//		GameRegistry.addShapelessRecipe(new ItemStack(IntegratedItems.Metadatacoin, 2, 1), new Object[] {new ItemStack(IntegratedItems.moltenbronze), new ItemStack(IntegratedItems.CoinMould, 1, Reactioncraft.WILDCARD_VALUE)});
//		GameRegistry.addRecipe(new ItemStack(IntegratedItems.Metadatacoin, 1, 2), new Object[] {"###", "###", "###", '#', new ItemStack(IntegratedItems.Metadatacoin, 1, 1)});
//		GameRegistry.addRecipe(new ItemStack(IntegratedItems.Metadatacoin, 1, 3), new Object[] {"DDD", 'D', new ItemStack(IntegratedItems.Metadatacoin, 1, 2)});
//		GameRegistry.addRecipe(new ItemStack(IntegratedItems.Metadatacoin, 1, 4), new Object[] {"###", "###", " D ", '#', new ItemStack(IntegratedItems.Metadatacoin, 1, 3), 'D', IntegratedItems.Sack});
//		GameRegistry.addRecipe(new ItemStack(IntegratedItems.Metadatacoin, 1, 5), new Object[] {"###", " D ", '#', new ItemStack(IntegratedItems.Metadatacoin, 1, 4), 'D', IntegratedItems.Bag});
//		GameRegistry.addShapelessRecipe(new ItemStack(IntegratedItems.Metadatacoin, 2, 6), new Object[] {new ItemStack(IntegratedItems.moltensilver), new ItemStack(IntegratedItems.CoinMould, 1, Reactioncraft.WILDCARD_VALUE)});
//		GameRegistry.addRecipe(new ItemStack(IntegratedItems.Metadatacoin, 1, 7), new Object[] {"###", "###", "###", '#', new ItemStack(IntegratedItems.Metadatacoin, 1, 6)});
//		GameRegistry.addRecipe(new ItemStack(IntegratedItems.Metadatacoin, 1, 8), new Object[] {"DDD", 'D', new ItemStack(IntegratedItems.Metadatacoin, 1, 7)});
//		GameRegistry.addRecipe(new ItemStack(IntegratedItems.Metadatacoin, 1, 9), new Object[] {"###", "###", " D ", '#', new ItemStack(IntegratedItems.Metadatacoin, 1, 8), 'D', IntegratedItems.Sack});
//		GameRegistry.addRecipe(new ItemStack(IntegratedItems.Metadatacoin, 1, 10), new Object[] {"###", " D ", '#', new ItemStack(IntegratedItems.Metadatacoin, 1, 9), 'D', IntegratedItems.Bag});
//		GameRegistry.addShapelessRecipe(new ItemStack(IntegratedItems.Metadatacoin, 2, 11), new Object[] {new ItemStack(IntegratedItems.moltengold), new ItemStack(IntegratedItems.CoinMould, 1, Reactioncraft.WILDCARD_VALUE)});
//		GameRegistry.addRecipe(new ItemStack(IntegratedItems.Metadatacoin, 1, 12), new Object[] {"###", "###", "###", '#', new ItemStack(IntegratedItems.Metadatacoin, 2, 11)});
//		GameRegistry.addRecipe(new ItemStack(IntegratedItems.Metadatacoin, 1, 13), new Object[] {"DDD", 'D', new ItemStack(IntegratedItems.Metadatacoin, 2, 12)});
//		GameRegistry.addRecipe(new ItemStack(IntegratedItems.Metadatacoin, 1, 14), new Object[] {"###", "###", " D ", '#', new ItemStack(IntegratedItems.Metadatacoin, 2, 13), 'D', IntegratedItems.Sack});
//		GameRegistry.addRecipe(new ItemStack(IntegratedItems.Metadatacoin, 1, 15), new Object[] {"###", " D ", '#', new ItemStack(IntegratedItems.Metadatacoin, 2, 14), 'D', IntegratedItems.Bag});
	}

	public static void loadCore()
	{
		//GameRegistry.addRecipe(new ItemStack(IntegratedBlocks.enderportal, 1), new Object[] {"I I", "XXX", 'I', IntegratedItems.blackdiamond, 'X', new ItemStack(IntegratedBlocks.endOres, 1, 2)});
		GameRegistry.addSmelting(Blocks.SPONGE, new ItemStack(BlockIndex.newSponge), 0.1F);

		//GameRegistry.addRecipe(new ItemStack(IntegratedBlocks.snowblock, 1), new Object[] {"DD", "DD", 'D', Block.ice});
	}
}