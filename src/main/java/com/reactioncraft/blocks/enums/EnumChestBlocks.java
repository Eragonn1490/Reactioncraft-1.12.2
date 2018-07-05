package com.reactioncraft.blocks.enums;

import net.minecraft.util.IStringSerializable;

public enum EnumChestBlocks implements IStringSerializable
{
	BOOKSHELF          (0, 0,   "0",       "0"),
	SCROLLSHELF    	   (1, 1,   "1",       "1")
	;

	private static final EnumChestBlocks[] META_LOOKUP = new EnumChestBlocks[values().length];
	private static final EnumChestBlocks[] DYE_DMG_LOOKUP = new EnumChestBlocks[values().length];
	private final int meta;
	private final int dyeDamage;
	private final String name;
	private final String unlocalizedName;

	private EnumChestBlocks(int meta, int dyeDamage, String name, String unlocalizedName)
	{
		this.meta = meta;
		this.dyeDamage = dyeDamage;
		this.name = name;
		this.unlocalizedName = unlocalizedName;
	}

	public int getMetadata()
	{
		return this.meta;
	}

	public int getDyeDamage()
	{
		return this.dyeDamage;
	}


	public String getUnlocalizedName()
	{
		return this.unlocalizedName;
	}

	public static EnumChestBlocks byDyeDamage(int damage)
	{
		if (damage < 0 || damage >= DYE_DMG_LOOKUP.length)
		{
			damage = 0;
		}

		return DYE_DMG_LOOKUP[damage];
	}

	public static EnumChestBlocks byMetadata(int meta)
	{
		if (meta < 0 || meta >= META_LOOKUP.length)
		{
			meta = 0;
		}

		return META_LOOKUP[meta];
	}

	public String toString()
	{
		return this.unlocalizedName;
	}

	public String getName()
	{
		return this.name;
	}


	static
	{
		for (EnumChestBlocks type : values())
		{
			META_LOOKUP[type.getMetadata()] = type;
			DYE_DMG_LOOKUP[type.getDyeDamage()] = type;
		}
	}
}