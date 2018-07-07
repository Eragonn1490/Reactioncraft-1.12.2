package com.reactioncraft.common.blocks.enums;

import net.minecraft.util.IStringSerializable;

public enum EnumStatueBlocks implements IStringSerializable
{
    CREEPER  (0, 0,     "0",    "0"),
    HUMAN    (1, 1,     "1",    "1"),
	BLAZE    (2, 2,     "2",    "2"),
	MIXER    (3, 3,     "3",    "3"),
	BAG_B    (4, 4,     "4",    "4"),
	BAG_S    (5, 5,     "5",    "5"),
	BAG_G    (6, 6,     "6",    "6"),
	COIL     (7, 7,     "7",    "7"),
	GLASS    (8, 8,     "8",    "8"),
	COMPRESSED_GOLD(9, 9,     "9",    "9");

    private static final EnumStatueBlocks[] META_LOOKUP = new EnumStatueBlocks[values().length];
    private static final EnumStatueBlocks[] DYE_DMG_LOOKUP = new EnumStatueBlocks[values().length];
    private final int meta;
    private final int dyeDamage;
    private final String name;
    private final String unlocalizedName;

    EnumStatueBlocks(int meta, int dyeDamage, String name, String unlocalizedName)
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

    public static EnumStatueBlocks byDyeDamage(int damage)
    {
        if (damage < 0 || damage >= DYE_DMG_LOOKUP.length)
        {
            damage = 0;
        }

        return DYE_DMG_LOOKUP[damage];
    }

    public static EnumStatueBlocks byMetadata(int meta)
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
        for (EnumStatueBlocks type : values())
        {
            META_LOOKUP[type.getMetadata()] = type;
            DYE_DMG_LOOKUP[type.getDyeDamage()] = type;
        }
    }
}