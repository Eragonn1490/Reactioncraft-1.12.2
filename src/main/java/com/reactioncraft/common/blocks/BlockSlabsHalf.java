package com.reactioncraft.common.blocks;

import com.reactioncraft.Reactioncraft;

/**
 * A half slab which gets its properties from the tin slab
 * @author CJMinecraft
 *
 */
public class BlockSlabsHalf extends BlockSlabs {

	public BlockSlabsHalf() {
		super();
		this.setCreativeTab(Reactioncraft.ReactioncraftTest);
	}

	/**
	 * Says that it isn't the double version of the block
	 */
	@Override
	public boolean isDouble() {
		return false;
	}
}