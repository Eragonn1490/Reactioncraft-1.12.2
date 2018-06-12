package com.reactioncraft.blocks;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.core.Logger;
import com.reactioncraft.core.ServerProxy;
import com.reactioncraft.registration.instances.BlockIndex;
import com.reactioncraft.registration.instances.ItemIndex;
import com.reactioncraft.tiles.TileEntityReprogrammer;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockReprogrammer extends BlockBase
{
	protected String name;

	public BlockReprogrammer(Material material)
	{
		super(material);
	}

	public void registerItemModel(ItemBlock itemBlock)
	{
		Reactioncraft.proxy.registerItemRenderer(itemBlock, 0, getRegistryName().getResourcePath());
	}

	/**
	 * Returns a new instance of a block's tile entity class. Called on placing the block.
	 */
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityReprogrammer();
	}

	@Override
	public boolean hasTileEntity(IBlockState state) 
	{
		return true;
	}

	/**
	 * Called when the block is right clicked by a player.
	 */
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		ItemStack item = player.inventory.getCurrentItem();
		//TileEntityReprogrammer tileentity = (TileEntityReprogrammer) world.getTileEntity(pos);

		if (!world.isRemote) {
			return true;
		}

		//TileEntity tileentity = world.getTileEntity(pos);

		TileEntityReprogrammer tileentity = (TileEntityReprogrammer) world.getTileEntity(pos);
		if(item.getItem() == ItemIndex.wirelessTransmitter)
		{		
			world.setTileEntity(pos, tileentity);

			item.getTagCompound().getInteger("Channel");	

			Logger.info("Frequency: " + item.getTagCompound().getInteger("Channel"));

			NBTTagCompound storedchannel = new NBTTagCompound();

			int test = item.getTagCompound().getInteger("Channel");

			tileentity.writeToNBT(storedchannel);
			storedchannel.setInteger("Stored", test);

			Logger.info("Test :", TileEntityReprogrammer.channel);

			return true;

		}
		else
		{
			return false;
		}
	

	/** ATTEMPT 2 #failed
		ItemStack item = player.inventory.getCurrentItem();

		if (world.isRemote)
        {
            return true;
        }

        else
        {
            TileEntity tileentity = world.getTileEntity(pos);

            if (tileentity instanceof TileEntityReprogrammer)
            {
            	if(item.getItem() == ItemIndex.wirelessTransmitter)
        		{		
            		TileEntityReprogrammer reprogrammer = (TileEntityReprogrammer) world.getTileEntity(pos);

        			item.getTagCompound().getInteger("Channel");	

        			Logger.info("Frequency: " + item.getTagCompound().getInteger("Channel"));

        			reprogrammer.channel = item.getTagCompound().getInteger("Channel");

        			Logger.info("Test :", reprogrammer.channel);
        		}
            }

            return true;
        }
	 **/
}

@Override
public boolean isOpaqueCube(IBlockState state) {
	return true;
}

@Override
public BlockReprogrammer setCreativeTab(CreativeTabs tab)
{
	super.setCreativeTab(tab);
	return this;
}
}