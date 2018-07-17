package com.reactioncraft.common.instances.registration;

import com.reactioncraft.common.recipes.RecipeRegistry;
import com.reactioncraft.common.utils.constants;

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
    	RecipeRegistry.reactioncraftMachines();
    	
		if(constants.Forestry == true)
				RecipeRegistry.forestry();
		if(constants.IC2 == true)
				RecipeRegistry.ic2();
	}
}