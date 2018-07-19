package com.reactioncraft.common.world.structures;

import java.util.Map;
import java.util.Random;
import com.reactioncraft.common.utils.*;
import com.reactioncraft.common.world.*;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.*;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import java.util.Map.Entry;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.minecraft.world.storage.loot.LootTableList;

public class WorldGenStructureTemplates extends WorldGenerator 
{
	Random r2 =new Random();
	int r;


	@Override
	public boolean generate(World world, Random rand, BlockPos position) 
	{
		WorldServer worldserver         = (WorldServer) world;
		MinecraftServer minecraftserver = world.getMinecraftServer();
		TemplateManager templatemanager = worldserver.getStructureTemplateManager();
		Template template;
		
		int j = r2.nextInt(8);
		 
		switch (j) 
		{
			case 1:  template = templatemanager.getTemplate(minecraftserver, new ResourceLocation(constants.MODID + ":obelisk_brown"));
				//Logger.info("3");
				break;
				
			case 2:  template = templatemanager.getTemplate(minecraftserver, new ResourceLocation(constants.MODID + ":obelisk_darkblue"));
				//Logger.info("4");
				break;
				
			case 3:  template = templatemanager.getTemplate(minecraftserver, new ResourceLocation(constants.MODID + ":obelisk_lightblue"));
				//Logger.info("5");
				break;
			
			case 4:  template = templatemanager.getTemplate(minecraftserver, new ResourceLocation(constants.MODID + ":obelisk_gold"));
				//Logger.info("6");
				break;
				
			case 5:  template = templatemanager.getTemplate(minecraftserver, new ResourceLocation(constants.MODID + ":obelisk_destroyed"));
				//Logger.info("7");
				break;
				
			case 6:  template = templatemanager.getTemplate(minecraftserver, new ResourceLocation(constants.MODID + ":temple1"));
				//Logger.info("8");
				break;
				
			case 7:  template = templatemanager.getTemplate(minecraftserver, new ResourceLocation(constants.MODID + ":tower_desert"));
				//Logger.info("9");
				break;
			
			default: template = templatemanager.getTemplate(minecraftserver, new ResourceLocation(constants.MODID + ":obelisk_brown"));
				//Logger.info("Default");
				break;
		}

		if(template == null)
		{
			System.out.println("NO STRUCTURE FOUND");
			return false;
		}
		

		if(WorldGenStructures.canSpawnHere(template, worldserver, position))
		{
			IBlockState iblockstate = world.getBlockState(position);
			world.notifyBlockUpdate(position, iblockstate, iblockstate, 3);

			PlacementSettings placementsettings = (new PlacementSettings()).setMirror(Mirror.NONE)
					.setRotation(Rotation.NONE).setIgnoreEntities(false).setChunk((ChunkPos) null)
					.setReplacedBlock((Block) null).setIgnoreStructureBlock(false);

			template.getDataBlocks(position, placementsettings);
			template.addBlocksToWorld(world, position.add(0, 1, 0), placementsettings);


			Map<BlockPos, String> map = template.getDataBlocks(position, placementsettings);

			for (Entry<BlockPos, String> entry : map.entrySet())
			{
				if ("chest".equals(entry.getValue()))
				{
					BlockPos blockpos2 = entry.getKey();
					world.setBlockState(blockpos2.up(), Blocks.AIR.getDefaultState(), 3);
					TileEntity tileentity = world.getTileEntity(blockpos2);

					if (tileentity instanceof TileEntityChest)
					{
						((TileEntityChest)tileentity).setLootTable(LootTableList.CHESTS_DESERT_PYRAMID, rand.nextLong());
					}
				}
			}
			return true;
		}
		return false;
	}

	public void addLoot(World world) {}
}