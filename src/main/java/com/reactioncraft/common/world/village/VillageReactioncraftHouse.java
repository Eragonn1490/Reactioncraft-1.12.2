/*******************************************************************************
	Based off of SirSengir's Forestry House
 ******************************************************************************/
package com.reactioncraft.common.world.village;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.reactioncraft.common.blocks.machines.BlockClayalizer;
import com.reactioncraft.common.instances.BlockIndex;
import com.reactioncraft.common.instances.registration.Villagers;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import net.minecraftforge.registries.ForgeRegistry;

public class VillageReactioncraftHouse extends StructureVillagePieces.House1 
{

	private static final Random random = new Random();

	private int averageGroundLevel = -1;

	public VillageReactioncraftHouse() {  }

	public VillageReactioncraftHouse(StructureVillagePieces.Start startPiece, int componentType, Random random, StructureBoundingBox boundingBox, EnumFacing facing) {
		super(startPiece, componentType, random, boundingBox, facing);
	}

	@Nullable
	public static VillageReactioncraftHouse buildComponent(StructureVillagePieces.Start startPiece, List<StructureComponent> pieces, Random random, int structureMinX, int structureMinY, int structureMinZ, EnumFacing facing, int componentType) {
		StructureBoundingBox bbox = StructureBoundingBox.getComponentToAddBoundingBox(structureMinX, structureMinY, structureMinZ, -4, 0, 0, 12, 9, 12, facing);
		if (!canVillageGoDeeper(bbox) || StructureComponent.findIntersecting(pieces, bbox) != null) {
			return null;
		}

		return new VillageReactioncraftHouse(startPiece, componentType, random, bbox, facing);
	}

	@Override
	public boolean addComponentParts(World world, Random random, StructureBoundingBox structBoundingBox) {

		if (averageGroundLevel < 0) {
			averageGroundLevel = getAverageGroundLevel(world, structBoundingBox);
			if (averageGroundLevel < 0) {
				return true;
			}

			boundingBox.offset(0, averageGroundLevel - boundingBox.maxY + 9 - 1, 0);
		}

		fillWithBlocks(world, structBoundingBox, 1, 1, 1, 7, 4, 4, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
		fillWithBlocks(world, structBoundingBox, 2, 1, 6, 8, 4, 10, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);

		// Garden
		buildGarden(world, structBoundingBox);

		// Garden fence
		IBlockState gardenFence = Blocks.OAK_FENCE.getDefaultState();
		fillWithBlocks(world, structBoundingBox, 1, 1, 6, 1, 1, 10, gardenFence, gardenFence, false);
		fillWithBlocks(world, structBoundingBox, 8, 1, 6, 8, 1, 10, gardenFence, gardenFence, false);
		fillWithBlocks(world, structBoundingBox, 2, 1, 10, 7, 1, 10, gardenFence, gardenFence, false);

		IBlockState fenceGate = Blocks.OAK_FENCE_GATE.getDefaultState();
		setBlockState(world, fenceGate.withProperty(BlockHorizontal.FACING, EnumFacing.EAST), 8, 1, 8, structBoundingBox);
		setBlockState(world, fenceGate.withProperty(BlockHorizontal.FACING, EnumFacing.EAST), 1, 1, 8, structBoundingBox);
		setBlockState(world, fenceGate.withProperty(BlockHorizontal.FACING, EnumFacing.NORTH), 4, 1, 10, structBoundingBox);

		// Flowers
		//plantFlowerGarden(world, structBoundingBox, 2, 1, 5, 7, 1, 9);

		// Apiaries
		buildMachines(world, structBoundingBox);

		// Floor
		IBlockState slabFloor = Blocks.STONE_SLAB.getDefaultState();
		IBlockState planksFloor = BlockIndex.cherry_planks.getDefaultState();
		slabFloor.withProperty(BlockSlab.HALF, BlockSlab.EnumBlockHalf.BOTTOM);
		fillWithBlocks(world, structBoundingBox, 2, 0, 1, 6, 0, 4, slabFloor, slabFloor, false);
		fillWithBlocks(world, structBoundingBox, 1, 0, 1, 1, 0, 4, planksFloor, planksFloor, false);
		fillWithBlocks(world, structBoundingBox, 7, 0, 1, 7, 0, 4, planksFloor, planksFloor, false);

		IBlockState cobblestoneState = Blocks.COBBLESTONE.getDefaultState();
		fillWithBlocks(world, structBoundingBox, 0, 0, 0, 0, 2, 5, cobblestoneState, cobblestoneState, false);
		fillWithBlocks(world, structBoundingBox, 8, 0, 0, 8, 2, 5, cobblestoneState, cobblestoneState, false);
		fillWithBlocks(world, structBoundingBox, 1, 0, 0, 7, 1, 0, cobblestoneState, cobblestoneState, false);
		fillWithBlocks(world, structBoundingBox, 1, 0, 5, 7, 1, 5, cobblestoneState, cobblestoneState, false);

		fillWithBlocks(world, structBoundingBox, 0, 3, 0, 0, 3, 5, planksFloor, planksFloor, false);
		fillWithBlocks(world, structBoundingBox, 8, 3, 0, 8, 3, 5, planksFloor, planksFloor, false);
		fillWithBlocks(world, structBoundingBox, 1, 2, 0, 7, 3, 0, planksFloor, planksFloor, false);
		fillWithBlocks(world, structBoundingBox, 1, 2, 5, 7, 3, 5, planksFloor, planksFloor, false);

		// Ceiling
		IBlockState slabCeiling = Blocks.STONE_SLAB.getDefaultState();
		slabCeiling.withProperty(BlockSlab.HALF, BlockSlab.EnumBlockHalf.TOP);
		fillWithBlocks(world, structBoundingBox, 1, 4, 1, 7, 4, 4, slabCeiling, slabCeiling, false);

		IBlockState logBracing = BlockIndex.cherrywood.getDefaultState();
		logBracing.withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Y);//Test
		fillWithBlocks(world, structBoundingBox, 0, 4, 1, 8, 4, 1, logBracing, logBracing, false);
		fillWithBlocks(world, structBoundingBox, 0, 4, 4, 8, 4, 4, logBracing, logBracing, false);

		fillWithBlocks(world, structBoundingBox, 0, 5, 2, 8, 5, 3, planksFloor, planksFloor, false);

		setBlockState(world, planksFloor, 0, 4, 2, structBoundingBox);
		setBlockState(world, planksFloor, 0, 4, 3, structBoundingBox);
		setBlockState(world, planksFloor, 8, 4, 2, structBoundingBox);
		setBlockState(world, planksFloor, 8, 4, 3, structBoundingBox);

		buildRoof(world, structBoundingBox);

		// sides of windows
		setBlockState(world, planksFloor, 0, 2, 1, structBoundingBox);
		setBlockState(world, planksFloor, 0, 2, 4, structBoundingBox);
		setBlockState(world, planksFloor, 8, 2, 1, structBoundingBox);
		setBlockState(world, planksFloor, 8, 2, 4, structBoundingBox);

		IBlockState glassPaneState = Blocks.GLASS_PANE.getDefaultState();

		// Windows on east side
		setBlockState(world, glassPaneState, 0, 2, 2, structBoundingBox);
		setBlockState(world, glassPaneState, 0, 2, 3, structBoundingBox);

		// stairs over window
		IBlockState eastStairs = Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST);
		setBlockState(world, eastStairs, -1, 3, 2, structBoundingBox);
		setBlockState(world, eastStairs, -1, 3, 3, structBoundingBox);

