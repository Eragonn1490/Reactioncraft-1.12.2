package com.reactioncraft.tiles;
 
import javax.annotation.Nonnull;
import com.reactioncraft.Reactioncraft;
import com.reactioncraft.registration.instances.PropertyIndex;
import com.reactioncraft.utils.constants;
 
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.energy.IEnergyStorage;
 
public class TileEntityConverterBackup extends TileEntityBase implements IEnergyStorage, ITickable
{
    private int energyStored;
    private int energyRecieved;
    private int energySent;
    private int energyMax = constants.MaxEnergy;
 
    @CapabilityInject(IEnergyStorage.class)
	static Capability<IEnergyStorage> Energy_CAPABILITY = null;
    
    @Override
    public NBTTagCompound writeToNBT (NBTTagCompound compound) 
    {
        this.writeNBT(compound);
 
        compound.setInteger("energy_stored",   this.energyStored);
        compound.setInteger("energy_recieved", this.energyRecieved);
        compound.setInteger("energy_sent",     this.energySent);
 
        return super.writeToNBT(compound);
    }
 
    @Override
    public void readFromNBT (NBTTagCompound compound) 
    {
        this.readNBT(compound);
 
        this.energyStored =   compound.getInteger("energy_stored");
        this.energyRecieved = compound.getInteger("energy_recieved");
        this.energySent =     compound.getInteger("energy_sent");
 
        super.readFromNBT(compound);
    }
 
    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) 
    {
        return this.energyRecieved;
    }
 
    @Override
    public int extractEnergy(int maxExtract, boolean simulate) 
    {
        return this.energySent;
    }
 
    @Override
    public int getEnergyStored() 
    {
        return this.energyRecieved;
    }
 
    @Override
    public int getMaxEnergyStored() 
    {
        return energyMax;
    }   
 
    @Override
    public boolean canExtract() 
    {
        return true;
    }
 
    @Override
    public boolean canReceive() 
    {
        return true;
    }
 
//    @Override
//    public void update() 
//    {   
//        if ((this.getEnergyStored() <= this.getMaxEnergyStored())) 
//        {   
//            TileEntity tile = world.getTileEntity(getPos());
// 
//            if (tile instanceof IEnergyStorage) 
//            {
//                int maxExtract = this.receiveEnergy(10, true); //Gets the maximum amount of energy that can be extracted from this tile in one tick.
//                int maxAvailable = this.extractEnergy(maxExtract, true); //Simulates removing "maxExtract" to find out how much energy is actually available.
// 
//                int energyTransferred = this.receiveEnergy(maxAvailable, true); //Sends "maxAvailable" to the target tile and records how much energy was accepted. 
// 
//                this.receiveEnergy(energyTransferred, true);//Extract the energy transferred from the internal storage.
//            }
//        }
//    }
    
    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) 
    {
      if (capability == Energy_CAPABILITY) 
      {
        return true;
      }
      return super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
      if (capability == Energy_CAPABILITY) {
        T inventory = null;
		return (T) inventory;
      }
      return super.getCapability(capability, facing);
    }
    
	@Override
	public void update() 
	{
		
	}
}