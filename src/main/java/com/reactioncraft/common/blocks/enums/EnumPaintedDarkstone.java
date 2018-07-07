package com.reactioncraft.common.blocks.enums;

import net.minecraft.util.IStringSerializable;

public enum EnumPaintedDarkstone implements IStringSerializable
{
    pattern0   (0, 0,   "0",      "0"),
    pattern1   (1, 1,   "1",      "1"),
    pattern2   (2, 2,   "2",      "2"),
    pattern3   (3, 3,   "3",      "3"),
    pattern4   (4, 4,   "4", 	  "4"),
    pattern5   (5, 5,   "5",      "5"),
    pattern6   (6, 6,   "6",      "6"),
    pattern7   (7, 7,   "7",      "7"),
    pattern8   (8, 8,   "8",      "8"),
	pattern9   (9, 9, 	"9",      "9"),
	pattern10  (10, 10, "10",     "10");


    private static final EnumPaintedDarkstone[] META_LOOKUP = new EnumPaintedDarkstone[values().length];
    private static final EnumPaintedDarkstone[] DYE_DMG_LOOKUP = new EnumPaintedDarkstone[values().length];
    private final int meta;
    private final int dyeDamage;
    private final String name;
    private final String unlocalizedName;

    EnumPaintedDarkstone(int meta, int dyeDamage, String name, String unlocalizedName)
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

    public static EnumPaintedDarkstone byDyeDamage(int damage)
    {
        if (damage < 0 || damage >= DYE_DMG_LOOKUP.length)
        {
            damage = 0;
        }

        return DYE_DMG_LOOKUP[damage];
    }

    public static EnumPaintedDarkstone byMetadata(int meta)
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
        for (EnumPaintedDarkstone type : values())
        {
            META_LOOKUP[type.getMetadata()] = type;
            DYE_DMG_LOOKUP[type.getDyeDamage()] = type;
        }
    }
}