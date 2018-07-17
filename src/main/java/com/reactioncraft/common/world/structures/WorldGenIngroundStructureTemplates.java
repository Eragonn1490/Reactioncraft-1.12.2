package com.reactioncraft.common.world.structures;

import java.util.Map;
import java.util.Random;

import com.reactioncraft.common.utils.Logger;
import com.reactioncraft.common.utils.constants;
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
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
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

/** This class is currently unused **/
public class WorldGenIngroundStructureTemplates extends WorldGenerator 
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
		
		int j = r2.nextInt(10);
		 
		switch (j) 
		{
			case 1:  template = templatemanager.getTemplate(minecraftserver, new ResourceLocation(constants.MODID + ":obelisk_chest"));
				Logger.info("1");
				break;
			
			default: template = templatemanager.getTemplate(minecraftserver, new ResourceLocation(constants.MODID + ":obelisk_chest"));
				Logger.info("Default");
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
			template.addBlocksToWorld(world, position.add(0, -2, 0), placementsettings);


			Map<BlockPos, String> map = template.getDataBlocks(position, placementsettings);

			for (Entry<BlockPos, String> entry : map.entrySet())
			{
				if ("chest".equals(entry.getValue()))
				{
					BlockPos blockpos2 = entry.getKey();
					world.setBlockState(blockpos2.down(2), Blocks.AIR.getDefaultState(), 3);
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