package com.reactioncraft.utils;

import java.io.File;
import java.util.Locale;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.ModContainer;

public class ReactioncraftConfiguration extends Configuration 
{
	public ReactioncraftConfiguration(File file) 
	{
		super(file);
	}

	@Override
	public void save() 
	{
		super.save();
	}
	//public static final String ID = "REACTIONCRAFT".toLowerCase(Locale.ENGLISH);
}