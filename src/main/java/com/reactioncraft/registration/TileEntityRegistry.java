package com.reactioncraft.registration;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.tiles.*;
import com.reactioncraft.utils.constants;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityRegistry 
{

	public static void registerTileEntities()
	{
        GameRegistry.registerTileEntity(TileEntityFreezer.class, constants.MODID+":freezer");
        GameRegistry.registerTileEntity(TileEntityBrickOven.class,  constants.MODID+":brickoven");
        GameRegistry.registerTileEntity(TileEntityClayalizer.class, constants.MODID+":clayalizer");
        //GameRegistry.registerTileEntity(TileEntityExtendedPiston.class, constants.MODID+":piston");
        GameRegistry.registerTileEntity(TileEntityReprogrammer.class, constants.MODID+":reprogrammer");
        GameRegistry.registerTileEntity(TileEntityConverter.class, constants.MODID+":Converter");
        GameRegistry.registerTileEntity(TileEntityBookcaseChest.class, constants.MODID+ ":bookcasechest");
        //GameRegistry.registerTileEntity(TileEntityMJ.class, constants.MODID+":mj");
	}
}