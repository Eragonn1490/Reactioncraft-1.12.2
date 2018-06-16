package com.reactioncraft.registration;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.tiles.TileEntityBrickOven;
import com.reactioncraft.tiles.TileEntityClayalizer;
import com.reactioncraft.tiles.TileEntityFreezer;
import com.reactioncraft.utils.constants;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityRegistry 
{

	public static void registerTileEntities()
	{
		//GameRegistry.registerTileEntity(TileEntityBookcaseChest.class, "Bookcasechest");
        GameRegistry.registerTileEntity(TileEntityFreezer.class, constants.MODID+":freezer");
        GameRegistry.registerTileEntity(TileEntityBrickOven.class,  constants.MODID+":brickoven");
        GameRegistry.registerTileEntity(TileEntityClayalizer.class, constants.MODID+":clayalizer");
	}
}