package com.reactioncraft.common.world.village;


import javax.annotation.Nullable;

import com.reactioncraft.common.utils.constants;

import java.util.List;
import java.util.Random;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraftforge.fml.common.registry.VillagerRegistry;


public class ReactioncraftField implements VillagerRegistry.IVillageCreationHandler 
{	
	@Override
	public StructureVillagePieces.PieceWeight getVillagePieceWeight(Random random, int size) {
		return new StructureVillagePieces.PieceWeight(VillageCornField.class, 100, MathHelper.getInt(random, 1 + size, 4 + size));
	}

	
	@Override
	public Class<?> getComponentClass() {
		return VillageCornField.class;
	}


	@Override
	@Nullable
	public StructureVillagePieces.Village buildComponent(StructureVillagePieces.PieceWeight villagePiece, StructureVillagePieces.Start startPiece, List<StructureComponent> pieces, Random random, int p1, int p2, int p3, EnumFacing facing, int p5) {
		return VillageCornField.buildComponent(startPiece, pieces, random, p1, p2, p3, facing, p5);
	}

}