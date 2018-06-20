package com.reactioncraft.registration;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.core.Logger;
import com.reactioncraft.registration.instances.ItemIndex;
import com.reactioncraft.world.village.*;
import com.reactioncraft.utils.constants;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MathHelper;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraft.world.gen.structure.StructureVillagePieces.PieceWeight;
import net.minecraft.world.gen.structure.StructureVillagePieces.Start;
import net.minecraft.world.gen.structure.StructureVillagePieces.Village;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;

/**
 * Created on 12/31/17.
 */
public class Villagers {

	@Nullable
	public static VillagerRegistry.VillagerProfession mherr;
	public static VillagerRegistry.VillagerProfession banker;

	public static void register(IForgeRegistry<VillagerRegistry.VillagerProfession> registry)
	{

		//TODO set trades
		/** VillagerRegistry.VillagerProfession **/ mherr = new VillagerRegistry.VillagerProfession(constants.MODID+":regular",constants.MODID+":textures/entity/rc_villager.png",constants.MODID+":textures/entity/zombie_villager/zombie_rc_villager.png");
		VillagerRegistry.VillagerCareer career      = new VillagerRegistry.VillagerCareer(mherr,"career1");
		career.addTrade(1, new EntityVillager.ITradeList() {
			@Override
			public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) {
				recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD,20+random.nextInt(4)),new ItemStack(ItemIndex.coinMould)));
				recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD,20+random.nextInt(4)),new ItemStack(ItemIndex.ingotmould)));
				recipeList.add(new MerchantRecipe(new ItemStack(ItemIndex.coins,5+random.nextInt(4), 12),new ItemStack(ItemIndex.coinMould)));
				recipeList.add(new MerchantRecipe(new ItemStack(ItemIndex.coins,5+random.nextInt(4), 12),new ItemStack(ItemIndex.ingotmould)));
			}
		});
		registry.register(mherr);

		//TODO set trades
		/** VillagerRegistry.VillagerProfession **/  banker =new VillagerRegistry.VillagerProfession(constants.MODID+":banker",constants.MODID+":textures/entity/banker.png","");
		VillagerRegistry.VillagerCareer villagerCareer=new VillagerRegistry.VillagerCareer(banker,"career1");
		villagerCareer.addTrade(1, new EntityVillager.ITradeList() {
			@Override
			public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) {
				recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD),new ItemStack(ItemIndex.coins)));
				recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD,2),new ItemStack(ItemIndex.coins,1,1)));
				//and so on
			}
		});

		//when villager's level goes up, new trades are unlocked
		villagerCareer.addTrade(2, new EntityVillager.ITradeList() {
			@Override
			public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) {
				recipeList.add(new MerchantRecipe(new ItemStack(Items.EMERALD,3),new ItemStack(ItemIndex.coins,1,2)));
			}
		});
		registry.register(banker);
		Logger.info("Villager Key is ",    registry.getKey(banker));
	}
	
	public static void registerVillageComponents() 
	{
		MapGenStructureIO.registerStructureComponent(VillageReactioncraftHouse.class, "Reactioncraft:House");
		MapGenStructureIO.registerStructureComponent(VillageReactioncraftBank.class, "Reactioncraft:Bank");
		MapGenStructureIO.registerStructureComponent(VillageReactioncraftFarm.class, "Reactioncraft:Farm");
	}
}