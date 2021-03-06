package com.reactioncraft.api;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.common.instances.BlockIndex;
import com.reactioncraft.common.instances.ItemIndex;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictionaryRegistry
{
	//This Class is Included so that anyone interested in using the oredictionary name can find out how they are registered in Reactioncraft Easily.
	//Do not Edit this class, might cause un-expected results
    public static void registerOres()
    {
        OreDictionary.registerOre("darkclay", new ItemStack(ItemIndex.sandStonePaste));
        OreDictionary.registerOre("ingotRefinedgold", new ItemStack(ItemIndex.ingotRefinedgold, 1, 0));
        OreDictionary.registerOre("ingotBronze", new ItemStack(ItemIndex.ingotBronze, 1, 0));
        OreDictionary.registerOre("ingotCopper", new ItemStack(ItemIndex.ingotCopper, 1, 0));
        OreDictionary.registerOre("gemRuby", ItemIndex.ruby);
        OreDictionary.registerOre("oreDesertCoal", new ItemStack(BlockIndex.surfaceOres, 1, 0));
        OreDictionary.registerOre("DarkCobble", new ItemStack(BlockIndex.desertBlocks, 1, 1));
        OreDictionary.registerOre("DarkStone",  new ItemStack(BlockIndex.desertBlocks, 1, 7));
        OreDictionary.registerOre("oreDesertGold", new ItemStack(BlockIndex.desertBlocks, 1, 10));
        OreDictionary.registerOre("BloodstoneBrick", new ItemStack(BlockIndex.bloodstonebricks));
        OreDictionary.registerOre("darkStoneCarved", new ItemStack(BlockIndex.desertBlocks, 1, 7));
        OreDictionary.registerOre("darkstonebrick", new ItemStack(BlockIndex.desertBlocks, 1, 3));
        OreDictionary.registerOre("plankWood", new ItemStack(BlockIndex.cherry_planks));
        OreDictionary.registerOre("cherryWood", new ItemStack(BlockIndex.cherrywood));
        OreDictionary.registerOre("scroll", new ItemStack(ItemIndex.scroll));
        OreDictionary.registerOre("rawCorn", new ItemStack(ItemIndex.rawcorn));
        OreDictionary.registerOre("cheese", new ItemStack(ItemIndex.cheese));
        OreDictionary.registerOre("goldRod", new ItemStack(ItemIndex.goldrod));
        OreDictionary.registerOre("ingotObsidian", new ItemStack(ItemIndex.obsidianingot));
        OreDictionary.registerOre("diamondBlack", new ItemStack(ItemIndex.blackdiamond));
        OreDictionary.registerOre("ingotBloodstone", new ItemStack(ItemIndex.ingotbloodstone));
        OreDictionary.registerOre("ingotSilver", new ItemStack(ItemIndex.ingotSilver));
        OreDictionary.registerOre("ingotSuperheatediron", new ItemStack(ItemIndex.superheatedironingot));
        OreDictionary.registerOre("ironDust", new ItemStack(ItemIndex.irondust));
        OreDictionary.registerOre("shardDragonstone", new ItemStack(ItemIndex.dragonstoneshard));
        OreDictionary.registerOre("gemDragonstone", new ItemStack(ItemIndex.gemdragonstone));
        OreDictionary.registerOre("dyePurple", new ItemStack(ItemIndex.dragonstoneshard));
        OreDictionary.registerOre("bones", new ItemStack(ItemIndex.bones));
        OreDictionary.registerOre("wrappedCorn", new ItemStack(ItemIndex.wrappedcorn));
        
        //Make all doors equal
        OreDictionary.registerOre("doorWood", Items.ACACIA_DOOR);
        OreDictionary.registerOre("doorWood", Items.BIRCH_DOOR);
        OreDictionary.registerOre("doorWood", Items.DARK_OAK_DOOR);
        OreDictionary.registerOre("doorWood", Items.OAK_DOOR);
        OreDictionary.registerOre("doorWood", Items.JUNGLE_DOOR);
        OreDictionary.registerOre("doorWood", Items.SPRUCE_DOOR);
        //
        OreDictionary.registerOre("doorWood", new ItemStack(ItemIndex.cherry_door));
        OreDictionary.registerOre("doorWood", new ItemStack(ItemIndex.woodenBookcasedoor));
        
        //Macerated Nether Gold
        OreDictionary.registerOre("clump_gold", new ItemStack(ItemIndex.goldDust));
        
        //Nether Ores
        OreDictionary.registerOre("oreBloodstone",         new ItemStack(BlockIndex.netherOres, 1, 4));
        OreDictionary.registerOre("oreNetherBlackDiamond", new ItemStack(BlockIndex.netherOres, 1, 0));
        OreDictionary.registerOre("oreNetherDragonstone",  new ItemStack(BlockIndex.netherOres, 1, 2));
        OreDictionary.registerOre("oreNetherDiamondOre",   new ItemStack(BlockIndex.netherOres, 1, 1));
        OreDictionary.registerOre("oreNetherGoldOre",      new ItemStack(BlockIndex.netherOres, 1, 3));
        
        //Chisels
        // "hammer" are the base teir
        // "hammerT1" are the ones capable of cutting anything harder then bloodstone
        OreDictionary.registerOre("hammer",   new ItemStack(ItemIndex.hammer          , 1, Reactioncraft.WILDCARD_VALUE));
        OreDictionary.registerOre("hammer",   new ItemStack(ItemIndex.bloodstoneHammer, 1, Reactioncraft.WILDCARD_VALUE));
        OreDictionary.registerOre("hammerT1", new ItemStack(ItemIndex.bloodstoneHammer, 1, Reactioncraft.WILDCARD_VALUE));
        
        //Chisels
        // "chisel" are the base teir
        // "chiselT2" are the ones capable of cutting anything harder then diamond
        // "chiselT3" are the only ones capable of cutting bloodstone
        OreDictionary.registerOre("chisel",   new ItemStack(ItemIndex.flintChisel     , 1, Reactioncraft.WILDCARD_VALUE));
        OreDictionary.registerOre("chisel",   new ItemStack(ItemIndex.copperChisel    , 1, Reactioncraft.WILDCARD_VALUE));
        OreDictionary.registerOre("chisel",   new ItemStack(ItemIndex.goldChisel      , 1, Reactioncraft.WILDCARD_VALUE));
        OreDictionary.registerOre("chisel",   new ItemStack(ItemIndex.diamondChisel   , 1, Reactioncraft.WILDCARD_VALUE));
        OreDictionary.registerOre("chisel",   new ItemStack(ItemIndex.bloodstoneChisel, 1, Reactioncraft.WILDCARD_VALUE));
        OreDictionary.registerOre("chiselT2", new ItemStack(ItemIndex.diamondChisel   , 1, Reactioncraft.WILDCARD_VALUE));
        OreDictionary.registerOre("chiselT2", new ItemStack(ItemIndex.bloodstoneChisel, 1, Reactioncraft.WILDCARD_VALUE));
        OreDictionary.registerOre("chiselT3", new ItemStack(ItemIndex.bloodstoneChisel, 1, Reactioncraft.WILDCARD_VALUE));
        
        //For IC2 Macerator
        OreDictionary.registerOre("oreGold",   new ItemStack(BlockIndex.netherOres,  1, 3));
        OreDictionary.registerOre("oreGold",   new ItemStack(BlockIndex.surfaceOres, 1, 3));
        OreDictionary.registerOre("oreSilver", new ItemStack(BlockIndex.surfaceOres, 1, 4));
    }
}