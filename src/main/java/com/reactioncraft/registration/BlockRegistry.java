package com.reactioncraft.registration;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.blocks.*;
import com.reactioncraft.blocks.BlockBookshelf;
import com.reactioncraft.blocks.BlockCactus;
import com.reactioncraft.blocks.machines.*;
import com.reactioncraft.blocks.ores.*;
import com.reactioncraft.vanillareplacements.*;
import com.reactioncraft.core.Logger;
import com.reactioncraft.items.ItemMulti;
import com.reactioncraft.registration.instances.BlockIndex;
import com.reactioncraft.utils.constants;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class BlockRegistry
{
	//Vanilla Overrides
	public static Block BOOKSHELF = null;

	public static ItemBlock registerBlockItem(Block block, RegistryEvent.Register<Item> registryEvent)
	{
		if(block==null)
		{
			Logger.error("Attempt to register item for null block");
			return null;
		}
		else if(block.getRegistryName()==null)
		{
			Logger.error("Attempt to register item for "+block.getClass().getCanonicalName()+" without registry name");
			return null;
		}
		ItemBlock itemBlock;

		if (block instanceof MetadataArray || block instanceof BlockBaseDoor)
		{
			itemBlock=new ItemMulti(block);

		}
		else{
			itemBlock=new ItemBlock(block);
		}
		itemBlock.setRegistryName(block.getRegistryName());
		registryEvent.getRegistry().register(itemBlock);
		if(block instanceof BlockBase)
		{
			BlockBase blockBase= (BlockBase) block;
			blockBase.registerItemModel(itemBlock);
		}
		else
			Reactioncraft.proxy.registerItemRenderer(itemBlock,0,block.getRegistryName().getResourcePath());
		return itemBlock;
	}

	@SuppressWarnings("unused")
	@SubscribeEvent
	public void registerBlocks(RegistryEvent.Register<Block> registryEvent)
	{
		init(registryEvent.getRegistry());
	}

	//now the items must be registered separately
	@SuppressWarnings("unused")
	@SubscribeEvent
	public void registerBlockItems(RegistryEvent.Register<Item> registryEvent)
	{
		//Vanilla Replacements
		registerBlockItem(Blocks.BOOKSHELF, registryEvent);
		Logger.info("Reactioncraft has successfully Replaced the bookshelf!");

		//Reactioncraft Blocks
		registerBlockItem(BlockIndex.bloodstonebricks,registryEvent);
		registerBlockItem(BlockIndex.cherryPlanks,registryEvent);
		registerBlockItem(BlockIndex.cherrywood,registryEvent);
		registerBlockItem(BlockIndex.cherryTreeLeaves,registryEvent);
		registerBlockItem(BlockIndex.rchive,registryEvent);


		registerBlockItem(BlockIndex.freezer,registryEvent);
		registerBlockItem(BlockIndex.brickOven,registryEvent);
		registerBlockItem(BlockIndex.claylizer,registryEvent);

		registerBlockItem(BlockIndex.chainladder,registryEvent);

		registerBlockItem(BlockIndex.redCactus,registryEvent);
		registerBlockItem(BlockIndex.greenCactus,registryEvent);

		registerBlockItem(BlockIndex.cherryTreeSapling,registryEvent);

		registerBlockItem(BlockIndex.cornTall, registryEvent);

		//Longer Piston
		//registerBlockItem(BlockIndex.extendedPiston, registryEvent);
		//registerBlockItem(BlockIndex.extendedPistonsticky, registryEvent);

		//Gate
		registerBlockItem(BlockIndex.gate, registryEvent);
		registerBlockItem(BlockIndex.fence, registryEvent);

		//Transmitter Blocks
		registerBlockItem(BlockIndex.transmitter, registryEvent);
		registerBlockItem(BlockIndex.reprogrammer, registryEvent);

		//RF-EU-MJ Converter
		registerBlockItem(BlockIndex.bloodstoneEnergyBlock, registryEvent);

		registerBlockItem(BlockIndex.enderportalframe,registryEvent);
		
		//tripwire rail
		registerBlockItem(BlockIndex.tripwirerail, registryEvent);


		/** Metadata Blocks **/
		//NOTICE there must exist json item model for each block-item
		ItemMulti desertpatterns = new ItemMulti(BlockIndex.desertpatterns);
		desertpatterns.setRegistryName(BlockIndex.desertpatterns.getRegistryName());
		registryEvent.getRegistry().register(desertpatterns);
		Reactioncraft.proxy.setItemBlockWithMetadataInventoryModel(desertpatterns ,"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15");

		//NOTICE there must exist json item model for each block-item
		ItemMulti hieroglyph = new ItemMulti(BlockIndex.hieroglyph);
		hieroglyph.setRegistryName(BlockIndex.hieroglyph.getRegistryName());
		registryEvent.getRegistry().register(hieroglyph);
		Reactioncraft.proxy.setItemBlockWithMetadataInventoryModel(hieroglyph ,"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15");

		//NOTICE there must exist json item model for each block-item
		ItemMulti surfaceOres = new ItemMulti(BlockIndex.surfaceOres);
		surfaceOres.setRegistryName(BlockIndex.surfaceOres.getRegistryName());
		registryEvent.getRegistry().register(surfaceOres);
		Reactioncraft.proxy.setItemBlockWithMetadataInventoryModel(surfaceOres ,"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15");

		//NOTICE there must exist json item model for each block-item
		ItemMulti netherOres = new ItemMulti(BlockIndex.netherOres);
		netherOres.setRegistryName(BlockIndex.netherOres.getRegistryName());
		registryEvent.getRegistry().register(netherOres);
		Reactioncraft.proxy.setItemBlockWithMetadataInventoryModel(netherOres ,"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15");

		//NOTICE there must exist json item model for each block-item
		ItemMulti sands =new ItemMulti(BlockIndex.dark_sand);
		sands.setRegistryName(BlockIndex.dark_sand.getRegistryName());
		registryEvent.getRegistry().register(sands);
		Reactioncraft.proxy.setItemBlockWithMetadataInventoryModel(sands ,"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15");

		//NOTICE there must exist json item model for each block-item
		ItemMulti glowingglass =new ItemMulti(BlockIndex.glowingGlass);
		glowingglass.setRegistryName(BlockIndex.glowingGlass.getRegistryName());
		registryEvent.getRegistry().register(glowingglass);
		Reactioncraft.proxy.setItemBlockWithMetadataInventoryModel(glowingglass ,"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15");

		//NOTICE there must exist json item model for each block-item
		ItemMulti bookcases =new ItemMulti(BlockIndex.bookcases);
		bookcases.setRegistryName(BlockIndex.bookcases.getRegistryName());
		registryEvent.getRegistry().register(bookcases);
		Reactioncraft.proxy.setItemBlockWithMetadataInventoryModel(bookcases ,"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15");

		//NOTICE there must exist json item model for each block-item
		ItemMulti endores =new ItemMulti(BlockIndex.endOres);
		endores.setRegistryName(BlockIndex.endOres.getRegistryName());
		registryEvent.getRegistry().register(endores);
		Reactioncraft.proxy.setItemBlockWithMetadataInventoryModel(endores ,"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15");

		//NOTICE there must exist json item model for each block-item
		ItemMulti desertblocks = new ItemMulti(BlockIndex.desertBlocks);
		desertblocks.setRegistryName(BlockIndex.desertBlocks.getRegistryName());
		registryEvent.getRegistry().register(desertblocks);
		Reactioncraft.proxy.setItemBlockWithMetadataInventoryModel(desertblocks ,"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15");

		//NOTICE there must exist json item model for each block-item
		ItemMulti columnItem =new ItemMulti(BlockIndex.column);
		columnItem.setRegistryName(BlockIndex.column.getRegistryName());
		registryEvent.getRegistry().register(columnItem);
		Reactioncraft.proxy.setItemBlockWithMetadataInventoryModel(columnItem ,"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15");


		//NOTICE there must exist json item model for each block-item
		ItemMulti minicolumnitem=new ItemMulti(BlockIndex.miniColumn);
		minicolumnitem.setRegistryName(BlockIndex.miniColumn.getRegistryName());
		registryEvent.getRegistry().register(minicolumnitem);
		Reactioncraft.proxy.setItemBlockWithMetadataInventoryModel(minicolumnitem ,"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15");

		//NOTICE there must exist json item model for each block-item
		ItemMulti smallestColumn=new ItemMulti(BlockIndex.smallestColumn);
		smallestColumn.setRegistryName(BlockIndex.smallestColumn.getRegistryName());
		registryEvent.getRegistry().register(smallestColumn);
		Reactioncraft.proxy.setItemBlockWithMetadataInventoryModel(smallestColumn ,"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15");
	}


	public void init(IForgeRegistry<Block> forgeRegistry)
	{		
		//Vanilla Block Replacements
		BOOKSHELF = (ModifiedBlockBookshelf) register(new ModifiedBlockBookshelf(),"bookshelf",forgeRegistry).setHardness(1.5F);

		/** Metadata Blocks **/
		//Columns
		BlockIndex.column = (BlockColumn) register((new BlockColumn(Material.ROCK)).setHardness(3.0F),"columnReg",forgeRegistry);
		BlockIndex.miniColumn = (BlockMiniColumn) register ((new BlockMiniColumn(Material.ROCK)).setHardness(3.0F),"ColumnMini",forgeRegistry);
		BlockIndex.smallestColumn = (BlockSmallestColumn) register ((new BlockSmallestColumn(Material.ROCK)).setHardness(3.0F),"smallestColumn",forgeRegistry);

		//Ore Blocks
		BlockIndex.surfaceOres = (BlockSurfaceOre) register(new BlockSurfaceOre(Material.ROCK),"surfaceOres",forgeRegistry).setHardness(3.0F);
		BlockIndex.netherOres  = (BlockNetherOre) register(new BlockNetherOre(Material.ROCK),"netherOres",forgeRegistry).setHardness(3.0F);
		BlockIndex.endOres     = (BlockEndOre) register(new BlockEndOre(Material.ROCK),"endores",forgeRegistry).setHardness(3.0F);

		//Desert Blocks
		BlockIndex.desertBlocks = (BlockDesertMulti) register(new BlockDesertMulti(Material.ROCK), "desertblocks",forgeRegistry).setHardness(3.0F);
		BlockIndex.desertpatterns = (BlockPaintedDarkstone) register(new BlockPaintedDarkstone(Material.ROCK), "desertpatterns",forgeRegistry).setHardness(3.0F);

		//Turn Desert Plants Into Metadata Blocks ^^

		//Sand Blocks
		BlockIndex.dark_sand = (BlockDarkSand) register(new BlockDarkSand(Material.SAND),"sands",forgeRegistry).setHardness(0.5F);

		BlockIndex.hieroglyph = (BlockHieroglyphMulti) register(new BlockHieroglyphMulti(Material.ROCK),"hieroglyph",forgeRegistry).setHardness(3.0F);

		//Bookshelf Blocks
		BlockIndex.bookcases = (BlockBookshelf) register(new BlockBookshelf(Material.WOOD),"bookcases",forgeRegistry).setHardness(1.5F);

		//Glass Blocks
		BlockIndex.glowingGlass = (BlockGlowingGlassMulti) register(new BlockGlowingGlassMulti(Material.GLASS, true, "glowingglass"),"glowingglass",forgeRegistry).setLightLevel(1).setHardness(0.3F);

		//Wooden Blocks
		BlockIndex.cherryPlanks = (BlockBase) register(new BlockBase(Material.WOOD).setHardness(2.0F).setResistance(5.0F).setCreativeTab(Reactioncraft.Reactioncraft),"cherryplanks",forgeRegistry);
		BlockIndex.cherryPlanks.setHarvestLevel("axe", 0); //Ensures that its breakable by the axe correctly

		BlockIndex.cherrywood = (BlockCherryTreeLog) register(new BlockCherryTreeLog(),"cherry_wood",forgeRegistry).setCreativeTab(Reactioncraft.Reactioncraft);
		BlockIndex.cherryTreeLeaves = (BlockCherryTreeLeaves) register(new BlockCherryTreeLeaves(),"cherry_leaves",forgeRegistry);

		//Wall Blocks

		/* Regular Blocks **/
		//Brick Blocks
		BlockIndex.bloodstonebricks = (BlockBase) register(new BlockBase(Material.ROCK).setCreativeTab(Reactioncraft.Reactioncraft),"bloodstonebricks",forgeRegistry).setHardness(90.0F);

		//Doors
		BlockIndex.woodenBookcase = (BlockBaseDoor) register(new BlockBaseDoor(Material.WOOD),"doorWbookcase",forgeRegistry).setHardness(1.5F);
		BlockIndex.ironBookcasedoor = (BlockBaseDoor) register(new BlockBaseDoor(Material.IRON),"doorIbookcase",forgeRegistry).setHardness(5.0F);
		BlockIndex.cherrydoor = (BlockBaseDoor) register(new BlockBaseDoor(Material.WOOD),"cherry_door",forgeRegistry).setHardness(1.5F);

		/* Special Regular Blocks **/
		//Machine Blocks
		BlockIndex.freezer = (BlockFreezer) (new BlockFreezer(false))  .setHardness(3.5F);
		register(BlockIndex.freezer,"freezer",forgeRegistry).setCreativeTab(Reactioncraft.Reactioncraft);

		BlockIndex.brickOven = ((BlockBrickOven)     (new BlockBrickOven(false))  .setHardness(3.5F).setCreativeTab(Reactioncraft.Reactioncraft));
		register(BlockIndex.brickOven,"brickoven",forgeRegistry);

		BlockIndex.claylizer = ((BlockClayalizer)   (new BlockClayalizer(false)).setHardness(3.5F).setCreativeTab(Reactioncraft.Reactioncraft));
		register(BlockIndex.claylizer,"claylizer",forgeRegistry);


		BlockIndex.rchive= (BlockHive) register( new BlockHive(),"hive",forgeRegistry);

		BlockIndex.cherryTreeSapling = (BlockCherryTreeSapling) register(new BlockCherryTreeSapling(),"cherry_tree_sapling",forgeRegistry);

		BlockIndex.ancientPlant = (BlockAncientPlant) register(new BlockAncientPlant(),"ancientplant",forgeRegistry);
		BlockIndex.cornBlock= (BlockCornPlant) register(new BlockCornPlant(),"corn",forgeRegistry);

		BlockIndex.cornTall = (BlockCornTall) register(new BlockCornTall(), "tallcorn", forgeRegistry);

		BlockIndex.chainladder= (BlockChainLadder) register(new BlockChainLadder(),"chain_ladder",forgeRegistry);

		BlockIndex.redCactus = (BlockBush) register(new BlockCactus().setCreativeTab(Reactioncraft.Reactioncraft),"cactus1",forgeRegistry);
		BlockIndex.greenCactus = (BlockBush) register(new BlockCactus().setCreativeTab(Reactioncraft.Reactioncraft),"cactus2",forgeRegistry);

		//Longer Piston
		//BlockIndex.extendedPiston = (BlockExtendedPiston) register(new BlockExtendedPiston(false), "pistonBase", forgeRegistry);
		//BlockIndex.extendedPistonsticky = (BlockExtendedPiston) register(new BlockExtendedPiston(true), "pistonStickyBase", forgeRegistry);
		//BlockIndex.extendedPistonHead = (BlockExtendedPistonHead) register(new BlockExtendedPistonHead(), "pistonBase", forgeRegistry);

		//Gate
		BlockIndex.gate = (BlockGate) register(new BlockGate().setCreativeTab(Reactioncraft.Reactioncraft), "gate", forgeRegistry) .setHardness(3.5F);
		BlockIndex.fence = (BlockCustomFence) register(new BlockCustomFence(Material.IRON, BlockPlanks.EnumType.OAK.getMapColor()) .setHardness(3.5F).setBlockUnbreakable().setCreativeTab(null), "fence", forgeRegistry);

		//Transmitter
		BlockIndex.transmitter = (BlockTransmitter) register(new BlockTransmitter(Material.ROCK).setHardness(3.0F),"transmitter",forgeRegistry);
		BlockIndex.reprogrammer = (BlockReprogrammer) register(new BlockReprogrammer(Material.ROCK).setHardness(3.0F),"reprogrammer",forgeRegistry);

		//
		BlockIndex.bloodstoneEnergyBlock = (BlockBloodstoneConverter) register(new BlockBloodstoneConverter(Material.ROCK).setHardness(3.0F),"bloodstoneEnergyBlock",forgeRegistry);

		BlockIndex.enderportalframe= (BlockEndPortalFrame2) register(new BlockEndPortalFrame2().setCreativeTab(Reactioncraft.Reactioncraft),"end_portal_frame",forgeRegistry);
		
		BlockIndex.tripwirerail = (BlockTripwirerail) register(new BlockTripwirerail(Material.ROCK),"tripwirerail",forgeRegistry);
	}


	/**Registers a block and sets unlocalized name*/
	private static Block register(Block block,String identifier,IForgeRegistry<Block> forgeRegistry )
	{
		block.setRegistryName(constants.MODID,identifier);
		block.setUnlocalizedName(constants.MODID+"."+identifier);
		forgeRegistry.register(block);
		return block;
	}
}