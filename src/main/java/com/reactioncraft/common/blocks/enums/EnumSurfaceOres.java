package com.reactioncraft.common.blocks.enums;

import net.minecraft.util.IStringSerializable;

public enum EnumSurfaceOres implements IStringSerializable
{
    DESERT_COAL      (0, 0,  "0",       "0"),
    DARK_BLUE_GEM    (1, 1,  "1",       "1"),
    LIGHT_BLUE_GEM   (2, 2,  "2",       "2"),
    DESERT_GOLD      (3, 3,  "3",       "3"),
    SILVER           (4, 4,  "4", 	    "4"),
    COPPER           (5, 5,  "5",       "5");


    private static final EnumSurfaceOres[] META_LOOKUP = new EnumSurfaceOres[values().length];
    private static final EnumSurfaceOres[] DYE_DMG_LOOKUP = new EnumSurfaceOres[values().length];
    private final int meta;
    private final int dyeDamage;
    private final String name;
    private final String unlocalizedName;

    EnumSurfaceOres(int meta, int dyeDamage, String name, String unlocalizedName)
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

    public static EnumSurfaceOres byDyeDamage(int damage)
    {
        if (damage < 0 || damage >= DYE_DMG_LOOKUP.length)
        {
            damage = 0;
        }

        return DYE_DMG_LOOKUP[damage];
    }

    public static EnumSurfaceOres byMetadata(int meta)
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
        for (EnumSurfaceOres type : values())
        {
            META_LOOKUP[type.getMetadata()] = type;
            DYE_DMG_LOOKUP[type.getDyeDamage()] = type;
        }
    }
}