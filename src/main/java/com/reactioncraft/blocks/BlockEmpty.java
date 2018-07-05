package com.reactioncraft.blocks;

import com.reactioncraft.blocks.*;
import com.reactioncraft.blocks.enums.*;
import com.reactioncraft.core.Logger;
import com.reactioncraft.registration.instances.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.*;
import net.minecraft.item.*;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.*;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockEmpty extends BlockBase implements MetadataArray
{
	public static final PropertyEnum<EnumEmptyBlocks> TYPE = PropertyEnum.<EnumEmptyBlocks>create("type", EnumEmptyBlocks.class);

	public BlockEmpty(Material material) 
	{
		super(material);
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) 
	{
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) 
	{
		return false;
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer()
	{
		return BlockRenderLayer.CUTOUT;
	}


	@Override
	public boolean canPlaceTorchOnTop(IBlockState state, IBlockAccess world, BlockPos pos) 
	{
		if (state.getValue(BlockEmpty.TYPE) == EnumEmptyBlocks.HUMAN)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) 
	{
		if (state.getValue(BlockEmpty.TYPE) == EnumEmptyBlocks.CREEPER)
		{
			//Left Right
			double x1 = 0.25;
			double x2 = 0.75;

			//up Down
			double y1 = 0;
			double y2 = 1.0;

			//Z axis
			double z1 = 0.25;
			double z2 = 0.75;

			final AxisAlignedBB BOUNDING_BOX=new AxisAlignedBB(x1, y1, z1, x2, y2, z2);

			return BOUNDING_BOX;
		}

		if (state.getValue(BlockEmpty.TYPE) == EnumEmptyBlocks.HUMAN)
		{
			//Left Right
			double x1 = 0.25;
			double x2 = 0.75;

			//up Down
			double y1 = 0;
			double y2 = 1.0;

			//Z axis
			double z1 = 0.25;
			double z2 = 0.75;

			final AxisAlignedBB BOUNDING_BOX=new AxisAlignedBB(x1, y1, z1, x2, y2, z2);

			return BOUNDING_BOX;
		}

		if (state.getValue(BlockEmpty.TYPE) == EnumEmptyBlocks.BLAZE)
		{
			//Left Right
			double x1 = 0.25;
			double x2 = 0.75;

			//up Down
			double y1 = 0;
			double y2 = 1.0;

			//Z axis
			double z1 = 0.25;
			double z2 = 0.75;

			final AxisAlignedBB BOUNDING_BOX=new AxisAlignedBB(x1, y1, z1, x2, y2, z2);

			return BOUNDING_BOX;
		}
		
		if (state.getValue(BlockEmpty.TYPE) == EnumEmptyBlocks.COIL)
		{
			//Left Right
			double x1 = 0.0;
			double x2 = 1.0;

			//up Down
			double y1 = 0;
			double y2 = 1.0;

			//Z axis
			double z1 = 0.0;
			double z2 = 1.0;

			final AxisAlignedBB BOUNDING_BOX=new AxisAlignedBB(x1, y1, z1, x2, y2, z2);

			return BOUNDING_BOX;
		}

		else
		{
			//Left Right
			double x1 = 0.0;
			double x2 = 1.0;

			//up Down
			double y1 = 0;
			double y2 = 1.0;

			//Z axis
			double z1 = 0.0;
			double z2 = 1.0;

			final AxisAlignedBB BOUNDING_BOX=new AxisAlignedBB(x1, y1, z1, x2, y2, z2);

			return BOUNDING_BOX;
		}
	}

	/**
	 * Called before the Block is set to air in the world. Called regardless of if the player's tool can actually
	 * collect this block
	 */
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player)
	{
		BlockPos blockposdown = pos.down();
		BlockPos blockposup   = pos.up();

		IBlockState creeper = BlockIndex.statues.getDefaultState().withProperty(BlockStatues.TYPE, EnumStatueBlocks.CREEPER);

		IBlockState human = BlockIndex.statues.getDefaultState().withProperty(BlockStatues.TYPE, EnumStatueBlocks.HUMAN);

		IBlockState blaze = BlockIndex.statues.getDefaultState().withProperty(BlockStatues.TYPE, EnumStatueBlocks.BLAZE);

		IBlockState coil = BlockIndex.statues.getDefaultState().withProperty(BlockStatues.TYPE, EnumStatueBlocks.COIL);

		if (state.getValue(BlockEmpty.TYPE) == EnumEmptyBlocks.CREEPER)
		{
			if (player.capabilities.isCreativeMode && state.getValue(BlockEmpty.TYPE) == EnumEmptyBlocks.CREEPER && worldIn.getBlockState(blockposdown) == creeper)
			{
				//if player is in creative mode   & the bottom block is a creeper & the world position up is empty
				worldIn.setBlockToAir(blockposdown);
				//set block below to air
				//Logger.info("hi");
			}
			if (!player.capabilities.isCreativeMode && state.getValue(BlockEmpty.TYPE) == EnumEmptyBlocks.CREEPER && worldIn.getBlockState(blockposdown) == creeper)
			{
				worldIn.setBlockToAir(blockposdown);
			}
		}

		if (state.getValue(BlockEmpty.TYPE) == EnumEmptyBlocks.HUMAN)
		{
			if (player.capabilities.isCreativeMode && state.getValue(BlockEmpty.TYPE) == EnumEmptyBlocks.HUMAN && worldIn.getBlockState(blockposdown) == human)
			{
				worldIn.setBlockToAir(blockposdown);
			}
			if (!player.capabilities.isCreativeMode && state.getValue(BlockEmpty.TYPE) == EnumEmptyBlocks.HUMAN && worldIn.getBlockState(blockposdown) == human)
			{
				worldIn.setBlockToAir(blockposdown);
			}
		}

		if (state.getValue(BlockEmpty.TYPE) == EnumEmptyBlocks.BLAZE)
		{
			if (player.capabilities.isCreativeMode && state.getValue(BlockEmpty.TYPE) == EnumEmptyBlocks.BLAZE && worldIn.getBlockState(blockposdown) == blaze)
			{
				worldIn.setBlockToAir(blockposdown);
			}
			if (!player.capabilities.isCreativeMode && state.getValue(BlockEmpty.TYPE) == EnumEmptyBlocks.BLAZE && worldIn.getBlockState(blockposdown) == blaze)
			{
				worldIn.setBlockToAir(blockposdown);
			}
		}

		if (state.getValue(BlockEmpty.TYPE) == EnumEmptyBlocks.COIL)
		{
			if (player.capabilities.isCreativeMode && state.getValue(BlockEmpty.TYPE) == EnumEmptyBlocks.COIL && worldIn.getBlockState(blockposdown) == coil)
			{
				worldIn.setBlockToAir(blockposdown);
			}
			if (!player.capabilities.isCreativeMode && state.getValue(BlockEmpty.TYPE) == EnumEmptyBlocks.COIL && worldIn.getBlockState(blockposdown) == coil)
			{
				worldIn.setBlockToAir(blockposdown);
			}
		}
	}

	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
	{
		if (state.getValue(BlockEmpty.TYPE) == EnumEmptyBlocks.CREEPER)
		{
			return new ItemStack(BlockIndex.statues, 1, 0);
		}
		if (state.getValue(BlockEmpty.TYPE) == EnumEmptyBlocks.HUMAN)
		{
			return new ItemStack(BlockIndex.statues, 1, 1);
		}
		if (state.getValue(BlockEmpty.TYPE) == EnumEmptyBlocks.BLAZE)
		{
			return new ItemStack(BlockIndex.statues, 1, 2);
		}
		if (state.getValue(BlockEmpty.TYPE) == EnumEmptyBlocks.COIL)
		{
			return new ItemStack(BlockIndex.statues, 1, 7);
		}
		else{ 
			return new ItemStack(Items.AIR); 
		}
	}


	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) 
	{
		EnumEmptyBlocks type = state.getValue(TYPE);

		super.getDrops(drops, world, pos, state, fortune); 

		if(type == EnumEmptyBlocks.CREEPER)
		{
			drops.clear();
			drops.add(new ItemStack(BlockIndex.statues, 1, 0));
		}

		if(type == EnumEmptyBlocks.HUMAN)
		{
			drops.clear();
			drops.add(new ItemStack(BlockIndex.statues, 1, 1));
		}

		if(type == EnumEmptyBlocks.BLAZE)
		{
			drops.clear();
			drops.add(new ItemStack(BlockIndex.statues, 1, 2));
		}

		if(type == EnumEmptyBlocks.COIL)
		{
			drops.clear();
			drops.add(new ItemStack(BlockIndex.statues, 1, 7));
		}
	}

	/**
	 * Gets the metadata of the item this Block can drop. This method is called when the block gets destroyed. It
	 * returns the metadata of the dropped item based on the old metadata of the block.
	 */
	public int damageDropped(IBlockState state)
	{
		return (state.getValue(TYPE)).getMetadata();
	}

	/**
	 * Convert the given metadata into a BlockState for this Block
	 */
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(TYPE, EnumEmptyBlocks.byMetadata(meta));
	}


	/**
	 * Convert the BlockState into the correct metadata value
	 */
	public int getMetaFromState(IBlockState state)
	{
		return (state.getValue(TYPE)).getMetadata();
	}


	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, TYPE);
	}
}