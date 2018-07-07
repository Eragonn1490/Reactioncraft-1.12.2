package com.reactioncraft.common.blocks.enums;

import net.minecraft.util.IStringSerializable;

public enum EnumEmptyBlocks implements IStringSerializable
{
    CREEPER  (0, 0,     "0",    "0"),
    HUMAN    (1, 1,     "1",    "1"),
	BLAZE    (2, 2,     "2",    "2"),
	COIL     (3, 3,     "3",    "3");

    private static final EnumEmptyBlocks[] META_LOOKUP = new EnumEmptyBlocks[values().length];
    private static final EnumEmptyBlocks[] DYE_DMG_LOOKUP = new EnumEmptyBlocks[values().length];
    private final int meta;
    private final int dyeDamage;
    private final String name;
    private final String unlocalizedName;

    EnumEmptyBlocks(int meta, int dyeDamage, String name, String unlocalizedName)
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

    public static EnumEmptyBlocks byDyeDamage(int damage)
    {
        if (damage < 0 || damage >= DYE_DMG_LOOKUP.length)
        {
            damage = 0;
        }

        return DYE_DMG_LOOKUP[damage];
    }

    public static EnumEmptyBlocks byMetadata(int meta)
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
        for (EnumEmptyBlocks type : values())
        {
            META_LOOKUP[type.getMetadata()] = type;
            DYE_DMG_LOOKUP[type.getDyeDamage()] = type;
        }
    }
}