package com.reactioncraft.core.mods;


import com.reactioncraft.common.energystorageblock.config.ConfigPowerSystem;
import com.reactioncraft.core.EnergyModProxy;

import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergySink;
import ic2.api.energy.tile.IEnergyTile;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fml.common.Optional;

/**
 * @see <a href="https://github.com/BuiltBrokenModding/VoltzEngine/blob/development/license.md">License</a> for what you can and can't do with the code.
 * Created by Dark(DarkGuardsman, Robert) on 5/22/2018.
 */
public class IC2Proxy extends EnergyModProxy
{
    public static final IC2Proxy INSTANCE = new IC2Proxy();

    @Override
    @Optional.Method(modid = "ic2")
    public boolean outputPower(TileEntity target, TileEntity source, IEnergyStorage energyStorage, EnumFacing enumFacing)
    {
        if (source.hasCapability(CapabilityEnergy.ENERGY, enumFacing.getOpposite()))
        {
            if (target instanceof IEnergySink && ((IEnergySink) target).acceptsEnergyFrom(null, enumFacing))
            {
                //Get demand and convert to FE power
                double demand = ((IEnergySink) target).getDemandedEnergy();
                int request = (int) Math.floor(demand * ConfigPowerSystem.FE_PER_EU);

                //Check how much power we can remove
                int give = energyStorage.extractEnergy(request, true);
                if (give > 0)
                {
                    //Convert give to IC2
                    double inject = give / ConfigPowerSystem.FE_PER_EU;

                    //Inject energy
                    double leftOver = ((IEnergySink) target).injectEnergy(enumFacing, inject, 1);

                    //Remove energy from storage
                    inject -= leftOver;
                    int remove = (int) Math.ceil(inject * ConfigPowerSystem.FE_PER_EU);
                    energyStorage.extractEnergy(remove, false);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    @Optional.Method(modid = "ic2")
    protected boolean handleBatteryCharge(IEnergyStorage energyStorage, int limit, ItemStack stack)
    {
        if (stack.getItem() instanceof IElectricItem)
        {
            int tier = ((IElectricItem) stack.getItem()).getTier(stack);

            //Get energy to offer
            int offer = energyStorage.extractEnergy(limit, true);

            if (offer > 0)
            {
                //Convert to IC2 power
                double insert = offer / ConfigPowerSystem.FE_PER_EU;

                //Give energy
                double taken = ElectricItem.manager.charge(stack, insert, tier, false, false);

                //Drain energy from storage
                int energy = (int) Math.ceil(taken * ConfigPowerSystem.FE_PER_EU);
                energyStorage.extractEnergy(energy, false);
            }

            return true;

        }
        return false;
    }

    @Override
    @Optional.Method(modid = "ic2")
    protected boolean handleBatteryDischarge(IEnergyStorage energyStorage, int limit, ItemStack stack)
    {
        if (stack.getItem() instanceof IElectricItem)
        {
            int tier = ((IElectricItem) stack.getItem()).getTier(stack);

            //Calculate max drain from battery
            double drain = limit / ConfigPowerSystem.FE_PER_EU;
            drain = ElectricItem.manager.discharge(stack, drain, tier, false, true, true);

            //Calculate max insert into tile
            int input = (int) Math.floor(drain * ConfigPowerSystem.FE_PER_EU);
            input = energyStorage.receiveEnergy(input, true);

            if (input > 0)
            {
                //Drain battery
                drain = input / ConfigPowerSystem.FE_PER_EU;
                drain = ElectricItem.manager.discharge(stack, drain, tier, false, true, false);

                //Insert into tile
                input = (int) Math.floor(drain * ConfigPowerSystem.FE_PER_EU);
                energyStorage.receiveEnergy(input, false);
            }

            return true;
        }
        return false;
    }

    @Override
    public boolean isBattery(ItemStack stack)
    {
        return stack.getItem() instanceof IElectricItem;
    }

    @Override
    @Optional.Method(modid = "ic2")
    public void onTileValidate(TileEntity tile)
    {
        if (ConfigPowerSystem.ENABLE_IC2 && tile instanceof IEnergyTile && !tile.getWorld().isRemote)
        {
            MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent((IEnergyTile) tile));
        }
    }

    @Override
    @Optional.Method(modid = "ic2")
    public void onTileInvalidate(TileEntity tile)
    {
        if (ConfigPowerSystem.ENABLE_IC2 && tile instanceof IEnergyTile && !tile.getWorld().isRemote)
        {
            MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent((IEnergyTile) tile));
        }
    }
}
