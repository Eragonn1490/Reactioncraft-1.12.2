package com.reactioncraft.common.tiles;


import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.ItemStackHandler;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import com.reactioncraft.common.energystorageblock.config.ConfigEnergyStorage;
import com.reactioncraft.common.energystorageblock.energy.EnergySideState;
import com.reactioncraft.common.energystorageblock.energy.EnergySideWrapper;
import com.reactioncraft.common.energystorageblock.network.MessageDesc;
import com.reactioncraft.common.energystorageblock.network.MessageTileEnergy;
import com.reactioncraft.common.energystorageblock.network.NetworkHandler;
import com.reactioncraft.common.instances.BlockIndex;
import com.reactioncraft.core.EnergyModProxy;

/**
 * @see <a href="https://github.com/BuiltBrokenModding/VoltzEngine/blob/development/license.md">License</a> for what you can and can't do with the code.
 * Created by Dark(DarkGuardsman, Robert) on 6/30/2018.
 */
public class TileEntityEnergyStorage extends TileEntityEnergy implements ITickable
{
    //NBT keys
    public static final String NBT_ENERGY_SIDES = "energy_sides";
    public static final String NBT_INVENTORY = "inventory";

    //Inventory constants
    public static final int INVENTORY_SIZE = 2;
    public static final int SLOT_BATTERY_DISCHARGE = 0;
    public static final int SLOT_BATTERY_CHARGE = 1;
    /** Main inventory */
    public final ItemStackHandler inventory = new ItemStackHandler(INVENTORY_SIZE);

    //Side handler for input/output/disconnect state
    private EnergySideWrapper[] energySideWrapper = new EnergySideWrapper[6];

    private int prevEnergy = -1;
    private boolean sendDescPacket = false;
    private boolean markChanged = true;

    @Override
    public void update()
    {
        if (!world.isRemote)
        {
            //Pull power in first
            dischargeBattery();

            //Only move power if we have power to move
            if (energyStorage.getEnergyStored() > 0)
            {
                //Output to charge item, second to allow users to charge items
                chargeBattery();
                //Last handle connected tiles
                outputPowerToConnectedTiles();
            }

            if (sendDescPacket)
            {
                sendDescPacket = false;
                prevEnergy = energyStorage.getEnergyStored();
                MessageDesc.send(this);
            }
            else if (energyStorage.getEnergyStored() != prevEnergy)
            {
                prevEnergy = energyStorage.getEnergyStored();
                NetworkHandler.sendToAllAround(this, new MessageTileEnergy(this, prevEnergy));
            }
        }


        if (markChanged)
        {
            markChanged = false;

            //Mark dirty so tile saves
            markDirty();

            //Get block state
            IBlockState currentState = world.getBlockState(getPos());
            IBlockState actualState = BlockIndex.bloodstoneEnergyBlock.getActualState(currentState, world, getPos());

            //Set block to force a full update
            world.setBlockState(getPos(), actualState);

            //Remove and re-add to IC2 network
            invalidate();
            validate();
        }
    }

    protected void dischargeBattery()
    {
        ItemStack batteryToDischarge = inventory.getStackInSlot(SLOT_BATTERY_DISCHARGE);
        if (!batteryToDischarge.isEmpty() && batteryToDischarge.getCount() == 1)
        {
            if (batteryToDischarge.hasCapability(CapabilityEnergy.ENERGY, null))
            {
                IEnergyStorage storage = batteryToDischarge.getCapability(CapabilityEnergy.ENERGY, null);
                if (storage != null && storage.canExtract())
                {
                    //Check how much can be drained
                    int offer = storage.extractEnergy(ConfigEnergyStorage.INPUT_LIMIT, true);

                    //Fill tile storage
                    int taken = energyStorage.receiveEnergy(offer, false);

                    //Drain battery
                    storage.extractEnergy(taken, false);

                    //Trigger inventory update, without saving may fail
                    inventory.setStackInSlot(SLOT_BATTERY_DISCHARGE, batteryToDischarge);
                }
            }
            else
            {
                EnergyModProxy.dischargeBattery(energyStorage, ConfigEnergyStorage.INPUT_LIMIT, batteryToDischarge);
            }
        }
    }

