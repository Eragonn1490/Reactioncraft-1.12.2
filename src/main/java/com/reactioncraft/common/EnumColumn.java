package com.reactioncraft.common;

import net.minecraft.util.IStringSerializable;

public enum EnumColumn implements IStringSerializable
{
    one1		(0, 0,   "one1",       "0"),
    one2		(1, 1,   "one2",       "1"),
    one3		(2, 2,   "one3",       "2"),
    two1	    (3, 3,   "two1",       "3"),
    two2		(4, 4,   "two2", 	   "4"),
    two3		(5, 5,   "two3",       "5"),
    three1		(6, 6,   "three1",     "6"),
    three2		(7, 7,   "three2",     "7"),
    three3		(8, 8,   "three3",     "8"),
    four1		(9, 9, 	 "four1",      "9"),
    four2		(10, 10, "four2",      "10"),
    four3		(11, 11, "four3",      "11"),
    five1       (12, 12, "five1",      "12"),
    five2		(13, 13, "five2",      "13"),
    five3		(14, 14, "five3",      "14"),
    six1		(15, 15, "six1",       "15");

    private static final EnumColumn[] META_LOOKUP = new EnumColumn[values().length];
    private static final EnumColumn[] DYE_DMG_LOOKUP = new EnumColumn[values().length];
    private final int meta;
    private final int dyeDamage;
    private final String name;
    private final String unlocalizedName;

    EnumColumn(int meta, int dyeDamage, String name, String unlocalizedName)
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

    public static EnumColumn byDyeDamage(int damage)
    {
        if (damage < 0 || damage >= DYE_DMG_LOOKUP.length)
        {
            damage = 0;
        }

        return DYE_DMG_LOOKUP[damage];
    }

    public static EnumColumn byMetadata(int meta)
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
        for (EnumColumn type : values())
        {
            META_LOOKUP[type.getMetadata()] = type;
            DYE_DMG_LOOKUP[type.getDyeDamage()] = type;
        }
    }
}