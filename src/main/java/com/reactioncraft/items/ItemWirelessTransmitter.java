package com.reactioncraft.items;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.Nullable;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.common.ItemModelProvider;
import com.reactioncraft.core.Logger;
import com.reactioncraft.registration.instances.BlockIndex;
import com.reactioncraft.registration.instances.ItemIndex;

import com.reactioncraft.utils.constants;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemWirelessTransmitter extends ItemBase
{
	public ItemWirelessTransmitter(String name) 
	{
		super(name);
		this.setUnlocalizedName(constants.MODID + "." +name);
		this.setMaxStackSize(1);
	}

	@Override
	public void registerItemModel() 
	{
		Reactioncraft.proxy.registerItemRenderer(this, 0, this.getRegistryName().getResourcePath());
	}

	@Override
	public ItemWirelessTransmitter setCreativeTab(CreativeTabs tab) 
	{
		super.setCreativeTab(tab);
		return this;
	}
	
	/**
     * Called each tick as long the item is on a player inventory. Uses by maps to check if is on a player hand and
     * update it's contents.
     */
	public void onUpdate(ItemStack item, World world, Entity player, int itemSlot, boolean isSelected) 
	{
		if (!item.hasTagCompound()) 
		{
			item.setTagCompound(new NBTTagCompound());
			int min = Integer.MIN_VALUE;
			int max = Integer.MAX_VALUE;
			
			int channel = ThreadLocalRandom.current().nextInt(min + 1, max);
			int storechannel = channel;
			
			NBTTagCompound frequency = new NBTTagCompound();
			
			frequency.setInteger("Channel", storechannel);
			frequency.setString ("Frequency", "Channel: " + storechannel);
			
			item.setTagCompound(frequency);
		}
    }
	
	@Override
    public void addInformation(ItemStack itemStack, @Nullable World worldIn, List<String> list, ITooltipFlag flagIn) {
        if (itemStack.getTagCompound() != null)
        {
            list.add("" + itemStack.getTagCompound().getString("Frequency"));
            list.add("" + itemStack.getTagCompound().getInteger("Channel"));//for debgging only
        }
        else { list.add("Please craft to see results"); }
    }
}