package com.reactioncraft.registration;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.common.recipes.RecipeRegistry;
import com.reactioncraft.utils.constants;

public class RecipesManager
{
	public static void registerRecipes() 
	{
		RecipeRegistry.netrecipyInit();
    	RecipeRegistry.oreSmelting();
    	RecipeRegistry.glassRecipesInit();
    	RecipeRegistry.loadRecipesforVanilla();
    	RecipeRegistry.foodRecipesInit();
    	RecipeRegistry.loadORES();
    	RecipeRegistry.currencyRecipesInit();
    	RecipeRegistry.miscRecipesInit();
    	RecipeRegistry.loadDesertRecipes();
		if(Reactioncraft.Forestry)
				RecipeRegistry.forestry();
		if(Reactioncraft.IC2)
			RecipeRegistry.ic2();
    	RecipeRegistry.reactioncraftMachines();
	}
}