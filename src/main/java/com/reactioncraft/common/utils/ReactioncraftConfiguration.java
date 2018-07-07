package com.reactioncraft.common.utils;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

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
}