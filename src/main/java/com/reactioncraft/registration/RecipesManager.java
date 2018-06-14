package com.reactioncraft.registration;

<<<<<<< HEAD
import com.reactioncraft.Reactioncraft;
import com.reactioncraft.common.recipes.RecipeRegistry;
import com.reactioncraft.utils.constants;
=======
import com.reactioncraft.common.recipes.RecipeRegistry;
>>>>>>> origin/master

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
		if(Reactioncraft.forestry)
				RecipeRegistry.forestry();
		if(Reactioncraft.industrialcraft)
			RecipeRegistry.ic2();
    	RecipeRegistry.forestry();
    	RecipeRegistry.ic2();
    	RecipeRegistry.reactioncraftMachines();
	}
}