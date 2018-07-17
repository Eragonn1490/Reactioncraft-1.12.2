package com.reactioncraft.common.blocks.enums;

import net.minecraft.util.IStringSerializable;

/**
 * Created on 12/29/17.
 */
public enum EnumColumnTypes implements IStringSerializable 
{
    BROWN1		(0, "0"),
    BROWN2		(1, "1"),
    BROWN3		(2, "2"),
    DARK_BLUE1  (3, "3"),
    DARK_BLUE2	(4, "4"),
    DARK_BLUE3	(5, "5"),
    LIGHT_BLUE1	(6, "6"),
    LIGHT_BLUE2	(7, "7"),
    LIGHT_BLUE3	(8, "8"),
    GOLD1		(9, "9"),
    GOLD2		(10, "10"),
    Bloodstone	(11, "11"),
    WEATHERED	(12, "12");

	private static final EnumColumnTypes[] META_LOOKUP = new EnumColumnTypes[values().length];
	//private final int meta;
	
    //NOTICE Can't exceed metadata limit of 16
    String unlocalizedName;
    int meta;
    EnumColumnTypes(int meta, String unlocalizedName_)
    {
        unlocalizedName=unlocalizedName_;
        this.meta = meta;
    }
    
    public int getMetadata()
    {
        return this.meta;
    }
    
    public static EnumColumnTypes byMetadata(int meta)
    {
        if (meta < 0 || meta >= META_LOOKUP.length)
        {
            meta = 0;
        }

        return META_LOOKUP[meta];
    }

    @Override
    public String getName() {
        return unlocalizedName;
    }
}