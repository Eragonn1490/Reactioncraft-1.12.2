package com.reactioncraft.tiles;

import com.reactioncraft.ItemHandler;
import com.reactioncraft.api.ClayalizerRecipes;
import com.reactioncraft.blocks.machines.BlockBrickOven;
import com.reactioncraft.blocks.machines.BlockClayalizer;
import com.reactioncraft.itemhandlers.ClaylizerItemHandler;
import com.reactioncraft.registration.instances.BlockIndex;
import net.minecraft.block.state.IBlockState;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nullable;

public class TileEntityClayalizer extends TileEntityBase implements ITickable
{


    /** The number of ticks that the furnace will keep burning */
    public int fuelburnTime;
    /** The number of ticks that a fresh copy of the currently-burning item would keep the furnace burning for */
    public int currentItemBurnTime;
    public int cookTime;
    public int totalCookTime;
    private String customName;

    final static String ITEMS="Item handler", OUTPUT="Results";
    public static final short PROCESS_TIME=200;
    public ClaylizerItemHandler itemHandler=new ClaylizerItemHandler(2,this);
    public ItemHandler outputHandler=new ItemHandler(1,this);


    /**
     * Returns true if this thing is named
     */
    public boolean hasCustomName()
    {
        return this.customName != null && !this.customName.isEmpty();
    }

    public void setCustomInventoryName(String p_145951_1_)
    {
        this.customName = p_145951_1_;
    }

    @Nullable
    @Override
    public ITextComponent getDisplayName() {
        return hasCustomName() ? new TextComponentString(customName) : new TextComponentString("Claylizer");
    }

    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        itemHandler.deserializeNBT(compound.getCompoundTag(ITEMS));
        outputHandler.deserializeNBT(compound.getCompoundTag(OUTPUT));

        this.fuelburnTime = compound.getInteger("BurnTime");
        this.cookTime = compound.getInteger("CookTime");
        this.totalCookTime = compound.getInteger("CookTimeTotal");

        if (compound.hasKey("CustomName", 8))
        {
            this.customName = compound.getString("CustomName");
        }
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setInteger("BurnTime", this.fuelburnTime);
        compound.setInteger("CookTime", this.cookTime);
        compound.setInteger("CookTimeTotal", this.totalCookTime);

        compound.setTag(OUTPUT,outputHandler.serializeNBT());
        compound.setTag(ITEMS,itemHandler.serializeNBT());


        if (this.hasCustomName())
        {
            compound.setString("CustomName", this.customName);
        }

        return compound;
    }


    /**
     * Like the old updateEntity(), except more generic.
     */
    public void update()
    {

        if(!world.isRemote)
        {
            IBlockState machineState=world.getBlockState(pos);
            ItemStack fuel=itemHandler.getStackInSlot(1);
            if(!fuel.isEmpty())
            {
                fuelburnTime =getItemBurnTime(fuel);
                world.addBlockEvent(pos, BlockIndex.claylizer,0, fuelburnTime);
            }

            ItemStack input=itemHandler.getStackInSlot(0);
            if(!input.isEmpty())
            {
                ItemStack result= ClayalizerRecipes.instance().getSmeltingResult(input);
                if(!result.isEmpty())
                {
                    if(currentItemBurnTime>0)
                    {
                        totalCookTime++;
                        if(totalCookTime>=PROCESS_TIME)
                        {
                            if(outputHandler.insertItem(0,result,true).isEmpty())
                            {
                                outputHandler.insertItem(0,result,false);
                                input.shrink(1);
                                totalCookTime=0;
                            }
                        }
                        world.addBlockEvent(pos,BlockIndex.claylizer,1,totalCookTime);
                        currentItemBurnTime--;
                        if(currentItemBurnTime>0 && !machineState.getValue(BlockClayalizer.STATE)) world.setBlockState(pos,machineState.withProperty(BlockClayalizer.STATE,true));
                    }
                    else{
                        if(!fuel.isEmpty()) {
                            currentItemBurnTime = getItemBurnTime(fuel);
                            fuel.shrink(1);
                        }
                        if(currentItemBurnTime>0) world.setBlockState(pos,machineState.withProperty(BlockBrickOven.STATE,true));
                        else {
                            if(machineState.getValue(BlockClayalizer.STATE)) world.setBlockState(pos,machineState.withProperty(BlockClayalizer.STATE,false));
                        }
                    }
                    world.addBlockEvent(pos,BlockIndex.claylizer,2,currentItemBurnTime);
                }
            }
            else{
                if(machineState.getValue(BlockClayalizer.STATE)) world.setBlockState(pos,machineState.withProperty(BlockClayalizer.STATE,false));
            }

        }
    }


    @Override
    public boolean receiveClientEvent(int id, int type) {
        if(id==0)
        {
            fuelburnTime =type;
            return true;
        }
        else if(id==1)
        {
            totalCookTime=type;
            return true;
        }
        else if(id==2)
        {
            currentItemBurnTime=type;
            return true;
        }
        return super.receiveClientEvent(id, type);
    }


    /**
     * Returns the number of ticks that the supplied fuel item will keep the furnace burning, or 0 if the item isn't
     * fuel
     */
    public static int getItemBurnTime(ItemStack stack)
    {
        return TileEntityFurnace.getItemBurnTime(stack);
    }

    public static boolean isItemFuel(ItemStack stack)
    {
        /**
         * Returns the number of ticks that the supplied fuel item will keep the furnace burning, or 0 if the item isn't
         * fuel
         */
        return getItemBurnTime(stack) > 0;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return facing!=null && capability== CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getCapability(net.minecraftforge.common.capabilities.Capability<T> capability, net.minecraft.util.EnumFacing facing)
    {
        if (facing != null && capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            if (facing == EnumFacing.DOWN)
                return (T) outputHandler;
            else return (T) itemHandler;
        return super.getCapability(capability, facing);
    }
}