package com.reactioncraft.blocks.machines;

import javax.annotation.Nullable;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.blocks.BlockBase;
import com.reactioncraft.tiles.TileEntityConverter;
import com.reactioncraft.utils.constants;
//import buildcraft.api.mj.MjAPI;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class BlockBloodstoneConverter extends BlockBase implements ITileEntityProvider
{
	public BlockBloodstoneConverter(Material material) 
	{
		super(material);
		this.setCreativeTab(Reactioncraft.Reactioncraft);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if(!worldIn.isRemote)
		{
			TileEntity te = worldIn.getTileEntity(pos);

			if(te instanceof TileEntityConverter)
			{
				//double amount = ((TileEntityConverter) te).getEnergyStored(side);
				//double total = ((TileEntityConverter) te).getMaxEnergyStored(side);

				//playerIn.sendStatusMessage(new TextComponentTranslation(constants.MODID + ".energybridge.stored", Math.round(amount)), true);
				//playerIn.sendStatusMessage(new TextComponentTranslation(constants.MODID + ".energybridge.max",    Math.round(total)), true);
			}
		}
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, side, hitX, hitY, hitZ);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) 
	{
		return new TileEntityConverter();
	}	
}