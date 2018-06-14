package com.reactioncraft.registration;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.blocks.*;
import com.reactioncraft.blocks.machines.BlockBrickOven;
import com.reactioncraft.blocks.machines.BlockClayalizer;
import com.reactioncraft.blocks.machines.BlockFreezer;
import com.reactioncraft.blocks.ores.BlockEndOre;
import com.reactioncraft.blocks.ores.BlockNetherOre;
import com.reactioncraft.blocks.ores.BlockSurfaceOre;
import com.reactioncraft.core.Logger;
import com.reactioncraft.items.ItemMulti;
import com.reactioncraft.registration.instances.BlockIndex;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockEndPortal;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class BlockRegistry
{
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
		//TODO
//        registerBlockItem(AncientPlant,registryEvent);

		registerBlockItem(BlockIndex.bloodstonebricks,registryEvent);
		registerBlockItem(BlockIndex.cherryPlanks,registryEvent);
		Item sand=registerBlockItem(BlockIndex.dark_sand,registryEvent);
		Reactioncraft.proxy.registerItemRenderer(sand,0,sand.getRegistryName().getResourcePath());

		ItemBlock bcitem= registerBlockItem(BlockIndex.bookcases,registryEvent);
		Reactioncraft.proxy.registerBlockItemRenderer(bcitem,12);
		ItemBlock dbitem=registerBlockItem(BlockIndex.desertBlocks,registryEvent);
		Reactioncraft.proxy.registerBlockItemRenderer(dbitem,9);

		ItemBlock gitem=registerBlockItem(BlockIndex.glowingGlass,registryEvent);
		Reactioncraft.proxy.registerBlockItemRenderer(gitem,16);

		ItemBlock hitem= registerBlockItem(BlockIndex.hieroglyph,registryEvent);
		Reactioncraft.proxy.registerBlockItemRenderer(hitem,13);

		ItemBlock soitem= registerBlockItem(BlockIndex.surfaceOres,registryEvent);
		Reactioncraft.proxy.registerBlockItemRenderer(soitem,6);

		ItemBlock noitem= registerBlockItem(BlockIndex.netherOres,registryEvent);
		Reactioncraft.proxy.registerBlockItemRenderer(noitem,5);

		ItemBlock eoitem= registerBlockItem(BlockIndex.endOres,registryEvent);
		Reactioncraft.proxy.registerBlockItemRenderer(eoitem,2);

		registerBlockItem(BlockIndex.enderportalframe,registryEvent);

		registerBlockItem(BlockIndex.cherrywood,registryEvent);
		registerBlockItem(BlockIndex.cherryTreeLeaves,registryEvent);
		registerBlockItem(BlockIndex.rchive,registryEvent);


		registerBlockItem(BlockIndex.freezer,registryEvent);
		registerBlockItem(BlockIndex.brickOven,registryEvent);
		registerBlockItem(BlockIndex.claylizer,registryEvent);
		registerBlockItem(BlockIndex.chainladder,registryEvent);
		ItemMulti columnItem=new ItemMulti(BlockIndex.column);
		columnItem.setRegistryName(BlockIndex.column.getRegistryName());
		registryEvent.getRegistry().register(columnItem);
		//NOTICE there must exist json item model for each block-item
		Reactioncraft.proxy.setItemBlockWithMetadataInventoryModel(columnItem ,"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15");

		ItemMulti minicolumnitem=new ItemMulti(BlockIndex.miniColumn);
		minicolumnitem.setRegistryName(BlockIndex.miniColumn.getRegistryName());
		registryEvent.getRegistry().register(minicolumnitem);
		Reactioncraft.proxy.setItemBlockWithMetadataInventoryModel(minicolumnitem ,"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15");

		registerBlockItem(BlockIndex.redCactus,registryEvent);
		registerBlockItem(BlockIndex.greenCactus,registryEvent);
		registerBlockItem(BlockIndex.newSponge,registryEvent);

		registerBlockItem(BlockIndex.cherryTreeSapling,registryEvent);
	}


	public void init(IForgeRegistry<Block> forgeRegistry)
	{		
		/* Metadata Blocks **/
		BlockIndex.column = (BlockColumn) register((new BlockColumn(Material.ROCK)).setHardness(3.0F),"columnReg",forgeRegistry);
		BlockIndex.miniColumn = (BlockMiniColumn) register ((new BlockMiniColumn(Material.ROCK)).setHardness(3.0F),"ColumnMini",forgeRegistry);
		//Turn Desert Plants Into Metadata Blocks ^^

		//Sand Blocks
		BlockIndex.dark_sand = (BlockDarkSand) register(new BlockDarkSand(Material.SAND),"sands",forgeRegistry).setHardness(0.5F);



		//Desert Blocks
		BlockIndex.desertBlocks = (BlockDesertMulti) register(new BlockDesertMulti(Material.ROCK),"desertblocks",forgeRegistry).setHardness(0.3F);
		BlockIndex.hieroglyph = (BlockHieroglyphMulti) register(new BlockHieroglyphMulti(Material.ROCK),"hieroglyph",forgeRegistry).setHardness(0.3F);
		
		//Bookshelf Blocks
		BlockIndex.bookcases = (BlockBookshelf) register(new BlockBookshelf(Material.WOOD),"bookcases",forgeRegistry).setHardness(3.0F);
		
		//Glass Blocks
		BlockIndex.glowingGlass = (BlockGlowingGlassMulti) register(new BlockGlowingGlassMulti(Material.GLASS, true, "glowingglass"),"glowingglass",forgeRegistry).setLightLevel(1).setHardness(0.0F);
		
		//Ore Blocks
		BlockIndex.surfaceOres = (BlockSurfaceOre) register(new BlockSurfaceOre(Material.ROCK),"surfaceOres",forgeRegistry).setHardness(3.0F);
		BlockIndex.netherOres  = (BlockNetherOre) register(new BlockNetherOre(Material.ROCK),"netherOres",forgeRegistry).setHardness(3.0F);
		BlockIndex.endOres     = (BlockEndOre) register(new BlockEndOre(Material.ROCK),"endores",forgeRegistry).setHardness(3.0F);

		//Wooden Blocks
		BlockIndex.cherryPlanks = (BlockBase) register(new BlockBase(Material.WOOD).setCreativeTab(Reactioncraft.Reactioncraft),"cherryplanks",forgeRegistry);
//		BlockIndex.Cherrywood= (BlockTree) register(new BlockTree(),"cherry_wood",forgeRegistry).setCreativeTab(Reactioncraft.Reactioncraft);
		BlockIndex.cherrywood = (BlockCherryTreeLog) register(new BlockCherryTreeLog(),"cherry_wood",forgeRegistry).setCreativeTab(Reactioncraft.Reactioncraft);
		BlockIndex.cherryTreeLeaves = (BlockCherryTreeLeaves) register(new BlockCherryTreeLeaves(),"cherry_leaves",forgeRegistry);

		//Wall Blocks

		/* Regular Blocks **/
		//Brick Blocks
		BlockIndex.bloodstonebricks = (BlockBase) register(new BlockBase(Material.ROCK).setCreativeTab(Reactioncraft.Reactioncraft),"bloodstonebricks",forgeRegistry);
		
		//Doors
		BlockIndex.woodenBookcase = (BlockBaseDoor) register(new BlockBaseDoor(Material.WOOD),"doorWbookcase",forgeRegistry).setHardness(3.0F);
		BlockIndex.ironBookcasedoor = (BlockBaseDoor) register(new BlockBaseDoor(Material.IRON),"doorIbookcase",forgeRegistry).setHardness(3.0F);
		BlockIndex.cherrydoor = (BlockBaseDoor) register(new BlockBaseDoor(Material.WOOD),"cherry_door",forgeRegistry).setHardness(3.0F);


		BlockIndex.freezer = (BlockFreezer) (new BlockFreezer(false))  .setHardness(3.5F);
		register(BlockIndex.freezer,"freezer",forgeRegistry).setCreativeTab(Reactioncraft.Reactioncraft);

		BlockIndex.brickOven = ((BlockBrickOven)     (new BlockBrickOven(false))  .setHardness(3.5F).setCreativeTab(Reactioncraft.Reactioncraft));
        register(BlockIndex.brickOven,"brickoven",forgeRegistry);

		BlockIndex.claylizer = ((BlockClayalizer)   (new BlockClayalizer(false)).setHardness(3.5F).setCreativeTab(Reactioncraft.Reactioncraft));
		register(BlockIndex.claylizer,"claylizer",forgeRegistry);

		BlockIndex.rchive= (BlockHive) register( new BlockHive(),"hive",forgeRegistry);

		BlockIndex.cherryTreeSapling = (BlockCherryTreeSapling) register(new BlockCherryTreeSapling(),"cherry_tree_sapling",forgeRegistry);

		BlockIndex.ancientPlant= (BlockAncientPlant) register(new BlockAncientPlant(),"ancientplant",forgeRegistry);
		BlockIndex.cornBlock= (BlockCornPlant) register(new BlockCornPlant(),"corn",forgeRegistry);

		BlockIndex.newSponge= (BlockNewSponge) register(new BlockNewSponge(16),"sponge",forgeRegistry);

		BlockIndex.chainladder= (BlockChainLadder) register(new BlockChainLadder(),"chain_ladder",forgeRegistry);

		BlockIndex.redCactus = (BlockBush) register(new BlockCactus().setCreativeTab(Reactioncraft.Reactioncraft),"cactus1",forgeRegistry);
		BlockIndex.greenCactus = (BlockBush) register(new BlockCactus().setCreativeTab(Reactioncraft.Reactioncraft),"cactus2",forgeRegistry);

		BlockIndex.enderportalframe= (BlockEndPortalFrame2) register(new BlockEndPortalFrame2().setCreativeTab(Reactioncraft.Reactioncraft),"end_portal_frame",forgeRegistry);
	}
	

	/**Registers a block and sets unlocalized name*/
	private static Block register(Block block,String identifier,IForgeRegistry<Block> forgeRegistry )
	{
			block.setRegistryName(Reactioncraft.MODID,identifier);
			block.setUnlocalizedName(Reactioncraft.MODID+"."+identifier);
			forgeRegistry.register(block);
			return block;
	}
}