package com.reactioncraft;

public class Todolist 
{
	/** This Class is just here as a list of things to fix **/
	/* Wishlist After v7.0.0
	 * 
	 * Advancements
	 * Drawbridge? Portcullis but vertical 
	 * Add can Silk touch to desert coal core and gems
	 * Recipe for Kingly Armour
	 * Finish Vanilla Villager Trades
	 * Treasure Piles? (Maybe)
	 * Make the Scroll Act like a book.
	 * Hireoglyph Recipes Changed to using machine?
	 * Villager drop with meat cleaver
	 * Fix Boreheads for Railcraft (When it updates to 1.12.2)
	 * Make Frame Useful for forestry again?
	 * Add bee species? that uses honeycomb and pollen comb?
	 * Desert Portcullis (Possibly Change Gate to use the Rail Model)
	 * Fix Jellyfish Movement / Underwater
	 * Way for mods to add bone drops
	 *
	 */
	
	/* Important Things to fix for 7.0.0
	 * Fix Villager profession reset, NBT not stored? (Removes Profession if caught with net and respawned)
	 * Brick Oven, Freezer don't return buckets
	 * Dis-allow capturing millenaire villagers (city forcibly respawns them anyway)
	 * Chest
	 * Longer Piston?
	 */
	
	
	/*
	 * Coming in MC 1.13
	 *  Columns and other Blocks that are not full will render as if inside of the fluid surrounding them!
	 *  Sea-creepers!!
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
	 * Lever Bookcase & Scrollshelf (With Possibility to add more) (v7.0.0)
	 * Tripwire Rail - if a cart is over this rail stops outputting redstone current until it passes  (to allow "tripwire" rail) (v7.0.0)
	 * Bag of Coins now has a custom block model (v7.0.0)
	 * Wireless Redstone Transmitter (v7.0.0)
	 * Wireless Redstone Reciever Block (v7.0.0)
	 * Wireless Redstone Zeroizer Item (v7.0.0)
	 * Wireless Redstone Reconfigurer Block (v7.0.0)
	 * Added Statues (6/26/18) (v7.0.0)
	 * Fixed Cup Statue Bounds (6/27/18) (v7.0.0)
	 * Fixed Bag of Coins Bounds (6/27/18) (v7.0.0)
	 * When you right click water with an empty Bowl, you get a bowl of water (6/28/18) (v7.0.0)
	 * Added Config Option for consuming water when you right click with an empty bowl (6/28/18) (v7.0.0)
	 * Re-Added Jellyfish (v7.0.0)
	 * Added Statues (v7.0.0)
	 * Added Compressed Gold Statue to Banker House (v7.0.0)
	 * Redesign corn item textures! (v7.0.0)
	 * Make sure Banker Villager can Spawn in Bank House (v7.0.0)
	 * EU - TO RF- To - MJ converter block. (v7.0.0)
	 * Ship in a bottle & Map in a bottle are now throwable Entities that "Break" (v7.0.0)
	 * End Ores Generate (v7.0.0)
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
	 * Fixed Broken Bone drops Again, now actually drops and no longer drops 2 (v6.4.8)
	 * Added Hardness' to Blocks, and tweaked some (v6.4.9)
	 * Freezer, Brick Oven Fixed (v7.0.0)
	 * Crawling Skeletons now burn in sunlight (v7.0.0)
	 * Fixed Bone Drops No longer drops from spiders(v7.0.0) 
	 * Fix Meat cleaver, Knife, Bat they are now weapons (v7.0.0)
	 * Recipe for PowerAdapters Mod Block With Bloodstone *Until custom converter block is finished* (v7.0.0)
	 * Fixed Purple Particles on Column blocks (v7.0.0)
	 * Villager Professions now have names (v7.0.0)
	 * Fixed Smallest Column Model & Block Bounds (v7.0.0)
	 * remove unused columns from creative tabs for (v7.0.0)
	 * EntityAiEatGrassMate now makes animals eat grass and then go into love mode (v7.0.0) Removed for now, can make things laggy
	 * remove unused columns from creative tabs for (v7.0.0)
	 * Fixed all Errored Block Model Except things that are being worked on (v7.0.0)
	 * Edit Pixels on Ingot Mould (v7.0.0)
	 * Fixed Creative Net having unlimited Uses (v7.0.0)
	 * Fixed Survival Net having correct amount of uses (old json file set them to 32765 uses on accident) (v7.0.0)
	 * Fixed Rotation of Meat cleaver (v7.0.0)
	 * Fixed Rotation of paint brush (v7.0.0)
	 * Re-Added Food Drop to Millenaire Villagers (If you kill with a meat cleaver, you get a human leg.. (v6.3.1, Broken v6.5.0) (Fixed For-real this time v7.0.0)
	 * Re-Added Bones Drop to Millenaire Villagers (v6.3.1 , Broken v6.5.0)   (Fixed For-real this time v7.0.0)
	 * Fixed Claylizer TE (6/28/18) (v7.0.0)
	 * Fixed raw human drop with meat cleaver (v7.0.0)
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
	 * Stone Slab Column now correctly mirrors stone double slabs (v7.0.0)
	 * Actually Stop Crawling Skeletons and Zombies from Spawning in Nether & End (v7.0.0)
	 * Re-Organized Git Folders (7/7/2018)
	 * 
	 */
}