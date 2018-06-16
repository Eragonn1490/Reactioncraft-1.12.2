package com.reactioncraft.common;

import net.minecraft.util.IStringSerializable;

/**
 * Created on 12/29/17.
 */
public enum ColumnTypes implements IStringSerializable{
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
    MULTI_BRICK("13"),
    CHISELED1("14"),
//    CHISELED2("15"),
    BRICK("15"),
//    MOSSY_BRICK("17")
    ;

    //NOTICE Can't exceed metadata limit of 16

    String unlocalizedName;
    ColumnTypes(String unlocalizedName_)
    {
        unlocalizedName=unlocalizedName_;
    }

    @Override
    public String getName() {
        return unlocalizedName;
    }
}
