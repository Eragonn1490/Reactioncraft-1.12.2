package com.reactioncraft.blocks;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.blocks.enums.*;
import com.reactioncraft.core.Logger;
import com.reactioncraft.registration.instances.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.*;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.*;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import java.util.*;

import javax.annotation.Nullable;

public class BlockStatues extends BlockBase implements MetadataArray
{
	public static final PropertyEnum<EnumStatueBlocks> TYPE = PropertyEnum.<EnumStatueBlocks>create("type", EnumStatueBlocks.class);

	public BlockStatues(Material materialIn)
	{
		super(materialIn);
		this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, EnumStatueBlocks.CREEPER));
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean canPlaceTorchOnTop(IBlockState state, IBlockAccess world, BlockPos pos) 
	{
		if (state.getValue(BlockStatues.TYPE) == EnumStatueBlocks.COMPRESSED_GOLD)
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
		if (state.getValue(BlockStatues.TYPE) == EnumStatueBlocks.CREEPER)
		{
			//Left Right
			double x1 = 0.25;
			double x2 = 0.75;

			//up Down
			double y1 = 0;
			double y2 = 1.7;

			//Z axis
			double z1 = 0.25;
			double z2 = 0.75;

			final AxisAlignedBB BOUNDING_BOX=new AxisAlignedBB(x1, y1, z1, x2, y2, z2);

			return BOUNDING_BOX;
		}
		
		if (state.getValue(BlockStatues.TYPE) == EnumStatueBlocks.HUMAN)
		{
			//Left Right
			double x1 = 0.25;
			double x2 = 0.75;

			//up Down
			double y1 = 0;
			double y2 = 2.0;

			//Z axis
			double z1 = 0.25;
			double z2 = 0.75;

			final AxisAlignedBB BOUNDING_BOX=new AxisAlignedBB(x1, y1, z1, x2, y2, z2);

			return BOUNDING_BOX;
		}
		
		if (state.getValue(BlockStatues.TYPE) == EnumStatueBlocks.BLAZE)
		{
			//Left Right
			double x1 = 0.25;
			double x2 = 0.75;

			//up Down
			double y1 = 0;
			double y2 = 1.7;

			//Z axis
			double z1 = 0.25;
			double z2 = 0.75;

			final AxisAlignedBB BOUNDING_BOX=new AxisAlignedBB(x1, y1, z1, x2, y2, z2);

			return BOUNDING_BOX;
		}
		
		if (state.getValue(BlockStatues.TYPE) == EnumStatueBlocks.BAG_B || state.getValue(BlockStatues.TYPE) == EnumStatueBlocks.BAG_S || state.getValue(BlockStatues.TYPE) == EnumStatueBlocks.BAG_G)
		{
			//Left Right
			double x1 = 0.25;
			double x2 = 0.75;

			//up Down
			double y1 = 0;
			double y2 = 0.5;

			//Z axis
			double z1 = 0.25;
			double z2 = 0.75;

			final AxisAlignedBB BOUNDING_BOX=new AxisAlignedBB(x1, y1, z1, x2, y2, z2);

			return BOUNDING_BOX;
		}

		if (state.getValue(BlockStatues.TYPE) == EnumStatueBlocks.GLASS)
		{
			//Left Right
			double x1 = 0.3;
			double x2 = 0.70;

			//up Down
			double y1 = 0;
			double y2 = 0.6;

			//Z axis
			double z1 = 0.3;
			double z2 = 0.70;

			final AxisAlignedBB BOUNDING_BOX=new AxisAlignedBB(x1, y1, z1, x2, y2, z2);

			return BOUNDING_BOX;
		}

		if (state.getValue(BlockStatues.TYPE) == EnumStatueBlocks.COMPRESSED_GOLD)
		{
			//Left Right
			double x1 = 0;
			double x2 = 1;

			//up Down
			double y1 = 0;
			double y2 = 1;

			//Z axis
			double z1 = 0;
			double z2 = 1;

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

	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean p_185477_7_)
	{
		if (state.getValue(BlockStatues.TYPE) == EnumStatueBlocks.HUMAN)
		{	
			addCollisionBoxToList(pos,      entityBox, collidingBoxes, state.getBoundingBox(worldIn, pos));
			addCollisionBoxToList(pos.up(), entityBox, collidingBoxes, state.getBoundingBox(worldIn, pos));
		}
		else
		{
			addCollisionBoxToList(pos,      entityBox, collidingBoxes, state.getBoundingBox(worldIn, pos));
		}
	}

	/**
     * Called by ItemBlocks after a block is set in the world, to allow post-place logic
     */
	@Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
		IBlockState human = BlockIndex.emptystatues.getDefaultState().withProperty(BlockEmpty.TYPE, EnumEmptyBlocks.HUMAN);
		
		IBlockState blaze = BlockIndex.emptystatues.getDefaultState().withProperty(BlockEmpty.TYPE, EnumEmptyBlocks.BLAZE);
		
		IBlockState creeper = BlockIndex.emptystatues.getDefaultState().withProperty(BlockEmpty.TYPE, EnumEmptyBlocks.CREEPER);
		
		IBlockState coil = BlockIndex.emptystatues.getDefaultState().withProperty(BlockEmpty.TYPE, EnumEmptyBlocks.COIL);
		
		if (state.getValue(BlockStatues.TYPE) == EnumStatueBlocks.HUMAN) 
		{
			worldIn.setBlockState(pos.up(), human);
		}
		
		if(state.getValue(BlockStatues.TYPE) == EnumStatueBlocks.BLAZE)
		{
			worldIn.setBlockState(pos.up(), blaze);
		}
		
		if(state.getValue(BlockStatues.TYPE) == EnumStatueBlocks.CREEPER)
		{
			worldIn.setBlockState(pos.up(), creeper);
		}
		
		if(state.getValue(BlockStatues.TYPE) == EnumStatueBlocks.COIL)
		{
			worldIn.setBlockState(pos.up(), coil);
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

		IBlockState creeperEmpty = BlockIndex.emptystatues.getDefaultState().withProperty(BlockEmpty.TYPE, EnumEmptyBlocks.CREEPER);
		
		IBlockState humanEmpty   = BlockIndex.emptystatues.getDefaultState().withProperty(BlockEmpty.TYPE, EnumEmptyBlocks.HUMAN);
		
		IBlockState blazeEmpty   = BlockIndex.emptystatues.getDefaultState().withProperty(BlockEmpty.TYPE, EnumEmptyBlocks.BLAZE);
		
		IBlockState coilEmpty    = BlockIndex.emptystatues.getDefaultState().withProperty(BlockEmpty.TYPE, EnumEmptyBlocks.COIL);
		
		//
		if (state.getValue(BlockStatues.TYPE) == EnumStatueBlocks.CREEPER)
		{
			if (player.capabilities.isCreativeMode && state.getValue(BlockStatues.TYPE) == EnumStatueBlocks.CREEPER && worldIn.getBlockState(blockposup) == creeperEmpty)
			{
				//if player is in creative mode   & the bottom block is a creeper & the world position up is empty
				worldIn.setBlockToAir(blockposup);
				//set block below to air
				//Logger.info("hi");
			}
			if (!player.capabilities.isCreativeMode && state.getValue(BlockStatues.TYPE) == EnumStatueBlocks.CREEPER && worldIn.getBlockState(blockposup) == creeperEmpty)
			{
				//if player is in creative mode   & the bottom block is a creeper & the world position up is empty
				worldIn.setBlockToAir(blockposup);
				//set block below to air
				//Logger.info("hey");
			}
		}
		
		if (state.getValue(BlockStatues.TYPE) == EnumStatueBlocks.HUMAN)
		{
			if (player.capabilities.isCreativeMode && state.getValue(BlockStatues.TYPE) == EnumStatueBlocks.HUMAN && worldIn.getBlockState(blockposup) == humanEmpty)
			{
				//if player is in creative mode   & the bottom block is a creeper & the world position up is empty
				worldIn.setBlockToAir(blockposup);
				//set block below to air
				//Logger.info("hi");
			}
			if (!player.capabilities.isCreativeMode && state.getValue(BlockStatues.TYPE) == EnumStatueBlocks.HUMAN && worldIn.getBlockState(blockposup) == humanEmpty)
			{
				//if player is in creative mode   & the bottom block is a creeper & the world position up is empty
				worldIn.setBlockToAir(blockposup);
				//set block below to air
				//Logger.info("hey");
			}
		}
		
		if (state.getValue(BlockStatues.TYPE) == EnumStatueBlocks.BLAZE)
		{
			if (player.capabilities.isCreativeMode && state.getValue(BlockStatues.TYPE) == EnumStatueBlocks.BLAZE && worldIn.getBlockState(blockposup) == blazeEmpty)
			{
				//if player is in creative mode   & the bottom block is a creeper & the world position up is empty
				worldIn.setBlockToAir(blockposup);
				//set block below to air
				//Logger.info("hi");
			}
			if (!player.capabilities.isCreativeMode && state.getValue(BlockStatues.TYPE) == EnumStatueBlocks.BLAZE && worldIn.getBlockState(blockposup) == blazeEmpty)
			{
				//if player is in creative mode   & the bottom block is a creeper & the world position up is empty
				worldIn.setBlockToAir(blockposup);
				//set block below to air
				//Logger.info("hey");
			}
		}
		
		if (state.getValue(BlockStatues.TYPE) == EnumStatueBlocks.COIL)
		{
			if (player.capabilities.isCreativeMode && state.getValue(BlockStatues.TYPE) == EnumStatueBlocks.COIL && worldIn.getBlockState(blockposup) == coilEmpty)
			{
				//if player is in creative mode   & the bottom block is a creeper & the world position up is empty
				worldIn.setBlockToAir(blockposup);
				//set block below to air
				//Logger.info("hi");
			}
			if (!player.capabilities.isCreativeMode && state.getValue(BlockStatues.TYPE) == EnumStatueBlocks.COIL && worldIn.getBlockState(blockposup) == coilEmpty)
			{
				//if player is in creative mode   & the bottom block is a creeper & the world position up is empty
				worldIn.setBlockToAir(blockposup);
				//set block below to air
				//Logger.info("hey");
			}
		}
	}
    
	/**
	 * Gets the metadata of the item this Block can drop. This method is called when the block gets destroyed. It
	 * returns the metadata of the dropped item based on the old metadata of the block.
	 */
	public int damageDropped(IBlockState state)
	{
		if(state.getValue(BlockStatues.TYPE) == EnumStatueBlocks.CREEPER)
		{
			return 0;
		}
		else
		{
			return (state.getValue(TYPE)).getMetadata();
		}
	}

	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) 
	{
		EnumStatueBlocks type = state.getValue(TYPE);

		super.getDrops(drops, world, pos, state, fortune); 

		if(type == EnumStatueBlocks.BAG_G)
		{
			drops.clear();
			drops.add(new ItemStack(ItemIndex.coins, 1, 15));
		}

		if(type == EnumStatueBlocks.BAG_S)
		{
			drops.clear();
			drops.add(new ItemStack(ItemIndex.coins, 1, 10));
		}

		if(type == EnumStatueBlocks.BAG_B)
		{
			drops.clear();
			drops.add(new ItemStack(ItemIndex.coins, 1, 5));
		}
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) 
	{
		EnumStatueBlocks desertBlocks=state.getValue(BlockStatues.TYPE);
		//        if(desertBlocks==EnumStatueBlocks.)
		return super.getItemDropped(state, rand, fortune);
	}

	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) 
	{
		items.add(new ItemStack(this,1,EnumStatueBlocks.CREEPER.getMetadata()));
		items.add(new ItemStack(this,1,EnumStatueBlocks.HUMAN.getMetadata()));
		items.add(new ItemStack(this,1,EnumStatueBlocks.BLAZE.getMetadata()));
		items.add(new ItemStack(this,1,EnumStatueBlocks.MIXER.getMetadata()));
		items.add(new ItemStack(this,1,EnumStatueBlocks.COIL.getMetadata()));
		items.add(new ItemStack(this,1,EnumStatueBlocks.GLASS.getMetadata()));
		items.add(new ItemStack(this,1,EnumStatueBlocks.COMPRESSED_GOLD.getMetadata()));
	}


	/**
	 * Convert the given metadata into a BlockState for this Block
	 */
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(TYPE, EnumStatueBlocks.byMetadata(meta));
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