package com.reactioncraft.common.events;


import com.reactioncraft.common.instances.ItemIndex;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraft.world.storage.loot.functions.SetCount;
import net.minecraft.world.storage.loot.functions.SetDamage;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class LootTableHandler
{
    @SubscribeEvent
    public void addLoot(LootTableLoadEvent lootTableLoadEvent)
    {
		//Items with different
    	LootEntry coin_mould  = createDefaultDamageableLoot(ItemIndex.coinMould);
    	LootEntry ingot_mould = createDefaultDamageableLoot(ItemIndex.ingotmould);
    	
    	//Items
        LootEntry bronze_ingot  = createDefaultLootEntry(ItemIndex.ingotBronze);
        LootEntry corn          = createDefaultLootEntry(ItemIndex.rawcorn);
        LootEntry ruby          = createDefaultLootEntry(ItemIndex.ruby);
        LootEntry ancient_fruit = createDefaultLootEntry(ItemIndex.ancientFruit);
        LootEntry ancient_flower= createDefaultLootEntry(ItemIndex.ancientFlower);
        LootEntry corn_seeds    = createDefaultLootEntry(ItemIndex.cornSeed);
        LootEntry sugar_cane    = createDefaultLootEntry(ItemIndex.sugarcaneItemBase);
        
        //Fishing
        LootEntry bottle_ship   = createDefaultLootEntry(ItemIndex.shipinabottle);
        LootEntry bottle_map    = createDefaultLootEntry(ItemIndex.mapinabottle);
        LootEntry salmon=createDefaultLootEntry(ItemIndex.salmonRaw);
        LootEntry yellowtail=createDefaultLootEntry(ItemIndex.yellowTailRaw);
        //End of Entries
        
        ResourceLocation tableName=lootTableLoadEvent.getName();
        LootTable lootTable=lootTableLoadEvent.getTable();
    	if(tableName== LootTableList.CHESTS_SPAWN_BONUS_CHEST)
        {
            LootPool lootPool=createDefaultLootPool(new LootEntry[]{ingot_mould,coin_mould,ancient_fruit},"bonus chest");
            lootTable.addPool(lootPool);
        }
        else if(tableName==LootTableList.CHESTS_ABANDONED_MINESHAFT)
        {
            LootPool lootPool=createDefaultLootPool(new LootEntry[]{ingot_mould, bronze_ingot,coin_mould,ancient_flower,ancient_fruit,corn_seeds,sugar_cane},"mineshaft");
            lootTable.addPool(lootPool);
        }
        else if(tableName==LootTableList.CHESTS_DESERT_PYRAMID)
        {
            LootPool lootPool=createDefaultLootPool(new LootEntry[]{coin_mould,ruby},"pyramid");
            lootTable.addPool(lootPool);
        }
        else if(tableName==LootTableList.CHESTS_JUNGLE_TEMPLE)
        {
            LootPool lootPool=createDefaultLootPool(new LootEntry[]{coin_mould,ruby,ancient_flower,ancient_fruit,corn_seeds,sugar_cane},"jungle temple");
            lootTable.addPool(lootPool);
        }
        else if(tableName==LootTableList.CHESTS_VILLAGE_BLACKSMITH)
        {
            LootPool pool=createDefaultLootPool(new LootEntry[]{ingot_mould, bronze_ingot,coin_mould},"blacksmith");
            lootTable.addPool(pool);
        }
        else if(tableName==LootTableList.CHESTS_SIMPLE_DUNGEON)
        {
        	//not working
 //           LootPool pool=createDefaultLootPool(new LootEntry[]{ingot_mould_entry})
        }
        else if(tableName==LootTableList.GAMEPLAY_FISHING)
        {
            LootPool pool=createDefaultLootPool(new LootEntry[]{bottle_ship, bottle_map, salmon, yellowtail},"fishing");
            lootTable.addPool(pool);
        }
    }

    private LootPool createDefaultLootPool(LootEntry[] lootEntries,String id)
    {
        LootPool lootPool=new LootPool(lootEntries,new LootCondition[]{},new RandomValueRange(1),new RandomValueRange(0),id);
        return lootPool;
    }


    private LootEntryItem createDefaultLootEntry(Item item)
    {
        SetDamage lootFunction=new SetDamage(new LootCondition[]{},new RandomValueRange(0));
        SetCount setCount=new SetCount(new LootCondition[]{},new RandomValueRange(1));
        LootEntryItem lootEntryItem=new LootEntryItem(item,5,1,new LootFunction[]{lootFunction,setCount},new LootCondition[]{},item.getRegistryName().getResourcePath());
        return lootEntryItem;
    }
    private LootEntryItem createDefaultDamageableLoot(Item item)
    {
        SetDamage lootFunction      = new SetDamage(new LootCondition[]{},new RandomValueRange(100));
        SetCount  setCount          = new SetCount (new LootCondition[]{},new RandomValueRange(1));
        LootEntryItem lootEntryItem = new LootEntryItem(item,5,1,new LootFunction[]{lootFunction,setCount},new LootCondition[]{},item.getRegistryName().getResourcePath());
        return lootEntryItem;
    }
}