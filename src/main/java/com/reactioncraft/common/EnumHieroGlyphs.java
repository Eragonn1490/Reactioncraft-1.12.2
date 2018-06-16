package com.reactioncraft.common;

import net.minecraft.util.IStringSerializable;

public enum EnumHieroGlyphs implements IStringSerializable
{
    BROWN1(0, 12,   "one1",       "0"),
    BROWN2(1, 11,   "one2",       "1"),
    BROWN3(2, 10,   "one3",       "2"),
    BLUE1(3, 9,   "two1",       "3"),
    BLUE2(4, 8,   "two2", 	   "4"),
    BLUE3(5, 7,   "two3",       "5"),
    CYAN1(6, 6,   "three1",     "6"),
    CYAN2(7,5,   "three2",     "7"),
    CYAN3(8, 4,   "three3",     "8"),
    GOLD1(9, 3, 	 "four1",      "9"),
    GOLD2(10, 2, "four2",      "10"),
    GOLD3(11, 1, "four3",      "11"),
    WEATHERED(12, 0, "five1",      "12");

    private static final EnumHieroGlyphs[] META_LOOKUP = new EnumHieroGlyphs[values().length];
    private static final EnumHieroGlyphs[] DYE_DMG_LOOKUP = new EnumHieroGlyphs[values().length];
    private final int meta;
    private final int dyeDamage;
    private final String name;
    private final String unlocalizedName;

    EnumHieroGlyphs(int meta, int dyeDamage, String name, String unlocalizedName)
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

    public static EnumHieroGlyphs byDyeDamage(int damage)
    {
        if (damage < 0 || damage >= DYE_DMG_LOOKUP.length)
        {
            damage = 0;
        }

        return DYE_DMG_LOOKUP[damage];
    }

    public static EnumHieroGlyphs byMetadata(int meta)
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
        for (EnumHieroGlyphs type : values())
        {
            META_LOOKUP[type.getMetadata()] = type;
            DYE_DMG_LOOKUP[type.getDyeDamage()] = type;
        }
    }
}