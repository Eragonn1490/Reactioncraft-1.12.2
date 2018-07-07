package com.reactioncraft.common.energystorageblock.inventory;

import javax.annotation.Nonnull;

import com.reactioncraft.Reactioncraft;
import com.reactioncraft.common.tiles.TileEntityEnergyStorage;
import net.minecraft.item.ItemStack;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.ItemStackHandler;

/**
 * @see <a href="https://github.com/BuiltBrokenModding/VoltzEngine/blob/development/license.md">License</a> for what you can and can't do with the code.
 * Created by Dark(DarkGuardsman, Robert) on 7/1/2018.
 */
public class InventoryEnergyStorage extends ItemStackHandler
{
    public final TileEntityEnergyStorage host;

    public InventoryEnergyStorage(TileEntityEnergyStorage host, int slots)
    {
        super(slots);
        this.host = host;
    }

    @Override
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate)
    {
        if (slot == TileEntityEnergyStorage.SLOT_BATTERY_CHARGE || slot == TileEntityEnergyStorage.SLOT_BATTERY_DISCHARGE)
        {
            if (isBattery(stack))
            {
                return super.insertItem(slot, stack, simulate);
            }
            return stack;
        }
        return super.insertItem(slot, stack, simulate);
    }

    /**
     * Checks if the itemstack is a battery
     *
     * @param stack - stack
     * @return true if battery
     */
    public static boolean isBattery(@Nonnull ItemStack stack)
    {
        return stack.hasCapability(CapabilityEnergy.ENERGY, null)
                || Reactioncraft.energyModProxies.stream().anyMatch(proxy -> proxy.isBattery(stack));
    }

    @Override
    public int getSlotLimit(int slot)
    {
        if (slot == TileEntityEnergyStorage.SLOT_BATTERY_CHARGE || slot == TileEntityEnergyStorage.SLOT_BATTERY_DISCHARGE)
        {
            return 1;
        }
        return 64;
    }

    @Override
    protected void onContentsChanged(int slot)
    {
        //Mark the tile has changed so it saves
        host.markDirty();
    }
}
