/**

 * This class was created by <williewillus>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 *
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * Modified for use in Reactioncraft by @Eragonn1490 (7/8/2018)
 */

package com.reactioncraft.common.recipes;



import com.google.gson.JsonObject;
import com.reactioncraft.common.registration.instances.PropertyIndex;

import net.minecraft.util.JsonUtils;
import net.minecraftforge.common.crafting.IConditionFactory;
import net.minecraftforge.common.crafting.JsonContext;
import java.util.function.BooleanSupplier;

public class JsonRecipeRemover implements IConditionFactory 
{
	@Override
	public BooleanSupplier parse(JsonContext context, JsonObject json) 
	{
		boolean value = JsonUtils.getBoolean(json , "value", true);
		return () -> PropertyIndex.removeTripwireRail == value;
	}
}