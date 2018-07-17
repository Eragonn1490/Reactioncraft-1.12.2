package com.reactioncraft.common.items;


import com.reactioncraft.Reactioncraft;
import com.reactioncraft.common.instances.BlockIndex;
import com.reactioncraft.common.instances.ItemIndex;
import com.reactioncraft.common.items.tools.ItemBaseSword;
import com.reactioncraft.common.utils.ItemModelProvider;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemKnife extends ItemBaseSword implements ItemModelProvider
{
	protected String name;

	public ItemKnife(String name)
	{
		super(name, ToolMaterial.IRON);
		this.name = name;
		this.setMaxStackSize(1);
		this.setMaxDamage(25);
		this.setCreativeTab(Reactioncraft.ReactioncraftItems);
		this.setNoRepair();
	}

	/**
	 * Returns true if players can use this item to affect the world (e.g. placing blocks, placing ender eyes in portal)
	 * when not in creative
	 */
	public boolean canItemEditBlocks()
	{
		return false;
	}

	/**
	 * Called when a Block is right-clicked with this Item
	 */
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		boolean hasbowl;
		ItemStack stack = player.getHeldItem(hand);
		IBlockState blockState = worldIn.getBlockState(pos);
		ItemStack bowlwater = new ItemStack(ItemIndex.bowlwater);

		for (int j = 0; j < player.inventory.getSizeInventory(); j++)
		{
			ItemStack slotStack1 = player.inventory.getStackInSlot(j);

			if (slotStack1.getItem() == Items.BOWL)
			{
				//Logger.info("has bowl");
				hasbowl = true;

				/** **/
				if(stack.getItem() == ItemIndex.knife && hasbowl == true)
				{
					//Logger.info("Testing if knife is in hand");
					if(blockState.getBlock() == Blocks.CACTUS || blockState.getBlock() == BlockIndex.redCactus || blockState.getBlock() == BlockIndex.greenCactus)
					{
						for (int i = 0; i < player.inventory.getSizeInventory(); i++)
						{
							ItemStack slotStack = player.inventory.getStackInSlot(i);

							if (slotStack.getItem() == Items.BOWL)
							{
								slotStack.shrink(1);
							}
						}

						player.addItemStackToInventory(bowlwater);
						player.inventory.getCurrentItem().damageItem(1, player);

						return EnumActionResult.PASS;
					}
				}
				/** **/
			}
			else
			{
				//Logger.info("has no bowl");
				hasbowl = false;
			}
		}

		return EnumActionResult.PASS;
	}

	private Boolean checkInventory(Boolean hasbowl, EntityPlayer player) 
	{

		return hasbowl;
	}

	public void registerItemModel() 
	{
		Reactioncraft.proxy.registerItemRenderer(this, 0, this.name);
	}
}