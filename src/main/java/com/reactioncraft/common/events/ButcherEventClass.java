package com.reactioncraft.common.events;

import com.google.common.collect.ImmutableMap;
import com.reactioncraft.registration.instances.ItemIndex;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Map;

public class ButcherEventClass
{
    
    private static Map<Object, Object> dropMap = ImmutableMap.builder()
    		//Add new drops for mobs below
    		.put(EntityPig.class,     ItemIndex.pork_chunk)
    		.put(EntityChicken.class, ItemIndex.chicken_head)
    		.put(EntityCow.class,     ItemIndex.beef_chunk)
    		.put(EntityHorse.class,   ItemIndex.raw_horse)
    		.put(EntityPlayer.class,  ItemIndex.raw_human)
    		//End of adding new mob drops.
    		.build();

    @SuppressWarnings("unused")
    @SubscribeEvent
    public void onEntityDrop(LivingDropsEvent event)
    {
        double rand = event.getEntityLiving().getEntityWorld().rand.nextDouble();

        try
        {
        	if (event.getSource().getTrueSource() instanceof EntityPlayer)
            {
                ItemStack e = ((EntityLivingBase)event.getSource().getTrueSource()).getHeldItemMainhand();

                if (e.getItem() == ItemIndex.meat_cleaver && rand < 1.0D)
                {
                    ItemStack drop = new ItemStack((Item)dropMap.get(event.getEntityLiving().getClass()));
                    event.getEntityLiving().entityDropItem(drop, 0.0F);
                }
            }
        }
        catch (NullPointerException var6) {} //This line is an empty exception dont change.
    }
}