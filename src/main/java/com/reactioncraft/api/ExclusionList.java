package com.reactioncraft.api;

import java.util.*;
import net.minecraft.entity.*;

public class ExclusionList implements Iterable<String>
{
	public static List<String> list = new ArrayList<String>();
	
	public ExclusionList()
	{
		//Example of Excluded Entites 
		//this.addExclusion("EnderDragon");
		//this.addExclusion("dragonPartHead");
		//this.addExclusion("dragonPartBody");
		//this.addExclusion("dragonPartTail1");
		//this.addExclusion("dragonPartTail2");
		//this.addExclusion("dragonPartTail3");
		//this.addExclusion("dragonPartWing1");
		//this.addExclusion("dragonPartWing2");
		//this.addExclusion("FallingSand");
		//this.addExclusion("Fireball");
		//this.addExclusion("PrimedTnt");
		//this.addExclusion("MinecartTNT");
		//this.addExclusion("WitherSkull");
		//this.addExclusion("Hydrolisc");
	}

	public void addExclusion(String name)
	{
		list.add(name);
	}

	public void removeExclusion(String name)
	{
		list.remove(name);
	}

	public boolean isExcluded(String name)
	{
		return list.contains(name);
	}

	public void addExclusion(Entity entity)
	{
		list.add(EntityList.getEntityString(entity));
	}

	public void addExclusion(EntityLiving entity)
	{
		list.add(EntityList.getEntityString(entity));
	}
	
	public void removeExclusion(Entity entity)
	{
		list.remove(EntityList.getEntityString(entity));
	}

	public boolean isExcluded(Entity entity)
	{
		return list.contains(EntityList.getEntityString(entity));
	}

	public Iterator<String> iterator()
	{
		return list.iterator();
	}
}