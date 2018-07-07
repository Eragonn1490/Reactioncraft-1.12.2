package com.reactioncraft.common.blocks;

import javax.annotation.Nullable;
import com.reactioncraft.Reactioncraft;
import com.reactioncraft.common.blocks.enums.EnumChestType;
import com.reactioncraft.common.tiles.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class BlockBookcaseChest extends Block
{
    public static final PropertyEnum<EnumChestType> VARIANT_PROP = PropertyEnum.create("variant", EnumChestType.class);

    public BlockBookcaseChest()
    {
        super(Material.IRON);
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT_PROP, EnumChestType.Book));
        this.setHardness(3.0F);
        this.setCreativeTab(Reactioncraft.ReactioncraftTest);
    }

    @Nullable
    public ILockableContainer getLockableContainer(World worldIn, BlockPos pos)
    {
        return this.getContainer(worldIn, pos, false);
    }


    @Nullable
    public ILockableContainer getContainer(World worldIn, BlockPos pos, boolean allowBlocking)
    {
        return null;
    }



    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing heldItem, float side, float hitX, float hitY)
    {
        TileEntity te = worldIn.getTileEntity(pos);


        if (te == null || !(te instanceof TileEntityBookcaseChest))
        {
            return true;
        }


        if (worldIn.isSideSolid(pos.add(0, 1, 0), EnumFacing.DOWN))
        {
            return true;
        }


        if (worldIn.isRemote)
        {
            return true;
        }
        
        if(te instanceof TileEntityHiddenBookChest)
        {
            playerIn.openGui(Reactioncraft.instance, 3, worldIn, pos.getX(), pos.getY(), pos.getZ());
        }
        if(te instanceof TileEntityHiddenScrollChest)
        {
            playerIn.openGui(Reactioncraft.instance, 4, worldIn, pos.getX(), pos.getY(), pos.getZ());
        }

        return true;
    }


    @Override
    public boolean hasTileEntity(IBlockState state)
    {
        return true;
    }


    @Override
    public TileEntity createTileEntity(World world, IBlockState state)
    {
        return state.getValue(VARIANT_PROP).makeEntity();
    }


    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list)
    {
        for (EnumChestType type : EnumChestType.VALUES)
        {
            {
                list.add(new ItemStack(this, 1, type.ordinal()));
            }
        }
    }


    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT_PROP, EnumChestType.VALUES[meta]);
    }



    @Override
    public int getMetaFromState(IBlockState blockState)
    {

        return blockState.getValue(VARIANT_PROP).ordinal();
    }



    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, VARIANT_PROP);
    }


    @Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state)
    {
        super.onBlockAdded(world, pos, state);
        world.notifyBlockUpdate(pos, state, state, 3);
    }



    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        TileEntity te = worldIn.getTileEntity(pos);

        if (te != null && te instanceof TileEntityBookcaseChest)
        {
        	TileEntityBookcaseChest teic = (TileEntityBookcaseChest) te;

            worldIn.notifyBlockUpdate(pos, state, state, 3);
        }
    }


    @Override
    public int damageDropped(IBlockState state)
    {
        return state.getValue(VARIANT_PROP).ordinal();
    }


    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
    	TileEntityBookcaseChest tileentity = (TileEntityBookcaseChest) worldIn.getTileEntity(pos);

        if (tileentity != null)
        {
            InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory) tileentity);
        }
        super.breakBlock(worldIn, pos, state);
    }


    @Override
    public boolean hasComparatorInputOverride(IBlockState state)
    {
        return true;
    }


    @Override
    public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos)
    {
        return Container.calcRedstone(worldIn.getTileEntity(pos));
    }


    @Override
    @Deprecated
    public boolean eventReceived(IBlockState state, World worldIn, BlockPos pos, int id, int param)
    {
        super.eventReceived(state, worldIn, pos, id, param);

        TileEntity tileentity = worldIn.getTileEntity(pos);

        return tileentity != null && tileentity.receiveClientEvent(id, param);
    }
}