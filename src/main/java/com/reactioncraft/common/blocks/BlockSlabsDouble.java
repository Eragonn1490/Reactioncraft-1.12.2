package com.reactioncraft.common.blocks;

import com.reactioncraft.Reactioncraft;

/**
 * A half slab which gets its properties from the tin slab
 * @author CJMinecraft
 *
 */
public class BlockSlabsDouble extends BlockSlabs {

	public BlockSlabsDouble() 
	{
		super();
		this.setCreativeTab(Reactioncraft.ReactioncraftItems);
	}

	/**
	 * Says that this block is the double version of the tin slab
	 */
	@Override
	public boolean isDouble() {
		return true;
	}
}
