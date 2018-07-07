package com.reactioncraft.common.world.village;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraftforge.fml.common.registry.VillagerRegistry.IVillageCreationHandler;

public class ReactioncraftFarmHandler implements IVillageCreationHandler 
{
	@Override
	public StructureVillagePieces.PieceWeight getVillagePieceWeight(Random random, int size) 
	{
		return new StructureVillagePieces.PieceWeight(VillageReactioncraftFarm.class, 20, MathHelper.getInt(random, size, 1 + size));
	}

	@Override
	public Class<?> getComponentClass() 
	{
		return VillageReactioncraftFarm.class;
	}

	@Override
	@Nullable
	public StructureVillagePieces.Village buildComponent(StructureVillagePieces.PieceWeight villagePiece, StructureVillagePieces.Start startPiece, List<StructureComponent> pieces, Random random, int p1, int p2, int p3, EnumFacing facing, int p5) {
		return VillageReactioncraftFarm.buildComponent(startPiece, pieces, random, p1, p2, p3, facing, p5);
	}
}