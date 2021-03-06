package com.reactioncraft.common.world;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class BiomeHandler
{
	@SubscribeEvent
	public void registerBiomes(RegistryEvent.Register<Biome> registryEvent)
	{
		registerBiomes(registryEvent.getRegistry());
	}

	private void registerBiomes(IForgeRegistry<Biome> biomes)
	{
		BiomeReactionDesert reactionDesert;
		reactionDesert = new BiomeReactionDesert(new Biome.BiomeProperties("Reaction Desert")
				.setBaseHeight(0.125F)
				.setHeightVariation(0.05F)
				.setTemperature(2.0F)
				.setRainfall(0.0F)
				.setRainDisabled());
		reactionDesert.setRegistryName("reaction_desert");
		biomes.register(reactionDesert);
		BiomeManager.addBiome(BiomeType.DESERT, new BiomeEntry(reactionDesert, 1000));//1000 is weight to spawn?
		BiomeManager.addSpawnBiome(reactionDesert);
		BiomeDictionary.addTypes(reactionDesert, Type.DRY);
	    BiomeManager.addVillageBiome(reactionDesert, true);
	}
}