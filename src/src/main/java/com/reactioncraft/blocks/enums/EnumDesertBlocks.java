package com.reactioncraft.blocks.enums;

import net.minecraft.util.IStringSerializable;

public enum EnumDesertBlocks implements IStringSerializable
{
    DESERT_Bricks      (0, 0,   "0",       "0"),
    Dark_cobble        (1, 1,   "1",       "1"),
    Chiseled_darkstone (2, 2,   "2",       "2"),
    GRANITE            (3, 3,   "3",       "3"),
    Cracked		       (4, 4,   "4", 	   "4"),
    Mossy		       (5, 5,   "5",       "5"),
    Brick		       (6, 6,   "6",     "6"),
    Darkstone          (7, 7,   "7",     "7"),
    Chiseled_Granite   (8, 8,   "8",     "8");
//    four1		(9, 9, 	 "four1",      "four1"),
//    four2		(10, 10, "four2",      "four2"),
//    four3		(11, 11, "four3",      "four3"),
//    five1       (12, 12, "five1",      "five1"),
//	five2		(13, 13, "five2",      "five2"),
//	five3		(14, 14, "five3",      "five3"),
//	six1		(15, 15, "six1",       "six1");

    private static final EnumDesertBlocks[] META_LOOKUP = new EnumDesertBlocks[values().length];
    private static final EnumDesertBlocks[] DYE_DMG_LOOKUP = new EnumDesertBlocks[values().length];
    private final int meta;
    private final int dyeDamage;
    private final String name;
    private final String unlocalizedName;

    EnumDesertBlocks(int meta, int dyeDamage, String name, String unlocalizedName)
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

    public static EnumDesertBlocks byDyeDamage(int damage)
    {
        if (damage < 0 || damage >= DYE_DMG_LOOKUP.length)
        {
            damage = 0;
        }

        return DYE_DMG_LOOKUP[damage];
    }

    public static EnumDesertBlocks byMetadata(int meta)
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
        for (EnumDesertBlocks type : values())
        {
            META_LOOKUP[type.getMetadata()] = type;
            DYE_DMG_LOOKUP[type.getDyeDamage()] = type;
        }
    }
}