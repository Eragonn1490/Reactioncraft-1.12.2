package com.reactioncraft.items.tools;

import net.minecraft.block.Block;
import net.minecraft.item.ItemTool;

import java.util.Set;

/**
 * Created on 12/22/17.
 */
public class ItemBaseTool extends ItemTool {
    protected ItemBaseTool(ToolMaterial materialIn, Set<Block> effectiveBlocksIn) {
        super(materialIn, effectiveBlocksIn);
    }
}
