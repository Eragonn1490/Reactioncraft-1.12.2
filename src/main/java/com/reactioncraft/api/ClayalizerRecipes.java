package com.reactioncraft.api;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Maps;
import com.reactioncraft.common.instances.BlockIndex;
import com.reactioncraft.common.instances.ItemIndex;
import com.reactioncraft.common.utils.Tools;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ClayalizerRecipes
{
    private static final ClayalizerRecipes SMELTING_BASE = new ClayalizerRecipes();
    private final Map<ItemStack, ItemStack> smeltingList = Maps.<ItemStack, ItemStack>newHashMap();
    private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();

    /**
     * Returns an instance of FurnaceRecipes.
     */
    public static ClayalizerRecipes instance()
    {
        return SMELTING_BASE;
    }

    private ClayalizerRecipes()
    {
        addSmeltingRecipeForBlock(BlockIndex.dark_sand,new ItemStack(ItemIndex.sandStonePaste, 2, 0),0.3f);
    }

    /**
     * Adds a smelting recipe, where the input item is an instance of Block.
     */
    public void addSmeltingRecipeForBlock(Block input, ItemStack stack, float experience)
    {
        this.addSmelting(Item.getItemFromBlock(input), stack, experience);
    }

    /**
     * Adds a smelting recipe using an Item as the input item.
     */
    public void addSmelting(Item input, ItemStack stack, float experience)
    {
        this.addSmeltingRecipe(new ItemStack(input, 1, 32767), stack, experience);
    }

    /**
     * Adds a smelting recipe using an ItemStack as the input for the recipe.
     */
    public void addSmeltingRecipe(ItemStack input, ItemStack stack, float experience)
    {
        if (!getSmeltingResult(input).isEmpty()) { net.minecraftforge.fml.common.FMLLog.info("Ignored smelting recipe with conflicting input: " + input + " = " + stack); return; }
        this.smeltingList.put(input, stack);
        this.experienceList.put(stack, Float.valueOf(experience));
    }

    /**
     * Returns the smelting result of an item.
     */
    public ItemStack getSmeltingResult(ItemStack stack)
    {
        for (Entry<ItemStack, ItemStack> entry : this.smeltingList.entrySet())
        {
            if (Tools.areItemTypesEqual(stack, entry.getKey()))
            {
                return entry.getValue().copy();
            }
        }

        return ItemStack.EMPTY;
    }
}