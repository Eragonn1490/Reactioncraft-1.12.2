package com.reactioncraft;

public class Todolist 
{
	/** This Class is just here as a list of things to fix **/
	
	/* New Things / Things to get to
	 * 
	 * Advancements
	 * Putting Out Mining Helmet if you get wet or right click water blocks.
	 * Fix Net Getting uses
	 * Drawbridge? Portcullis but vertical 
	 * Wireless Redstone Item (either on command Typed or right click power a related block with redstone)
	 * Add can Silk touch to desert coal core and gems
	 * Edit Pixels on Ingot Mould
	 * Add Names to Coins
	 * Recipe for Kingly Armour
	 * Add Names to Villager Types
	 * Finish Vanilla Villager Trades
	 * Lever , Chest, 
	 * Treasure Piles? (Maybe)
	 * Make the Scroll Act like a book.
	 * Redesign corn item textures!
	 * Make sure Banker Villager can Spawn in Bank House
	 * EU - TO RF- To - MJ converter block.
	 * Redstone Rail - cart over rail stops redstone current until it passes  (to allow "tripwire" rail)
	 * Make Entity in love from EntityAiEatGrassMate last longer
	 * Hireoglyph Recipes Changed to using machine
	 * Villager drop with meat cleaver
	 * Longer Piston?
	 * Breaker Rail (allows redstone current to pass through, stops temporarily when a car is over the block or a connected breaker block)
	 * Fix Boreheads for Railcraft (When it updates to 1.12.2)
	 * Make Frame Useful for forestry again?
	 * Add bee species? that uses honeycomb and pollen comb?
	 * Re-Added Food Drop to Millenaire Villagers (If you kill with a meat cleaver, you get a human leg.. (v6.3.1, Broken v6.5.0)
	 * Re-Added Bones Drop to Millenaire Villagers (v6.3.1 , Broken v6.5.0)
	 *	
	 *
	 */
	
	/*   Important Things to fix for 7.0.0
	 * 
	 * Fixed Rotation of Meat cleaver 
	 * Fixed Rotation of paint brush 
	 * Fix Smallest Column Model & Block Bounds 
	 * Fix Claylizer TE
	 * Way for mods to add bone drops
	 * Fix Villager profession reset, NBT not stored? (Removes Profession if caught with net and respawned)
	 * Creative net breaks after one use (shouldn't)
	 * fix raw human drop with meat cleaver
	 * Add Statues and fix block bounds
	 * Add Block's & Models to Bag of Coins (for future treasure update)
	 * Add Jellyfish
	 * remove unused columns from creative tabs for (v7.0.0)
	 * 
	 */
	
	
	/* Added 
	 * 
	 * ancient fruit needs to be edible
	 * Portcullis (Can be up to 90 Blocks Tall) & Portcullis gate block. (v0.4.5.1)
	 * Reactioncraft Corn can be used in Millenaire Wah & Masa Recipes. (you have to pickup millenaire maize first to unlock recipe) (v0.6)
	 * First Advancement recipes (v0.6)
	 * Reactioncraft Corn can be used in Forestry Squeezer for SEED OIL (v0.6)
	 * Reactioncraft Ancient Fruit can be used in Forestry Squeezer for SEED OIL (v0.6)
	 * Reactioncraft Cacti can be used in Forestry Squeezer for Water (v0.6)
	 * Changed Recipe for making popcorn Kernels   Corn Kernel now needs to be added to a furnace to dry (v0.6)
	 * Re-Added Config File although currently nothing to configure..   (v0.6.1)
	 * Fixed Skinny Column Textures & Big Column Textures (v0.6.1.1) 
	 * Added Dynamic Lighting Support with Optifine (v6.2)
	 * added Ic2 API for future Support (v6.2)
	 * Re-added IC2 machine support (v6.2.1)
	 * Darkstone correctly drops dark cobblestone (v6.2.2)
	 * Darkstone & Dark cobblestone now Macerated into Dark Sand (v6.2.2)
	 * Fixed Chisel Recipes for Items & Columns (was spawning 2 instead of damaging the chisel, working now, v6.2.2)
	 * Mining Helmet ON/OFF Items (v6.2.2)
	 * Mining Helmet Functionality (v6.2.3)
	 * Fix Chisel Recipes for Items & Columns (Spawns 2 instead of damaging the chisel, caused by .json formatting bug) (v6.2.4)
	 * Support for Millenaire Trading (v6.3.1)
	 * Automatically Create Files for Millenaire Trading (v6.4)
	 * Add Corn Plant Plot to Vanilla Villages in Farm House
	 * Added Reactioncraft Villager House (v6.4.6)
	 * Entity Animals now Self Populate.. (v6.4.8)  (seems to be hit or miss depending on there distance from each other, might tweak in a later version)
	 * All Vanilla Iron Items Except Horse Armor drop iron shavings when crafted IF you have a hammer in your inventory (v6.4.9)
	 * Paint Brush, Paint Colors, Bowl of Water (v7.0.0)
	 * 
	 */
	