    protected void chargeBattery()
    {
        ItemStack batteryToCharge = inventory.getStackInSlot(SLOT_BATTERY_CHARGE);
        if (!batteryToCharge.isEmpty() && batteryToCharge.getCount() == 1)
        {
            if (batteryToCharge.hasCapability(CapabilityEnergy.ENERGY, null))
            {
                IEnergyStorage storage = batteryToCharge.getCapability(CapabilityEnergy.ENERGY, null);
                if (storage != null && storage.canReceive())
                {
                    //Check how much can be inserted
                    int request = storage.receiveEnergy(ConfigEnergyStorage.OUTPUT_LIMIT, true);

                    //Drain power from tile
                    int drained = energyStorage.extractEnergy(request, false);

                    //Charge battery
                    storage.receiveEnergy(drained, false);

                    //Trigger inventory update, without saving may fail
                    inventory.setStackInSlot(SLOT_BATTERY_CHARGE, batteryToCharge);
                }
            }
            else
            {
                EnergyModProxy.chargeBattery(energyStorage, ConfigEnergyStorage.OUTPUT_LIMIT, batteryToCharge);
            }
        }
    }

    @Override
    public boolean canOutputEnergySide(EnumFacing side)
    {
        return getEnergySideWrapper(side).sideState == EnergySideState.OUTPUT;
    }

    @Override
    public boolean canInputEnergySide(EnumFacing side)
    {
        return getEnergySideWrapper(side).sideState == EnergySideState.INPUT;
    }

    @Override
    public int getInputLimit(EnumFacing side)
    {
        return ConfigEnergyStorage.INPUT_LIMIT;
    }

    @Override
    public int getOutputLimit(EnumFacing side)
    {
        return ConfigEnergyStorage.OUTPUT_LIMIT;
    }

    @Override
    public int getEnergyCapacity()
    {
        return ConfigEnergyStorage.CAPACITY;
    }

    public EnergySideState toggleEnergySide(EnumFacing side)
    {
        //update state
        EnergySideWrapper wrapper = getEnergySideWrapper(side);
        wrapper.sideState = wrapper.sideState.next();

        //Next tick update and send packet
        sendDescPacket = true;
        markChanged = true;

        //Return new state
        return wrapper.sideState;
    }

    @Override
    public void readDescMessage(NBTTagCompound tagCompound)
    {
        super.readDescMessage(tagCompound);
        markChanged = true;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        inventory.deserializeNBT(compound.getCompoundTag(NBT_INVENTORY));
        readData(compound);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        compound.setTag(NBT_INVENTORY, inventory.serializeNBT());
        return writeData(super.writeToNBT(compound));
    }

    @Override
    protected void readData(NBTTagCompound compound)
    {
        super.readData(compound);

        //Load side data
        if (compound.hasKey(NBT_ENERGY_SIDES))
        {
            NBTTagCompound sideSave = compound.getCompoundTag(NBT_ENERGY_SIDES);
            for (EnumFacing facing : EnumFacing.VALUES)
            {
                EnergySideWrapper wrapper = getEnergySideWrapper(facing);
                byte i = sideSave.getByte(facing.getName());
                if (i >= 0 && i < EnergySideState.values().length)
                {
                    wrapper.sideState = EnergySideState.values()[i];
                }
            }
        }
    }

    @Override
    protected NBTTagCompound writeData(NBTTagCompound compound)
    {
        //Save side data
        NBTTagCompound sideSave = new NBTTagCompound();
        for (EnumFacing facing : EnumFacing.VALUES)
        {
            EnergySideWrapper wrapper = energySideWrapper[facing.ordinal()];
            if (wrapper != null)
            {
                sideSave.setByte(facing.getName(), (byte) wrapper.sideState.ordinal());
            }
        }
        compound.setTag(NBT_ENERGY_SIDES, sideSave);

        return super.writeData(compound);
    }

    @Override
    @Nullable
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing)
    {
        if (capability == CapabilityEnergy.ENERGY)
        {
            return (T) (facing == null ? energyStorage : getEnergySideWrapper(facing));
        }
        return super.getCapability(capability, facing);
    }

    public EnergySideWrapper getEnergySideWrapper(@Nonnull EnumFacing facing)
    {
        if (energySideWrapper[facing.ordinal()] == null)
        {
            energySideWrapper[facing.ordinal()] = new EnergySideWrapper(this, facing);
            markChanged = true;
        }
        return energySideWrapper[facing.ordinal()];
    }
}
