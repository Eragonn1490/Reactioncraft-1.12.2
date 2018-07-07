package com.reactioncraft.common.items.tools;

import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.item.ItemTool;

/**
 * Created on 12/22/17.
 */
public class ItemBaseTool extends ItemTool {
    protected ItemBaseTool(ToolMaterial materialIn, Set<Block> effectiveBlocksIn) {
        super(materialIn, effectiveBlocksIn);
    }
}
