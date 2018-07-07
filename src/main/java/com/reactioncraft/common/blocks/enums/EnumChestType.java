package com.reactioncraft.common.blocks.enums;


import com.reactioncraft.common.tiles.TileEntityBookcaseChest;
import com.reactioncraft.common.tiles.TileEntityHiddenBookChest;
import com.reactioncraft.common.tiles.TileEntityHiddenScrollChest;
import com.reactioncraft.common.utils.constants;

import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;



public enum EnumChestType implements IStringSerializable
{
	Book  (54, 9, true, "iron_chest.png", TileEntityHiddenBookChest.class  , 184, 202),

	Scroll(81, 9, true, "gold_chest.png", TileEntityHiddenScrollChest.class, 184, 256);


	public static final EnumChestType VALUES[] = values();

	public final String name;

	public final int size;

	public final int rowLength;

	public final boolean tieredChest;

	public final ResourceLocation modelTexture;

	private String breakTexture;

	public final Class<? extends TileEntityBookcaseChest> clazz;

	public final int xSize;

	public final int ySize;


	EnumChestType(int size, int rowLength, boolean tieredChest, String modelTexture, Class<? extends TileEntityBookcaseChest> clazz, int xSize, int ySize)
	{
		this.name = this.name().toLowerCase();

		this.size = size;

		this.rowLength = rowLength;

		this.tieredChest = tieredChest;

		this.modelTexture = new ResourceLocation(constants.MODID, "textures/model/chest/" + modelTexture);

		this.clazz = clazz;

		this.xSize = xSize;

		this.ySize = ySize;
	}


	public String getBreakTexture()
	{
		if (this.breakTexture == null)
		{
			switch (this)
			{
				case Book:
				{
					this.breakTexture = "minecraft:blocks/planks_oak";
					break;
				}
				
				case Scroll:
				{
					this.breakTexture = "minecraft:blocks/planks_oak";
					break;
				}

				default:
				{
					this.breakTexture = "ironchest:blocks/" + this.getName() + "break";
				}
			}
		}
		return this.breakTexture;
	}


	@Override
	public String getName()
	{
		return this.name;
	}


	public int getRowCount()
	{
		return this.size / this.rowLength;
	}

	public TileEntityBookcaseChest makeEntity()
	{

		switch (this)
		{
		case Book:
			return new TileEntityHiddenBookChest();

		case Scroll:
			return new TileEntityHiddenScrollChest();


		default:
			return null;
		}
	}
}