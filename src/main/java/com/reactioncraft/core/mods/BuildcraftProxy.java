package com.reactioncraft.core.mods;

import buildcraft.api.mj.IMjReceiver;
import buildcraft.api.mj.MjAPI;
import com.reactioncraft.common.capabilities.MjCapabilityProvider;
import com.reactioncraft.common.energystorageblock.config.ConfigEnergyStorage;
import com.reactioncraft.common.energystorageblock.config.ConfigPowerSystem;
import com.reactioncraft.common.energystorageblock.config.ConfigWirelessEnergyTower;
import com.reactioncraft.common.tiles.TileEntityEnergyStorage;
import com.reactioncraft.common.tiles.TileEntityWirelessConnector;
import com.reactioncraft.common.utils.constants;
import com.reactioncraft.core.EnergyModProxy;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * @see <a href="https://github.com/BuiltBrokenModding/VoltzEngine/blob/development/license.md">License</a> for what you can and can't do with the code.
 * Created by Dark(DarkGuardsman, Robert) on 5/22/2018.
 */
public class BuildcraftProxy extends EnergyModProxy
{
    public static final String BC = "buildcraftenergy";
    public static final BuildcraftProxy INSTANCE = new BuildcraftProxy();

    @Override
    @Optional.Method(modid = "buildcraftenergy")
    public void preInit()
    {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    @Optional.Method(modid = "buildcraftenergy")
    public void attachCapabilityItem(AttachCapabilitiesEvent<TileEntity> event)
    {
        if (event.getObject() instanceof TileEntityEnergyStorage)
        {
            event.addCapability(
                    new ResourceLocation(constants.MODID, "wrapper.buildcraft.energy"),
                    new MjCapabilityProvider((TileEntityEnergyStorage) event.getObject()));
        }
    }

    @Override
    @Optional.Method(modid = "buildcraftenergy")
    public boolean outputPower(TileEntity target, TileEntity source, IEnergyStorage energyStorage, EnumFacing enumFacing)
    {
        //Check that we support output for side
        if (ConfigPowerSystem.ENABLE_BUILDCRAFT && source.hasCapability(CapabilityEnergy.ENERGY, enumFacing.getOpposite()))
        {
            //Check that target can receive energy
            if (target.hasCapability(MjAPI.CAP_RECEIVER, enumFacing))
            {
                IMjReceiver receiver = target.getCapability(MjAPI.CAP_RECEIVER, enumFacing);
                if (receiver != null && receiver.canReceive())
                {
                    long request = limitOutput(source, receiver.getPowerRequested());
                    if (request > 0)
                    {
                        //Convert and check extract
                        int energy = (int) Math.floor(toForgeEnergy(request));
                        energy = energyStorage.extractEnergy(energy, true);

                        if (energy > 0)
                        {
                            //Convert and insert
                            long insert = (long) Math.floor(toBuildcraftEnergy(energy));
                            long leftOver = receiver.receivePower(insert, false);

                            //Get energy taken
                            long taken = insert - leftOver;

                            //convert and remove energy
                            energy = (int) Math.ceil(toForgeEnergy(taken));
                            energyStorage.extractEnergy(energy, false);
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public static long limitOutput(TileEntity source, long mj)
    {
        int limit = 200;
        if (source instanceof TileEntityEnergyStorage)
        {
            limit = ConfigEnergyStorage.OUTPUT_LIMIT_BC;
        }
        else if (source instanceof TileEntityWirelessConnector)
        {
            limit = ConfigWirelessEnergyTower.OUTPUT_LIMIT_BC;
        }
        return Math.min(mj, limit * MjAPI.ONE_MINECRAFT_JOULE);
    }

    public static long limitInput(TileEntity source, long mj)
    {
        int limit = 200;
        if (source instanceof TileEntityEnergyStorage)
        {
            limit = ConfigEnergyStorage.INPUT_LIMIT_BC;
        }
        else if (source instanceof TileEntityWirelessConnector)
        {
            limit = ConfigWirelessEnergyTower.INPUT_LIMIT_BC;
        }
        return Math.min(mj, limit * MjAPI.ONE_MINECRAFT_JOULE);
    }


    public static double toBuildcraftEnergy(int fe)
    {
        return (fe / ConfigPowerSystem.FE_PER_MJ) * MjAPI.MJ;
    }

    public static double toForgeEnergy(long mj)
    {
        return (mj / (double) MjAPI.MJ) * ConfigPowerSystem.FE_PER_MJ;
    }
}