		// Windows on west side
		setBlockState(world, glassPaneState, 8, 2, 2, structBoundingBox);
		setBlockState(world, glassPaneState, 8, 2, 3, structBoundingBox);

		// stairs over window
		IBlockState westStairs = Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST);
		setBlockState(world, westStairs, 9, 3, 2, structBoundingBox);
		setBlockState(world, westStairs, 9, 3, 3, structBoundingBox);

		// Windows garden side
		setBlockState(world, glassPaneState, 2, 2, 5, structBoundingBox);
		setBlockState(world, glassPaneState, 3, 2, 5, structBoundingBox);
		setBlockState(world, glassPaneState, 4, 2, 5, structBoundingBox);

		// Windows front side
		setBlockState(world, glassPaneState, 5, 2, 0, structBoundingBox);
		setBlockState(world, glassPaneState, 6, 2, 5, structBoundingBox);

		// Block Clayalizer
		if (random.nextInt(2) == 0) {
			IBlockState Clayalizer = BlockIndex.claylizer.getDefaultState().withProperty(BlockClayalizer.FACING, EnumFacing.EAST);
			setBlockState(world, Clayalizer, 1, 1, 3, structBoundingBox);
		}

		IBlockState airState = Blocks.AIR.getDefaultState();

		IBlockState cherrydoor = BlockIndex.cherrydoor.getDefaultState().withProperty(BlockClayalizer.FACING, EnumFacing.EAST);
		this.setBlockState(world, cherrydoor.withProperty(BlockDoor.FACING, EnumFacing.NORTH), 2, 1, 0, structBoundingBox);
		this.setBlockState(world, cherrydoor.withProperty(BlockDoor.FACING, EnumFacing.NORTH).withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.UPPER), 2, 2, 0, structBoundingBox);

		this.setBlockState(world, Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.NORTH), 2, 3, 1, structBoundingBox);

		IBlockState northStairs = Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST);
		if (isAirBlockAtCurrentPosition(world, new BlockPos(2, 0, -1), structBoundingBox) && !isAirBlockAtCurrentPosition(world, new BlockPos(2, -1, -1), structBoundingBox)) {
			setBlockState(world, northStairs.withProperty(BlockStairs.FACING, EnumFacing.NORTH), 2, 0, -1, structBoundingBox);
		}

		setBlockState(world, airState, 6, 1, 5, structBoundingBox);
		setBlockState(world, airState, 6, 2, 5, structBoundingBox);

		this.setBlockState(world, Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.SOUTH), 6, 3, 4, structBoundingBox);

		this.setBlockState(world, cherrydoor.withProperty(BlockDoor.FACING, EnumFacing.SOUTH), 6, 1, 5, structBoundingBox);
		this.setBlockState(world, cherrydoor.withProperty(BlockDoor.FACING, EnumFacing.SOUTH).withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.UPPER), 6, 2, 5, structBoundingBox);

		for (int i = 0; i < 5; ++i) {
			for (int j = 0; j < 9; ++j) {
				clearCurrentPositionBlocksUpwards(world, j, 7, i, structBoundingBox);
				replaceAirAndLiquidDownwards(world, Blocks.COBBLESTONE.getDefaultState(), j, -1, i, structBoundingBox);
			}
		}

		//generateChest(world, structBoundingBox, random, 7, 1, 3, Constants.VILLAGE_NATURALIST_LOOT_KEY);

		// Inside Corners
		fillWithBlocks(world, structBoundingBox, 1, 1, 1, 1, 3, 1, gardenFence, gardenFence, false);
		fillWithBlocks(world, structBoundingBox, 1, 1, 4, 1, 3, 4, gardenFence, gardenFence, false);
		fillWithBlocks(world, structBoundingBox, 7, 1, 1, 7, 3, 1, gardenFence, gardenFence, false);
		fillWithBlocks(world, structBoundingBox, 7, 1, 4, 7, 3, 4, gardenFence, gardenFence, false);

		spawnVillagers(world, boundingBox, 2, 1, 2, 2);

		return true;
	}

	private void buildRoof(World world, StructureBoundingBox structBoundingBox) {
		for (int z = -1; z <= 2; ++z) {
			for (int x = 0; x <= 8; ++x) {
				IBlockState northStairs = Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH);
				northStairs.withProperty(BlockStairs.FACING, EnumFacing.NORTH);
				setBlockState(world, northStairs, x, 4 + z, z, structBoundingBox);

				IBlockState southStairs = Blocks.STONE_BRICK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH);
				southStairs.withProperty(BlockStairs.FACING, EnumFacing.SOUTH);
				setBlockState(world, southStairs, x, 4 + z, 5 - z, structBoundingBox);
			}
		}
	}

	private void buildGarden(World world, StructureBoundingBox box) {

		Block ground = Blocks.DIRT;
		if (structureType == 1) { // desert
			ground = Blocks.SAND;
		}

		for (int i = 1; i <= 8; i++) {
			for (int j = 6; j <= 10; j++) {
				replaceAirAndLiquidDownwards(world, ground.getDefaultState(), i, 0, j, box);
			}
		}
	}

	private void buildMachines(World world, StructureBoundingBox box) {
		populateMachines(world, box, new BlockPos(3, 1, 8));
		populateMachines(world, box, new BlockPos(6, 1, 8));
	}

	private void populateMachines(World world, StructureBoundingBox box, BlockPos pos) {
		int xCoord = getXWithOffset(pos.getX(), pos.getZ());
		int yCoord = getYWithOffset(pos.getY());
		int zCoord = getZWithOffset(pos.getX(), pos.getZ());
		BlockPos posNew = new BlockPos(xCoord, yCoord, zCoord);

		if (!box.isVecInside(posNew)) {
			return;
		}

		IBlockState blockState = world.getBlockState(posNew);
		if (BlockIndex.claylizer == blockState.getBlock() || !world.isBlockLoaded(posNew.down())) {
			return;
		}
	}

	@Override
	protected int chooseProfession(int villagerCount, int currentVillagerProfession) 
	{
		ForgeRegistry<VillagerRegistry.VillagerProfession> registry = (ForgeRegistry<VillagerRegistry.VillagerProfession>) ForgeRegistries.VILLAGER_PROFESSIONS;
		return registry.getID(Villagers.mherr);
	}

	private boolean isAirBlockAtCurrentPosition(World world, BlockPos pos, StructureBoundingBox box) {
		IBlockState blockStateFromPos = getBlockStateFromPos(world, pos.getX(), pos.getY(), pos.getZ(), box);
		return blockStateFromPos.getBlock().isAir(blockStateFromPos, world, pos);
	}
}