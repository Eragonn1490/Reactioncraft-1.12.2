package com.reactioncraft.core;

import javax.annotation.Nullable;

import com.reactioncraft.common.containers.*;
import com.reactioncraft.common.tiles.*;
import com.reactioncraft.common.ui.*;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class ServerProxy implements IGuiHandler
{
	public void registerItemRenderer(Item item, int meta, String id) 
	{
	}

	public void setItemBlockWithMetadataInventoryModel(ItemBlock itemBlock, String... variants)
	{
	}

	public void registerRenderInformation() 
	{
	}

	public void registerItemBlockRenderer(ItemBlock itemBlock, int meta) 
	{
	}

	/**
	 *
	 * @param item block item
	 * @param metadataRange 1-16
	 */
	public void registerBlockItemRenderer(ItemBlock item, int metadataRange){}

	public enum GuiIDs
	{
		BRICK_OVEN,
		CLAYLISER,
		FREEZER,
		Bookcase,
		Scrollcase,
		reprogrammer,
		ENERGY_BANK
	}

	@Override
	@Nullable
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z){
		final TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));

		if(tileEntity!=null) {
			switch (GuiIDs.values()[ID]) {
			case BRICK_OVEN:
				return new ContainerBrickOven(player.inventory, (TileEntityBrickOven) tileEntity);
			case FREEZER:
				return new ContainerFreezer(player.inventory, (TileEntityFreezer) tileEntity);
			case CLAYLISER:
				return new ContainerClayalizer(player.inventory, (TileEntityClayalizer) tileEntity);
			case Bookcase:
				return new ContainerBookChest(player.inventory, (TileEntityHiddenBookChest) tileEntity, player);
			case Scrollcase:
				return new ContainerScrollChest(player.inventory, (TileEntityHiddenScrollChest) tileEntity, player);
			case reprogrammer:
				return new ContainerReprogrammer(player, (TileEntityReprogrammer) tileEntity);
			case ENERGY_BANK:
				return new ContainerEnergyStorage(player, (TileEntityEnergyStorage) tileEntity);
			}
			throw new IllegalArgumentException("No gui with id " + ID);
		}
		return null;
	}

	@Override
	@Nullable
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		final TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));

		if (tileEntity != null) {
			switch (GuiIDs.values()[ID]) 
			{
			case BRICK_OVEN:   return new GuiBrickoven(player.inventory,   (TileEntityBrickOven)         tileEntity);
			case CLAYLISER:    return new GuiClayalizer (player.inventory, (TileEntityClayalizer)        tileEntity);
			case FREEZER:      return new GuiFreezer    (player.inventory, (TileEntityFreezer)           tileEntity);
			case Bookcase:     return new GUIBookChest( player.inventory,  (TileEntityHiddenBookChest)   tileEntity);
			case Scrollcase:   return new GUIScrollChest(player.inventory, (TileEntityHiddenScrollChest) tileEntity);
			case reprogrammer: return new GuiReprogramer(player,           (TileEntityReprogrammer)      tileEntity);
			case ENERGY_BANK:  return new GuiEnergyStorage(player,         (TileEntityEnergyStorage)     tileEntity);
			}
		}
		return null;
	}

	public World getLocalWorld()
	{
		return Minecraft.getMinecraft().world;
	}
}