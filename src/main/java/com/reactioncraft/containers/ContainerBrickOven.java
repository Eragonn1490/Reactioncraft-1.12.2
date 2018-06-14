package com.reactioncraft.containers;

import com.reactioncraft.api.BrickOvenRecipes;
import com.reactioncraft.tiles.TileEntityBrickOven;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nullable;

public class ContainerBrickOven extends Container
{
    private final TileEntityBrickOven brickOven;
//    private int cookTime;
//    private int totalCookTime;
//    private int furnaceBurnTime;
//    private int currentItemBurnTime;

    public ContainerBrickOven(InventoryPlayer playerInventory, TileEntityBrickOven furnaceInventory)
    {
        this.brickOven = furnaceInventory;
        this.addSlotToContainer(new SlotItemHandler(furnaceInventory.itemHandler, 0, 56, 17));
        this.addSlotToContainer(new SlotItemHandler(furnaceInventory.itemHandler, 1, 56, 53));
        this.addSlotToContainer(new SlotItemHandler(furnaceInventory.outputHandler, 0, 116, 35));

        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int k = 0; k < 9; ++k)
        {
            this.addSlotToContainer(new Slot(playerInventory, k, 8 + k * 18, 142));
        }
    }

    public void addListener(IContainerListener listener)
    {
        super.addListener(listener);
//        listener.sendAllWindowProperties(this, this.brickOven);
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

//        for (int i = 0; i < this.listeners.size(); ++i)
//        {
//            IContainerListener icontainerlistener = this.listeners.get(i);
//
//            if (this.cookTime != this.brickOven.getField(2))
//            {
//                icontainerlistener.sendWindowProperty(this, 2, this.brickOven.getField(2));
//            }
//
//            if (this.furnaceBurnTime != this.brickOven.getField(0))
//            {
//                icontainerlistener.sendWindowProperty(this, 0, this.brickOven.getField(0));
//            }
//
//            if (this.currentItemBurnTime != this.brickOven.getField(1))
//            {
//                icontainerlistener.sendWindowProperty(this, 1, this.brickOven.getField(1));
//            }
//
//            if (this.totalCookTime != this.brickOven.getField(3))
//            {
//                icontainerlistener.sendWindowProperty(this, 3, this.brickOven.getField(3));
//            }
//        }
//
//        this.cookTime = this.brickOven.getField(2);
//        this.furnaceBurnTime = this.brickOven.getField(0);
//        this.currentItemBurnTime = this.brickOven.getField(1);
//        this.totalCookTime = this.brickOven.getField(3);
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data)
    {
//        this.brickOven.setField(id, data);
    }

    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return true;
    }

    /**
     * Take a stack from the specified inventory slot.
     */
    @Nullable
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index == 2)
            {
                if (!this.mergeItemStack(itemstack1, 3, 39, true))
                {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (index != 1 && index != 0)
            {
                if (!BrickOvenRecipes.instance().getSmeltingResult(itemstack1).isEmpty())
                {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if (TileEntityBrickOven.isItemFuel(itemstack1))
                {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if (index >= 3 && index < 30)
                {
                    if (!this.mergeItemStack(itemstack1, 30, 39, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if (index >= 30 && index < 39 && !this.mergeItemStack(itemstack1, 3, 30, false))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 3, 39, false))
            {
                return null;
            }

            if (itemstack1.getCount() == 0)
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount())
            {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, itemstack1);
        }

        return itemstack;
    }
}