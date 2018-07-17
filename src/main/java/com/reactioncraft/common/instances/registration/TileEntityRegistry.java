package com.reactioncraft.common.instances.registration;

import com.reactioncraft.common.tiles.*;
import com.reactioncraft.common.utils.constants;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityRegistry 
{
	public static void registerTileEntities()
	{
        GameRegistry.registerTileEntity(TileEntityFreezer.class,            constants.MODID+":freezer");
        GameRegistry.registerTileEntity(TileEntityBrickOven.class,          constants.MODID+":brickoven");
        GameRegistry.registerTileEntity(TileEntityClayalizer.class,         constants.MODID+":clayalizer");
        //GameRegistry.registerTileEntity(TileEntityExtendedPiston.class,   constants.MODID+":piston");
        GameRegistry.registerTileEntity(TileEntityReprogrammer.class,       constants.MODID+":reprogrammer");
        GameRegistry.registerTileEntity(TileEntityTrigger.class,            constants.MODID+":trigger");
        GameRegistry.registerTileEntity(TileEntityBookcaseChest.class,      constants.MODID+":bookcasechestbase");
        GameRegistry.registerTileEntity(TileEntityHiddenBookChest.class,    constants.MODID+":bookcasechest");
        GameRegistry.registerTileEntity(TileEntityHiddenScrollChest.class,  constants.MODID+":scrollcasechest");
        GameRegistry.registerTileEntity(TileEntityMachine.class,            constants.MODID+":machine");
        GameRegistry.registerTileEntity(TileEntityEnergy.class,             constants.MODID+":energy");
        GameRegistry.registerTileEntity(TileEntityEnergyStorage.class,      constants.MODID+":energy_storage");
        GameRegistry.registerTileEntity(TileEntityWirelessConnector.class,  constants.MODID+":wireless_energy_connector");
        GameRegistry.registerTileEntity(TileEntityWirelessController.class, constants.MODID+":wireless_energy_controller");
	}
}