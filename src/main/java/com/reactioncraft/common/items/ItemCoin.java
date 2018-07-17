package com.reactioncraft.common.items;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.common.instances.BlockIndex;
import com.reactioncraft.common.instances.ItemIndex;
import com.reactioncraft.common.utils.constants;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemCoin extends Item
{
	public ItemCoin()
	{
		super();
		this.setHasSubtypes(true);
	}

	private static final String[] names = new String[] { "0", "1","2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"};

	public String getUnlocalizedName(ItemStack par1ItemStack)
	{	
		int i =getMetadata(par1ItemStack);

		return "item."+constants.MODID + ".coin." + names[i];
	}

	@Override
	public int getMetadata(int damage) { return damage; }

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) 
	{
		if(tab==Reactioncraft.ReactioncraftItems) 
		{
			for (int i = 0; i < 16; i++) { items.add(new ItemStack(this, 1, i)); }
		}
	}

	/**
	 * Called when a Block is right-clicked with this Item
	 */
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		ItemStack itemstack = player.getHeldItemMainhand();
			
		if(player.getHeldItemMainhand().getMetadata() == ItemIndex.coins.getMetadata(15))
		{
			if (facing == EnumFacing.UP && player.canPlayerEdit(pos.offset(facing), facing, itemstack) && worldIn.isAirBlock(pos.up()))
			{
				//Logger.info("Gold");
				worldIn.setBlockState(pos.up(), BlockIndex.statues.getStateFromMeta(6));
				player.getHeldItemMainhand().shrink(1);
				return EnumActionResult.PASS;
			}
		}

		if(player.getHeldItemMainhand().getMetadata() == ItemIndex.coins.getMetadata(10))
		{		
			if (facing == EnumFacing.UP && player.canPlayerEdit(pos.offset(facing), facing, itemstack) && worldIn.isAirBlock(pos.up()))
			{
				//Logger.info("silver");
				worldIn.setBlockState(pos.up(), BlockIndex.statues.getStateFromMeta(5));
				player.getHeldItemMainhand().shrink(1);
				return EnumActionResult.PASS;
			}
		}

		if(player.getHeldItemMainhand().getMetadata() == ItemIndex.coins.getMetadata(5))
		{				
			if (facing == EnumFacing.UP && player.canPlayerEdit(pos.offset(facing), facing, itemstack) && worldIn.isAirBlock(pos.up()))
			{
				//Logger.info("Bronze");
				worldIn.setBlockState(pos.up(), BlockIndex.statues.getStateFromMeta(4));
				player.getHeldItemMainhand().shrink(1);
				return EnumActionResult.PASS;
			}
		}
		else if (player.getHeldItemMainhand().getMetadata() != ItemIndex.coins.getMetadata(5) || player.getHeldItemMainhand().getMetadata() != ItemIndex.coins.getMetadata(10) || player.getHeldItemMainhand().getMetadata() != ItemIndex.coins.getMetadata(15))
		{
			//Logger.info("Not the right meta or item ", player.getHeldItemMainhand().getItem());
			return EnumActionResult.FAIL;
		}
		
		return EnumActionResult.PASS;
	}
}