package com.reactioncraft.blocks;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.entities.EntityBee;
import com.reactioncraft.registration.instances.ItemIndex;
import net.minecraft.block.BlockDragonEgg;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class BlockHive extends BlockDragonEgg
{
//	private Icon[][] iconBuffer;
	private EntityPlayer entityplayer;
	
	public BlockHive()
	{
		super();

//		this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 1.0F, 0.9375F);
//		this.setBurnProperties(this.blockID, 30, 60);

		this.setCreativeTab(Reactioncraft.Reactioncraft);
	}





//	public static boolean canFallBelow(World par0World, int par1, int par2, int par3)
//	{
//		return false;
//	}

	@Override
	public void onBlockDestroyedByPlayer(World world, BlockPos pos, IBlockState state) {
		if (!world.isRemote)
		{
			EntityBee entitysilverfish  = new EntityBee(world);
			EntityBee entitysilverfish1 = new EntityBee(world);
			EntityBee entitysilverfish2 = new EntityBee(world);
			EntityBee entitysilverfish3 = new EntityBee(world);
			int par2=pos.getX(), par3=pos.getY(), par4=pos.getZ();
			entitysilverfish.setLocationAndAngles((double)par2 + 0.5D, (double)par3, (double)par4 + 0.5D, 0.0F, 0.0F);
			entitysilverfish1.setLocationAndAngles((double)par2 + 0.5D, (double)par3, (double)par4 + 0.5D, 0.0F, 0.0F);
			entitysilverfish2.setLocationAndAngles((double)par2 + 0.5D, (double)par3, (double)par4 + 0.5D, 0.0F, 0.0F);
			entitysilverfish3.setLocationAndAngles((double)par2 + 0.5D, (double)par3, (double)par4 + 0.5D, 0.0F, 0.0F);
			world.spawnEntity(entitysilverfish);
			world.spawnEntity(entitysilverfish1);
			world.spawnEntity(entitysilverfish2);
			world.spawnEntity(entitysilverfish3);
		}
		super.onBlockDestroyedByPlayer(world, pos, state);
	}


	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}




	@Override
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		return true;
	}



//	@Override
//	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
//		return new ItemStack(BlockIndex.be)
//	}





	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
		items.add(new ItemStack(this,1,0));
	}

	@Override
	public int damageDropped(IBlockState state) {

		return super.damageDropped(state);
	}

//	/**
//	 * Returns the ID of the items to drop on destruction.
//	 */
//	@Override
//	public int idDropped(int i, Random random, int j)
//	{
//		switch (i)
//		{
//		default:
//			return -1;
//
//		case 1:
//			return -1;
//
//		case 2:
//			return blockID;
//
//		case 3:
//			return blockID;
//
//		case 4:
//			return blockID;
//
//		case 5:
//			return blockID;
//
//		case 6:
//			return blockID;
//
//		case 7:
//			return blockID;
//
//			//	            case 8:
//			//	                return RCBDM.UncutLBGem.itemID;
//			//
//			//	            case 9:
//			//	                return RCBDM.UncutDBGem.itemID;
//
//		case 11:
//			return blockID;
//		}
//	}


	@Override
	public void getDrops(NonNullList<ItemStack> ret, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		if(world instanceof WorldServer) {
			for (int i = 0; i < ((WorldServer) world).rand.nextInt(3); i++)
				ret.add(new ItemStack(ItemIndex.pollencomb, 1, 0));
			for (int i = 0; i < ((WorldServer) world).rand.nextInt(3); i++)
				ret.add(new ItemStack(ItemIndex.honeycomb, 1, 0));
		}
	}
}