	/* Fixed
	 * 
	 * Ancient Plant: Grows, looks correct, Drops Fixed
	 * Bookshelf Fixed Transformations
	 * Bookshelf Door Drops Fixed
	 * Fixed Cherry Tree Sapling Drop
	 * Fixed Corn growing, and Growing to Final Phase, now grows 4 tall
	 * Fixed Brick Oven Recipes
	 * Fixed  Full Column Textures & Recipes
	 * Renamed Popcorn Seeds to Popcorn Kernel 
	 * Ingot Bucket & Molten Liquid Buckets now Correctly Stack to 1 (v0.6)
	 * Fixed Chainloop Recipe (v0.6)
	 * Fixed Hammer Recipe (v0.6)
	 * Caught Item now names mob correctly (v6.2.4)
	 * Fix Net crashing upon being crafted, uses not working (v6.2.4) 
	 * Ingot & Coin Moulds spawn with 0 uses (v6.2.4)
	 * Rename  Desert Block Enums (v6.4.1)
	 * Desert Coal now drops coal instead of block (v6.4.1)
	 * Desert Gems now drop Uncut Gems instead of block (v6.4.1)
	 * scrollshelf now correctly drops 1 scroll per scroll actually inside (v6.4.1)
	 * bookshelf   now correctly drops 1 book per book actually inside (v6.4.1)
	 * Scrollshelf & Bookshelf Webbed & Webbed Full Versions added to creative tabs (v6.4.1)
	 * Add Cherry Door iCONs (v6.4.1)
	 * Desert Coal Macerated into 2 Coal (v6.4.1)
	 * Desert Gems & Dragonstone Gems now crafted correctly with chisel (v6.4.1)
	 * Server Crash Fixed Stopped using invalid directory method(v6.4.3)
	 * confirmed fixed server crash (v6.4.4)
	 * fixed mobs dropping 2 bones instead of one (v6.4.5) (finish 6/13/18)
	 * fixed crawling skeletons not burning in sunlight (v6.4.6)
	 * crawling skeletons & zombies dropping mob skulls (v6.4.6)
	 * fixed diamond colum recipe (typo v6.4.7)
	 * Fix Broken Bone drops Again, now actually drops and no longer drops 2 (v6.4.8)
	 * Added Hardness' to Blocks, and tweaked some (v6.4.9)
	 * Freezer, Brick Oven Fixed (v7.0.0)
	 * Crawling Skeletons now burn in sunlight (v7.0.0)
	 * Fixed Bone Drops No longer drops from spiders(v7.0.0) 
	 * Fix Meat cleaver, Knife, Bat they are now weapons (v7.0.0)
	 * Recipe for PowerAdapters Mod Block With Bloodstone *Until custom converter block is finished* (v7.0.0)
	 * Fixed Purple Particles on Column blocks (v7.0.0)
	 * Villager Professions now have names (v7.0.0)
	 * 
	 */
	 
	/* Changed
	 * 
	 * Nerfed Crawling Creature Spawn Rate
	 * Crawling Skeletons dont like zombies
	 * Chain Loops now give 2 instead of one (v6.4.9)
	 * Rewrite of alot of code & reorganization of files (v6.4.9)
	 * Alexiy Fixed Machines and Rewrote (v6.4.9)
	 * Stop Crawling Skeletons and Zombies from Spawning in Nether & End (v6.4.9)
	 * Moved Popcorn Kernel over to Food tab (v6.4.9)
	 * Moved Bag of popcorn over to Food tab (v6.4.9)
	 * Fixed Corn Plant not swapping over correctly if bone meal was used to grow (v6.4.9)
	 * Gold & Gem Hireoglyphics now return material used to make them if they are not regular
	 * set forge tool classes for blocks (v6.5.0)
	 * Double Tall Grass now cooks in furnace to straw (v7.0.0)
	 */
}