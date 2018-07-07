package com.reactioncraft.common.blocks.enums;

import net.minecraft.util.IStringSerializable;

/**
 * Created on 12/29/17.
 */
public enum EnumColumnTypes implements IStringSerializable {
    BROWN1("0"),
    BROWN2("1"),
    BROWN3("2"),
    DARK_BLUE1("3"),
    DARK_BLUE2("4"),
    DARK_BLUE3("5"),
    LIGHT_BLUE1("6"),
    LIGHT_BLUE2("7"),
    LIGHT_BLUE3("8"),
    GOLD1("9"),
    GOLD2("10"),
    GOLD3("11"),
    WEATHERED("12"),
    ;

	
    //NOTICE Can't exceed metadata limit of 16
    String unlocalizedName;
    EnumColumnTypes(String unlocalizedName_)
    {
        unlocalizedName=unlocalizedName_;
    }

    @Override
    public String getName() {
        return unlocalizedName;
    }
}