package com.reactioncraft.common.registration;

import com.reactioncraft.common.tiles.*;
import com.reactioncraft.common.utils.constants;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityRegistry 
{
	public static void registerTileEntities()
	{
        GameRegistry.registerTileEntity(TileEntityFreezer.class,           constants.MODID+":freezer");
        GameRegistry.registerTileEntity(TileEntityBrickOven.class,         constants.MODID+":brickoven");
        GameRegistry.registerTileEntity(TileEntityClayalizer.class,        constants.MODID+":clayalizer");
        //GameRegistry.registerTileEntity(TileEntityExtendedPiston.class,  constants.MODID+":piston");
        GameRegistry.registerTileEntity(TileEntityReprogrammer.class,      constants.MODID+":reprogrammer");
        GameRegistry.registerTileEntity(TileEntityTrigger.class,           constants.MODID+":trigger");
        //GameRegistry.registerTileEntity(TileEntityConverter.class,       constants.MODID+":Converter");
        GameRegistry.registerTileEntity(TileEntityBookcaseChest.class,     constants.MODID+":bookcasechestbase");
        GameRegistry.registerTileEntity(TileEntityHiddenBookChest.class,   constants.MODID+":bookcasechest");
        GameRegistry.registerTileEntity(TileEntityHiddenScrollChest.class, constants.MODID+":scrollcasechest");
	}
}