package com.reactioncraft.common.items.energy;


import com.reactioncraft.Reactioncraft;
import com.reactioncraft.common.capabilities.CapabilityTriggerHz;
import com.reactioncraft.common.items.ItemBase;
import com.reactioncraft.common.tiles.TileEntityTrigger;
import com.reactioncraft.common.utils.constants;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

/**
 * @see <a href="https://github.com/BuiltBrokenModding/VoltzEngine/blob/development/license.md">License</a> for what you can and can't do with the code.
 * Created by Dark(DarkGuardsman, Robert) on 6/25/2018.
 */
public class ItemTriggerClear extends ItemBase
{
    public ItemTriggerClear(String name)
    {
    	super(name);
        setCreativeTab(Reactioncraft.ReactioncraftItems);
        setMaxStackSize(1);
    }

    @Override
    public EnumActionResult onItemUseFirst(EntityPlayer player, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand)
    {
        if (!worldIn.isRemote)
        {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if (tileEntity instanceof TileEntityTrigger)
            {
                tileEntity.getCapability(CapabilityTriggerHz.CAPABILITY, null).setTriggerHz(0);
                player.sendMessage(new TextComponentTranslation(getUnlocalizedName() + ".info.cleared"));
            }
        }
        return EnumActionResult.SUCCESS;
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        return EnumActionResult.SUCCESS;
    }
}
