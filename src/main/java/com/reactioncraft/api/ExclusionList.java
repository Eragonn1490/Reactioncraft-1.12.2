package com.reactioncraft.api;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExclusionList implements Iterable<String>
{
    public static List<String> list = new ArrayList<String>();

    public ExclusionList()
    {
        this.addExclusion("EnderDragon");
        this.addExclusion("dragonPartHead");
        this.addExclusion("dragonPartBody");
        this.addExclusion("dragonPartTail1");
        this.addExclusion("dragonPartTail2");
        this.addExclusion("dragonPartTail3");
        this.addExclusion("dragonPartWing1");
        this.addExclusion("dragonPartWing2");
        this.addExclusion("FallingSand");
        this.addExclusion("Fireball");
        this.addExclusion("PrimedTnt");
        this.addExclusion("MinecartTNT");
        this.addExclusion("WitherSkull");
        this.addExclusion("Hydrolisc");
        //To Add custom Exclusions to the mods
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