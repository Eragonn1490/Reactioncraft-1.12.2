package com.reactioncraft.blocks.enums;

import net.minecraft.util.IStringSerializable;

public enum EnumLevers implements IStringSerializable
{
	FULL           	   (0, 0,   "0",       "0"),
	SCROLLCASE_FULL    (1, 1,   "1",       "1");
	/*
	WEBBED_FULL     	   (2, 9,    "2",       "2"),
	SCROLLCASE_EMPTY	   (3, 8,    "3",       "3"),
	ONE_THIRD        	   (4, 7,    "4", 	     "4"),
	TWO_THIRDS      	   (5, 6,    "5",       "5"),
	FULL            	   (6, 5,    "6",       "6"),
	SCROLLCASE_ONE_THIRD   (7, 4,    "7",       "7"),
	SCROLLCASE_TWO_THIRDS  (8, 3,    "8",       "8"),
	SCROLLCASE_FULL        (9, 2, 	 "9",       "9"),
	SCROLLCASE_WEBBED_EMPTY(10, 1,   "10",      "10"),
	SCROLLCASE_WEBBED_FULL (11, 0,   "11",      "11"),
	;
	**/

	private static final EnumLevers[] META_LOOKUP = new EnumLevers[values().length];
	private static final EnumLevers[] DYE_DMG_LOOKUP = new EnumLevers[values().length];
	private final int meta;
	private final int dyeDamage;
	private final String name;
	private final String unlocalizedName;

	private EnumLevers(int meta, int dyeDamage, String name, String unlocalizedName)
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

	public static EnumLevers byDyeDamage(int damage)
	{
		if (damage < 0 || damage >= DYE_DMG_LOOKUP.length)
		{
			damage = 0;
		}

		return DYE_DMG_LOOKUP[damage];
	}

	public static EnumLevers byMetadata(int meta)
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
		for (EnumLevers type : values())
		{
			META_LOOKUP[type.getMetadata()] = type;
			DYE_DMG_LOOKUP[type.getDyeDamage()] = type;
		}
	}
}